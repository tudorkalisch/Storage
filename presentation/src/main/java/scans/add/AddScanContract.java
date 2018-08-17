package scans.add;

import java.util.List;

import common.mvp.MvpPresenter;
import common.mvp.MvpView;
import scans.model.DrawRectangles;
import scans.model.ScanViewModel;

public interface AddScanContract {

    interface View extends MvpView {
        void showData(ScanViewModel model);

        void showSuccess();

        void showError();

        void showRectangulars(List<DrawRectangles> drawRectangles);




    }

    interface Presenter extends MvpPresenter<View> {

    }
}
