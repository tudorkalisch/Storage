package domain.usecase;

import javax.inject.Inject;

import domain.models.Label;
import domain.repository.LabelRepositoryInterface;
import io.reactivex.Observable;

public class UpdateLabel extends UseCase<Void, Label> {
    private LabelRepositoryInterface labelRepository;

    @Inject
    public UpdateLabel(LabelRepositoryInterface labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    Observable<Void> buildUseCaseObservable(Label label) {
        return labelRepository.updateLabel(label);
    }
}
