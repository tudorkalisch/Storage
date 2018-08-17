package main.fragments;


import common.mvp.MvpPresenter;
import common.mvp.MvpView;
import main.fragments.model.MainViewModel;

public interface ScanFragmentContract {

    interface View extends MvpView {
        void showData(MainViewModel item);
    }

    interface Presenter extends MvpPresenter<View> {
        void getDataScans();
    }
}

