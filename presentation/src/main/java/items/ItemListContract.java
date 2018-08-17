package items;


import java.util.List;

import common.mvp.MvpView;

public interface ItemListContract {

    interface View extends MvpView {
        void showError();

        void showData(List<String> model);
    }

    interface Presenter {
        void getDataLabels();
    }
}
