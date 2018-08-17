package main;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;

import java.io.IOException;
import java.util.Objects;

import javax.inject.Inject;

import common.BasePresenter;
import timber.log.Timber;
import utils.Constants;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    @Inject
    MainPresenter() {

    }

    public Bitmap getImageFromCamera(int resultCode, int requestCode, Intent data, Context context) {
        Bitmap bitmap = null;
        if (requestCode == Constants.SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                bitmap = null;
                getView().onErrorMessage("Cancelled");
            }
        } else {
            if (requestCode == Constants.SELECT_IMAGE2) {
                if (resultCode == Activity.RESULT_OK) {
                    if (data != null) {
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), data.getData());

                        } catch (IOException e) {
                            Timber.e(e);
                        }

                    }
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    getView().onErrorMessage("Cancelled");
                }
            }
        }
        return bitmap;
    }
}
