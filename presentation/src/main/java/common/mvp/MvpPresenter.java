package common.mvp;

public interface MvpPresenter<T extends MvpView> {

    void attachView(T mvpView);

    void detachView();

}
