package domain.usecase;


import javax.inject.Inject;

import domain.models.Label;
import domain.repository.LabelRepositoryInterface;
import io.reactivex.Observable;

/**
 * Get a Label object from data using the Id
 **/

public class GetLabel extends UseCase<Label, String> {
    private LabelRepositoryInterface labelRepository;

    @Inject
    GetLabel(LabelRepositoryInterface labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    Observable<Label> buildUseCaseObservable(String itemName) {
        return this.labelRepository.getLabel(itemName).toObservable();
    }
}
