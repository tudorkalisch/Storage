package labels.edit;


import javax.inject.Inject;

import common.BasePresenter;
import domain.models.Label;
import domain.usecase.GetLabel;
import domain.usecase.UpdateLabel;
import io.reactivex.observers.DisposableObserver;
import labels.model.LabelViewModel;

public class EditLabelPresenter extends BasePresenter<EditLabelContract.View> implements EditLabelContract.Presenter {
    @Inject
    GetLabel getLabelUseCase;
    @Inject
    UpdateLabel updateLabel;

    @Inject
    EditLabelPresenter(GetLabel getLabel, UpdateLabel updateLabel) {
        this.getLabelUseCase = getLabel;
        this.updateLabel = updateLabel;
    }


    private void showLabel(Label label) {
        LabelViewModel labelViewModel = convertFrom(label);
        this.getView().showData(labelViewModel);

    }

    private Label convertTo(LabelViewModel labelViewModel) {
        return new Label(labelViewModel.getId(), labelViewModel.getItemName(), labelViewModel.getLabelList());
    }

    private LabelViewModel convertFrom(Label label) {
        return new LabelViewModel(label.getId(), label.getItemName(), label.getLabels());
    }

    public void getDataLabel(String itemName) {
        this.getLabelUseCase.execute(new LabelObserver(), itemName);
    }

    public void saveLabel(LabelViewModel labelViewModel) {
        Label label = convertTo(labelViewModel);
        this.updateLabel.execute(new SaveLabelObserver(), label);
    }

    public void disposeGetLabel() {
        this.getLabelUseCase.dispose();
    }

    public void disposeSaveLabel() {
        this.updateLabel.dispose();
    }

    private class SaveLabelObserver extends DisposableObserver<Void> {
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

    private class LabelObserver extends DisposableObserver<Label> {
        @Override
        public void onNext(Label label) {
            EditLabelPresenter.this.showLabel(label);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }


}
