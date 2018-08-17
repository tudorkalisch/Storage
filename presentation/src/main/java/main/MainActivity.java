package main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deakdan.labelreaderv2.R;
import com.github.clans.fab.FloatingActionMenu;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import common.BaseActivity;
import items.ItemListActivity;
import labels.add.AddLabelActivity;
import main.fragments.LabelsFragment;
import main.fragments.ScansFragment;
import utils.Constants;

public class MainActivity extends BaseActivity implements MainContract.View {
    @Inject
    MainPresenter mMainPresenter;


    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.mainFloatBtn)
    FloatingActionMenu mainFloat;
    @BindView(R.id.searchEditText)
    TextView titleApp;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.MyMaterialTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();

    }


    void init() {
        activityComponent().inject(this);
        mMainPresenter.attachView(this);
        setToolbar();
        setViewPagerScans();

    }

    private void setViewPagerScans() {
        ViewPagerAd adapter = new ViewPagerAd(getSupportFragmentManager());
        adapter.addFragment(new ScansFragment(), Constants.SCANS_TOOLBAR);
        adapter.addFragment(new LabelsFragment(), Constants.LABEL_TOOLBAR);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bitmap = mMainPresenter.getImageFromCamera(resultCode, requestCode, data, this);
        if (bitmap == null) {
            cancelIntent();
        } else {
            Intent intent = new Intent(this, ItemListActivity.class);
            intent.putExtra(Constants.IMAGE, saveToInternalStorage(bitmap,this));
            intent.putExtra(Constants.IMAGE_NAME, bitmap.toString() + ".jpg");
            startActivity(intent);
        }

    }

    private void cancelIntent() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    private void setToolbar() {
        tabs.setupWithViewPager(viewPager);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
    }

    @OnClick(R.id.scanFloatBtn)
    public void scanFloatClick(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, Constants.SELECT_IMAGE);
    }


    @OnClick(R.id.labelFloatBtn)
    public void labelFloatClick(View view) {
        Intent intent = new Intent(this, AddLabelActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.scanFloatGalleryBtn)
    public void scanFloatGallery(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, Constants.SELECT_PICTURE), Constants.SELECT_IMAGE2);
    }

    @Override
    public void onBackPressed() {
        mainFloat.close(true);
    }

    @Override
    protected void onResumeFragments() {
        mainFloat.close(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    @Override
    public void onErrorMessage(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

}
