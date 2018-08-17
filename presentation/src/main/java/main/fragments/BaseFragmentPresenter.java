package main.fragments;
import common.mvp.MvpPresenter;
import common.mvp.MvpView;
public class BaseFragmentPresenter<T extends MvpView> implements MvpPresenter<T> {
        private T mMvpView;

        @Override
        public void attachView(T mvpView) {
            mMvpView = mvpView;
        }

        @Override
        public void detachView() {
            mMvpView = null;
        }

        private boolean isViewAttached() {
            return mMvpView != null;
        }

        T getMvpView() {
            return mMvpView;
        }

        public void checkViewAttached() {
            if (!isViewAttached()){
                throw new MvpViewNotAttachedException();
            }
        }

        public static class MvpViewNotAttachedException extends RuntimeException {
            MvpViewNotAttachedException() {
                super("Please call Presenter.attachView(MvpView) before" +
                        " requesting data to the Presenter");
            }
        }
    }
