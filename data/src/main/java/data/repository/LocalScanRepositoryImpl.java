package data.repository;


import java.util.List;
import java.util.concurrent.Executor;

import data.local.scan.ScanDAO;
import data.local.scan.ScanDatabase;
import data.local.scan.entities.ScanEntity;
import data.local.scan.entities.mapper.ScanEntityDataMapper;
import domain.models.Scan;
import domain.repository.ScanRepositoryInterface;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public class LocalScanRepositoryImpl implements ScanRepositoryInterface {
    private ScanDAO scanDAO;
    private Executor executor;
    private ScanEntityDataMapper scanEntityDataMapper;

    public LocalScanRepositoryImpl(Executor executor, ScanEntityDataMapper entityDataMapper, ScanDatabase scanDatabase) {
        this.scanDAO = scanDatabase.scanDAO();
        this.executor = executor;
        this.scanEntityDataMapper = entityDataMapper;
    }

    public Maybe<List<Scan>> getScans() {
        return scanDAO.getScans()
                .map(scanEntities -> scanEntityDataMapper.trasnfrom(scanEntities));
    }

    @Override
    public Single<Scan> getScan(String title) {
        return scanDAO.getScan(title)
                .map(scanEntity -> scanEntityDataMapper.trasnfrom(scanEntity));
    }

    @Override
    public Observable<Void> saveScan(Scan scan) {
        ScanEntity scanEntity = this.scanEntityDataMapper.transform(scan);
        executor.execute(() -> scanDAO.insertScan(scanEntity));
        return Observable.empty();
    }

    @Override
    public Observable<Void> updateScan(Scan scan) {
        ScanEntity scanEntity = this.scanEntityDataMapper.transform(scan);
        executor.execute(() -> scanDAO.update(scanEntity));
        return Observable.empty();
    }

}
