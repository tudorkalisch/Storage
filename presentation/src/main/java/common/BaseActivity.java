package common;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import common.mvp.MvpView;
import es.dmoral.toasty.Toasty;
import injection.component.ActivityComponent;
import injection.component.DaggerActivityComponent;
import injection.module.ActivityModule;
import injection.module.LabelRoomModule;
import injection.module.ScanRoomModule;
import application.MyApplication;


public abstract class BaseActivity extends AppCompatActivity implements MvpView {
    private ActivityComponent mActivityComponent;

    public ActivityComponent activityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .applicationComponent(MyApplication.get(this).getComponent())
                    .activityModule(new ActivityModule(this))
                    .scanRoomModule(new ScanRoomModule(this))
                    .labelRoomModule(new LabelRoomModule(this))
                    .build();
        }
        return mActivityComponent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void successToast(final String msg) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> Toasty.success(getApplicationContext(), msg, Toast.LENGTH_SHORT, true).show(), 10);
    }

    public void errorToast(final String msg) {
        Handler handler = new Handler(Looper.getMainLooper());

        handler.postDelayed(() -> Toasty.error(getApplicationContext(), msg, Toast.LENGTH_SHORT, true).show(), 10);
    }

    public void infoToast(final String msg) {
        Handler handler = new Handler(Looper.getMainLooper());

        handler.postDelayed(() -> Toasty.info(getApplicationContext(), msg, Toast.LENGTH_SHORT, true).show(), 10);
    }

    public String saveToInternalStorage(Bitmap bitmapImage, Context context) {
        ContextWrapper cw = new ContextWrapper(context.getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File mypath = new File(directory, bitmapImage.toString() + ".jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 30, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert fos != null;
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    public Bitmap loadImageFromStorage(String path,String name)
    {
        try {
            File f=new File(path, name);
            return BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
