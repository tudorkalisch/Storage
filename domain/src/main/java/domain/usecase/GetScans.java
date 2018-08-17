package domain.usecase;


import java.util.List;

import javax.inject.Inject;

import domain.models.Scan;
import domain.repository.ScanRepositoryInterface;
import io.reactivex.Observable;

/**
 * Get all the scans in a format used for the list in Scan fragment
 **/
public class GetScans extends UseCase<List<Scan>, Void> {

    private ScanRepositoryInterface scanRepository;

    @Inject
    GetScans(ScanRepositoryInterface scanRepository) {
        this.scanRepository = scanRepository;
    }

    @Override
    Observable<List<Scan>> buildUseCaseObservable(Void unused) {
        return scanRepository.getScans().toObservable();
    }
}
