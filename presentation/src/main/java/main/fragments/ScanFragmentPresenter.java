package main.fragments;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import domain.models.Scan;
import domain.usecase.GetScans;
import injection.annotation.PerActivity;
import io.reactivex.observers.DisposableObserver;
import main.fragments.model.MainViewModel;
import main.fragments.model.MainViewModelItem;


@PerActivity
public class ScanFragmentPresenter extends BaseFragmentPresenter<ScanFragmentContract.View> implements ScanFragmentContract.Presenter {
    @Inject
    GetScans getScansUseCase;

    @Inject
    ScanFragmentPresenter(GetScans getScansUseCase) {
        this.getScansUseCase = getScansUseCase;
    }

    @Override
    public void getDataScans() {
        this.getScansUseCase.execute(new ScanListObserver(), null);
    }

    private void showScans(List<Scan> scanItems) {
        List<MainViewModelItem> mainItems = new ArrayList<>();
        for (Scan item : scanItems) {
            mainItems.add(new MainViewModelItem(item.getId(),item.getTitle()));
        }
        this.getMvpView().showData(new MainViewModel(mainItems));
    }

    public void dispose() {
        this.getScansUseCase.dispose();
    }

    private class ScanListObserver extends DisposableObserver<List<Scan>> {
        @Override
        public void onNext(List<Scan> scanItems) {
            ScanFragmentPresenter.this.showScans(scanItems);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }


}
