package labels.add;

import javax.inject.Inject;

import common.BasePresenter;
import domain.models.Label;
import domain.usecase.SaveLabel;
import labels.model.LabelViewModel;


public class AddLabelPresenter extends BasePresenter<AddLabelContract.View> implements AddLabelContract.Presenter {
    @Inject
    SaveLabel saveLabelUseCase;

    @Inject
    AddLabelPresenter(SaveLabel saveLabelUseCase) {
        this.saveLabelUseCase = saveLabelUseCase;
    }

    private Label convertTo(LabelViewModel labelViewModel) {
        return new Label(labelViewModel.getId(), labelViewModel.getItemName(), labelViewModel.getLabelList());
    }

    public void saveLabel(LabelViewModel labelViewModel) {
        Label label = convertTo(labelViewModel);
        this.saveLabelUseCase.execute(new SaveLabelObserver(), label);
    }

    private class SaveLabelObserver extends io.reactivex.observers.DisposableObserver<Void> {
        @Override
        public void onNext(Void aVoid) {

        }

        @Override
        public void onError(Throwable e) {
            getView().showError();
        }

        @Override
        public void onComplete() {
            getView().showSuccess();
        }
    }

}
