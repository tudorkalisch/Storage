package labels.add;

import common.mvp.MvpPresenter;
import common.mvp.MvpView;
import labels.model.LabelViewModel;

public interface AddLabelContract {

    interface View extends MvpView {
        void showData(LabelViewModel model);

        void showError();

        void showSuccess();
    }

    interface Presenter extends MvpPresenter<View> {
    }

}
