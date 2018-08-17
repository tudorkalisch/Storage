package main.fragments;


import common.mvp.MvpPresenter;
import common.mvp.MvpView;
import main.fragments.model.MainViewModel;

public interface LabelsFragmentContract {
    interface View extends MvpView {
        void showData(MainViewModel item);
        void showError();
    }

    interface Presenter extends MvpPresenter<View> {
        void getDataLabels();
    }


}
