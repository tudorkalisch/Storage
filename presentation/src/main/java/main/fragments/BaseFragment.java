package main.fragments;




import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import java.util.Objects;

import common.mvp.MvpView;
import es.dmoral.toasty.Toasty;
import injection.component.ActivityComponent;
import injection.component.DaggerActivityComponent;
import injection.module.ActivityModule;
import injection.module.LabelRoomModule;
import injection.module.ScanRoomModule;
import application.MyApplication;

public abstract class BaseFragment  extends Fragment implements MvpView {
    private ActivityComponent mActivityComponent;
    public ActivityComponent activityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .applicationComponent(MyApplication.get(Objects.requireNonNull(this.getActivity())).getComponent())
                    .activityModule(new ActivityModule(this.getActivity()))
                    .scanRoomModule(new ScanRoomModule(this.getActivity()))
                    .labelRoomModule(new LabelRoomModule(this.getActivity()))
                    .build();
        }
        return mActivityComponent;
    }

    public void successToast(final String msg){
        Handler handler = new Handler(Looper.getMainLooper());

        handler.postDelayed(() -> Toasty.success(Objects.requireNonNull(getContext()), msg, Toast.LENGTH_SHORT, true).show(), 10);
    }

    public void errorToast(final String msg){
        Handler handler = new Handler(Looper.getMainLooper());

        handler.postDelayed(() -> Toasty.error(Objects.requireNonNull(getContext()), msg, Toast.LENGTH_SHORT, true).show(), 10);
    }

    public void infoToast(final String msg){
        Handler handler = new Handler(Looper.getMainLooper());

        handler.postDelayed(() -> Toasty.info(Objects.requireNonNull(getContext()), msg, Toast.LENGTH_SHORT, true).show(), 10);
    }
}