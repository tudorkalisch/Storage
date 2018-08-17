package scans.add;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.deakdan.labelreaderv2.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import common.BaseActivity;
import main.MainActivity;
import scans.add.adapter.ScanAdapter;
import scans.model.DrawRectangles;
import scans.model.ScanViewModel;
import utils.Constants;

public class AddScanActivity extends BaseActivity implements AddScanContract.View {
    @BindView(R.id.scanItemRV)
    RecyclerView scanItems;
    @BindView(R.id.caputredIV)
    ImageView imageView;
    @BindView(R.id.scanTitle)
    EditText scanTitle;
    @Inject
    AddScanPresenter addScanPresenter;
    ScanAdapter scanAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scan);
        ButterKnife.bind(this);
        init();

    }
    @OnClick(R.id.backButton)
    public void backBtn(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }


    void init() {
        activityComponent().inject(this);
        addScanPresenter.attachView(this);
        scanAdapter = new ScanAdapter(new ScanViewModel());
        scanItems.setHasFixedSize(true);
        scanItems.setLayoutManager(new LinearLayoutManager(this));
        scanItems.setAdapter(scanAdapter);
        addScanPresenter.getDataLabel(getIntent().getStringExtra(Constants.ITEM_NAME));
        addScanPresenter.getDrawRectangles(getBitmap());
    }

    @Override
    public void showData(ScanViewModel model) {
        scanAdapter.setData(model);
        scanAdapter.notifyDataSetChanged();
    }
    @Override
    public void showRectangulars(List<DrawRectangles> list) {
        draw(list, getBitmap());
    }


    public Bitmap getBitmap() {
        String path = getIntent().getStringExtra(Constants.IMAGE);
        String imageName = getIntent().getStringExtra(Constants.IMAGE_NAME);
        return loadImageFromStorage(path,imageName);
    }


    void draw(List<DrawRectangles> drawRectangles, Bitmap bitmap) {
        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Paint p = new Paint();
        Canvas canvas = new Canvas(mutableBitmap);
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.RED);
        for (DrawRectangles drawRectangles1 : drawRectangles) {
            canvas.drawRect(drawRectangles1.getRect(), p);
        }
        setOnTouchListener(imageView, drawRectangles, canvas, p);
        imageView.setImageBitmap(mutableBitmap);
        imageView.draw(canvas);
    }

    @SuppressLint("ClickableViewAccessibility")
    void setOnTouchListener(ImageView imageView, List<DrawRectangles> drawRectangles, Canvas canvas, Paint p) {
        imageView.setOnTouchListener((view, motionEvent) -> {
            switch (motionEvent.getAction() & motionEvent.getActionMasked()) {
                case MotionEvent.ACTION_DOWN: {
                    pressMotion(motionEvent, drawRectangles, canvas, p);
                }
                break;
                default:
                    break;
            }
            imageView.draw(canvas);
            return true;
        });
    }

    public void pressMotion(MotionEvent motionEvent, List<DrawRectangles> drawRectangles, Canvas canvas, Paint p) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        Paint p1 = new Paint();
        p1.setStyle(Paint.Style.STROKE);
        p1.setColor(Color.GREEN);
        imageView.invalidate();
        for (int i = 0; i < drawRectangles.size(); i++) {

            if (drawRectangles.get(i).getRect().contains(x, y)) {
                if (drawRectangles.get(i).isVisited()) {
                    canvas.drawRect(drawRectangles.get(i).getRect(), p1);
                    drawRectangles.get(i).setVisited(true);
                }
                scanAdapter.setTextToCopy(drawRectangles.get(i).getText());
                showMessage();
            } else {
                if (drawRectangles.get(i).isVisited()) {
                    canvas.drawRect(drawRectangles.get(i).getRect(), p);
                    imageView.draw(canvas);
                }
            }
        }
    }

    @Override
    public void showSuccess() {
        successToast("Success!");
    }

    private void showMessage() {
        successToast("Value copied");
    }

    @Override
    public void showError() {
        errorToast("Error adding scan");
    }


    @OnClick(R.id.addBT)
    public void setAddBT() {
        String stringTitle = scanTitle.getText().toString();

        if(TextUtils.isEmpty(stringTitle)) {
            scanTitle.setError(getString(R.string.error_null_check));
            scanTitle.requestFocus();
            return;
        }
        ScanViewModel scanViewModel = scanAdapter.getData();
        Bitmap bitmap = getBitmap();
        scanViewModel.setBitmap(bitmap);
        scanViewModel.setTitle(scanTitle.getText().toString());
        addScanPresenter.addScan(scanViewModel, this);
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        ActivityOptions options =
                ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.fade_out);
        this.startActivity(myIntent, options.toBundle());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        addScanPresenter.dispose();
        addScanPresenter.detachView();
    }

}
