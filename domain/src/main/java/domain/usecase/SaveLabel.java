package domain.usecase;


import javax.inject.Inject;

import domain.models.Label;
import domain.repository.LabelRepositoryInterface;
import io.reactivex.Observable;

/**
 * Save a new label in the database with an Unique Id
 **/
public class SaveLabel extends UseCase<Void, Label> {
    private LabelRepositoryInterface labelRepository;

    @Inject
    SaveLabel(LabelRepositoryInterface labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    Observable<Void> buildUseCaseObservable(Label label) {
        return labelRepository.saveLabel(label);
    }
}