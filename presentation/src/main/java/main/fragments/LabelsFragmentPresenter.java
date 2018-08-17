package main.fragments;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import domain.models.Label;
import domain.usecase.GetLabels;
import io.reactivex.observers.DisposableObserver;
import main.fragments.model.MainViewModel;
import main.fragments.model.MainViewModelItem;

public class LabelsFragmentPresenter extends BaseFragmentPresenter<LabelsFragmentContract.View> implements LabelsFragmentContract.Presenter {

    @Inject
    GetLabels getLabelsUseCase;

    @Inject
    LabelsFragmentPresenter(GetLabels getLabelsUseCase) {
        this.getLabelsUseCase = getLabelsUseCase;
    }

    @Override
    public void getDataLabels() {
        this.getLabelsUseCase.execute(new LabelListObserver(), null);
    }

    private void showLabels(List<Label> labelItems) {
        List<MainViewModelItem> mainItems = new ArrayList<>();
        for (Label item : labelItems) {
            mainItems.add(new MainViewModelItem(item.getId(), item.getItemName()));
        }
        this.getMvpView().showData(new MainViewModel(mainItems));
    }

    public void dispose() {
        this.getLabelsUseCase.dispose();
    }


    private class LabelListObserver extends DisposableObserver<List<Label>> {
        @Override
        public void onNext(List<Label> labelItems) {
            LabelsFragmentPresenter.this.showLabels(labelItems);
        }

        @Override
        public void onError(Throwable e) {
            LabelsFragmentPresenter.this.getMvpView().showError();
        }

        @Override
        public void onComplete() {
        }
    }
}


