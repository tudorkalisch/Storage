package scans.edit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.deakdan.labelreaderv2.R;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import common.BaseActivity;
import main.MainActivity;
import scans.edit.adapter.ScanAdapter;
import scans.model.DrawRectangles;
import scans.model.ScanViewModel;
import utils.Constants;

public class EditScanActivity extends BaseActivity implements EditScanContract.View {
    @Inject
    EditScanPresenter editScanPresenter;
    @BindView(R.id.editScanRecyclerView)
    RecyclerView editScanItems;
    @BindView(R.id.editScanImageView)
    ImageView imageView;
    @BindView(R.id.editTitleEText)
    EditText title;
    ScanAdapter scanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_scan);
        ButterKnife.bind(this);
        init();
    }

    void init() {
        activityComponent().inject(this);
        editScanPresenter.attachView(this);
        scanAdapter = new ScanAdapter(new ScanViewModel());
        editScanItems.setHasFixedSize(true);
        editScanItems.setLayoutManager(new LinearLayoutManager(this));
        editScanItems.setAdapter(scanAdapter);
        editScanPresenter.getDataScan(Objects.requireNonNull(getIntent().getExtras()).getString(Constants.TITLE_EDIT));
}

    public void onErrorMessage(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRectangulars(List<DrawRectangles> drawRectangles) {
        draw(drawRectangles, scanAdapter.getMyData().getBitmap());
    }

    void draw(List<DrawRectangles> drawRectangles, Bitmap bitmap) {
        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Paint p = new Paint();
        Canvas canvas = new Canvas(mutableBitmap);
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.RED);
       // p.setStrokeWidth(10);
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
       // p1.setStrokeWidth(10);
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


    private void showMessage() {
        successToast("Value copied");
    }

    public void showData(ScanViewModel model) {
        title.setText(model.getTitle());
        imageView.setImageBitmap(model.getBitmap());
        scanAdapter.setData(model);
        editScanPresenter.getDrawRectangles(model.getBitmap());
    }

    @OnClick(R.id.backButton)
    public void backBtn() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showSuccess() {
        successToast("Success!");
    }

    @Override
    public void showError() {
        errorToast("Error");
    }


    @OnClick(R.id.editBT)
    public void setEditBT() {
        Intent intent = new Intent(this, MainActivity.class);
        ScanViewModel model = scanAdapter.getMyData();
        model.setTitle(title.getText().toString());
        this.editScanPresenter.updateScan(model, this);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        editScanPresenter.dispose();
        editScanPresenter.updateScan.dispose();
        editScanPresenter.getScanLabelMlKitUseCase.dispose();
        editScanPresenter.getScanUseCase.dispose();
    }
}
