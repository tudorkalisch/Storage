package data.local.label.entities.mapper;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import data.local.label.entities.LabelEntity;
import domain.models.Label;

public class LabelEntityDataMapper {
    @Inject
    public LabelEntityDataMapper() {
    }

    public Label trasnfrom(LabelEntity labelEntity) {
        return new Label(labelEntity.getId(), labelEntity.getTitle(), labelEntity.getFields());
    }

    public LabelEntity transform(Label label) {
        LabelEntity labelEntity = null;
        if (label != null) {
            labelEntity = new LabelEntity(label.getId(), label.getItemName(), label.getLabels());
        }
        return labelEntity;
    }

    public List<Label> trasnfrom(List<LabelEntity> labelEntitys) {
        List<Label> labels = new ArrayList<>();
        for (LabelEntity labelEntity : labelEntitys) {
            labels.add(trasnfrom(labelEntity));
        }
        return labels;
    }
}
