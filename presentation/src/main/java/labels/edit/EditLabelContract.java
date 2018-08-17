package labels.edit;


import common.mvp.MvpPresenter;
import common.mvp.MvpView;
import labels.model.LabelViewModel;

public interface EditLabelContract {

    interface View extends MvpView {

        void showData(LabelViewModel model);

        void showError();

        void showSuccess();
    }

    interface Presenter extends MvpPresenter<View> {

        void getDataLabel(String itemName);

        void disposeGetLabel();

        void disposeSaveLabel();

    }
}
