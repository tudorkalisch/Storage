package data.local.scan.entities.mapper;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import data.local.scan.entities.ScanEntity;
import domain.models.Scan;

public class ScanEntityDataMapper {
    @Inject
    public ScanEntityDataMapper() {
    }

    public Scan trasnfrom(ScanEntity scanEntity) {
        Scan scan = null;
        if (scanEntity != null) {
            scan = new Scan(scanEntity.getId(), scanEntity.getTitle(), scanEntity.getImagePath(), scanEntity.getImageName(), scanEntity.getFields(), scanEntity.getValues());
        }
        return scan;
    }

    public ScanEntity transform(Scan scan) {
        ScanEntity scanEntity = null;
        if (scan != null) {
            scanEntity = new ScanEntity(scan.getId(),scan.getTitle(), scan.getImagePath(), scan.getFields(), scan.getValues(), scan.getImageName());
        }
        return scanEntity;
    }

    public List<Scan> trasnfrom(List<ScanEntity> scanEntities) {
        List<Scan> scans = new ArrayList<>();
        for (ScanEntity scanEntity : scanEntities) {
            scans.add(trasnfrom(scanEntity));
        }
        return scans;
    }
}
