package domain.usecase;


import javax.inject.Inject;

import domain.models.Scan;
import domain.repository.ScanRepositoryInterface;
import io.reactivex.Observable;

/**
 * Get a Scan object from data using the Id
 **/
public class GetScan extends UseCase<Scan, String> {
    private ScanRepositoryInterface scanRepository;

    @Inject
    GetScan(ScanRepositoryInterface scanRepository) {
        this.scanRepository = scanRepository;
    }

    @Override
    Observable<Scan> buildUseCaseObservable(String title) {
        return this.scanRepository.getScan(title).toObservable();
    }
}

