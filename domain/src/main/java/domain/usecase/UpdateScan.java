package domain.usecase;



import javax.inject.Inject;

import domain.models.Scan;
import domain.repository.ScanRepositoryInterface;
import io.reactivex.Observable;

public class UpdateScan extends UseCase<Void, Scan> {
    private ScanRepositoryInterface scanRepository;

    @Inject
    public UpdateScan(ScanRepositoryInterface scanRepository) {
        this.scanRepository = scanRepository;
    }

    @Override
    Observable<Void> buildUseCaseObservable(Scan scan) {
        return scanRepository.updateScan(scan);
    }

}
