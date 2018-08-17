package domain.usecase;


import java.util.List;

import javax.inject.Inject;

import domain.models.Label;
import domain.repository.LabelRepositoryInterface;
import io.reactivex.Observable;

/**
 * Get all the labels in a format used for the list in Label fragment
 **/
public class GetLabels extends UseCase<List<Label>, Void> {
    private LabelRepositoryInterface labelRepository;

    @Inject
    GetLabels(LabelRepositoryInterface labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    Observable<List<Label>> buildUseCaseObservable(Void aVoid) {
        return this.labelRepository.getLabels().toObservable();
    }
}
