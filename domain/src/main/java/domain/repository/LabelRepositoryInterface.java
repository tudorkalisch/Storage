package domain.repository;


import java.util.List;

import domain.models.Label;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface LabelRepositoryInterface {

    Single<Label> getLabel(String labelId);

    Observable<Void> saveLabel(Label label);

    Maybe<List<Label>> getLabels();

    Observable<Void> updateLabel(Label label);
}
