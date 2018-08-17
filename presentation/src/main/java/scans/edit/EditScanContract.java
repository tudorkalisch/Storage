package scans.edit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;


import java.util.List;

import common.mvp.MvpPresenter;
import common.mvp.MvpView;
import scans.model.DrawRectangles;
import scans.model.ScanViewModel;

public interface EditScanContract {

    interface View extends MvpView {
        void showData(ScanViewModel model);

        void showError();

        void showSuccess();

        void onErrorMessage(String text);

        void showRectangulars(List<DrawRectangles> drawRectangles);
    }

    interface Presenter extends MvpPresenter<View> {
        void getDataScan(String title);

        Bitmap getImageFromGallery(int resultCode, int requestCode, Intent data, Context context);
    }
}
