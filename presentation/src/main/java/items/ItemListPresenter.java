package items;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import common.BasePresenter;
import domain.models.Label;
import domain.usecase.GetLabels;
import io.reactivex.observers.DisposableObserver;

public class ItemListPresenter extends BasePresenter<ItemListContract.View> implements ItemListContract.Presenter {
    @Inject
    GetLabels getLabelsUseCase;

    @Inject
    ItemListPresenter(GetLabels getLabelsUseCase) {
        this.getLabelsUseCase = getLabelsUseCase;
    }

    @Override
    public void getDataLabels() {
        this.getLabelsUseCase.execute(new LabelListObserver(), null);
    }

    private void showLabels(List<Label> labelItems) {
        List<String> list = new ArrayList<>();
        for (Label item : labelItems) {
            list.add(item.getItemName());
        }
        this.getView().showData(list);
    }

    public void dispose() {
        this.getLabelsUseCase.dispose();
    }


    private class LabelListObserver extends DisposableObserver<List<Label>> {
        @Override
        public void onNext(List<Label> labelItems) {
            ItemListPresenter.this.showLabels(labelItems);
        }

        @Override
        public void onError(Throwable e) {
            ItemListPresenter.this.getView().showError();
        }

        @Override
        public void onComplete() {
        }
    }
}
