package main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import java.io.IOException;

import common.mvp.MvpPresenter;
import common.mvp.MvpView;

public interface MainContract {

    interface View extends MvpView {
        void onErrorMessage(String text);
    }

    interface Presenter extends MvpPresenter<View> {
        Bitmap getImageFromCamera(int resultCode, int requestCode, Intent data, Context context) throws IOException;
    }
}
