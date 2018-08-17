package domain.usecase;


import javax.inject.Inject;

import domain.models.Scan;
import domain.repository.ScanRepositoryInterface;
import io.reactivex.Observable;

/**
 * Save a new label in the database with an Unique Id
 **/
public class SaveScan extends UseCase<Void, Scan> {
    private ScanRepositoryInterface scanRepository;

    @Inject
    SaveScan(ScanRepositoryInterface scanRepository) {
        this.scanRepository = scanRepository;
    }

    @Override
    Observable<Void> buildUseCaseObservable(Scan scan) {
        return this.scanRepository.saveScan(scan);
    }
}
