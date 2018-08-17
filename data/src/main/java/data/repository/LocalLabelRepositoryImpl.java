package data.repository;


import java.util.List;
import java.util.concurrent.Executor;

import data.local.label.LabelDAO;
import data.local.label.LabelDatabase;
import data.local.label.entities.LabelEntity;
import data.local.label.entities.mapper.LabelEntityDataMapper;
import domain.models.Label;
import domain.repository.LabelRepositoryInterface;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public class LocalLabelRepositoryImpl implements LabelRepositoryInterface {
    private LabelDAO labelDAO;
    private LabelEntityDataMapper labelEntityDataMapper;
    private Executor executor;

    public LocalLabelRepositoryImpl(Executor executor, LabelEntityDataMapper labelEntityDataMapper, LabelDatabase labelDatabase) {
        this.executor = executor;
        this.labelDAO = labelDatabase.labelDAO();
        this.labelEntityDataMapper = labelEntityDataMapper;
    }

    @Override
    public Observable<Void> saveLabel(Label label) {
        LabelEntity labelEntity = this.labelEntityDataMapper.transform(label);
        executor.execute(() -> labelDAO.insertLabel(labelEntity));
        return Observable.empty();
    }

    @Override
    public Single<Label> getLabel(String labelTitle) {
        return labelDAO.getLabel(labelTitle)
                .map(labelEntity -> labelEntityDataMapper.trasnfrom(labelEntity));
    }

    @Override
    public Maybe<List<Label>> getLabels() {
        return labelDAO.getLabels()
                .map(labelEntities -> labelEntityDataMapper.trasnfrom(labelEntities));
    }

    @Override
    public Observable<Void> updateLabel(Label label) {
        LabelEntity labelEntity = this.labelEntityDataMapper.transform(label);
        executor.execute(() -> labelDAO.update(labelEntity));
        return Observable.empty();
    }
}
