package domain.usecase;


import java.util.List;

import javax.inject.Inject;

import domain.models.MlKit;
import domain.repository.ScanRepository;
import io.reactivex.Observable;

public class SetScanLabelMlKit extends UseCase<List<MlKit>, Void> {
    @Inject
    ScanRepository scanRepository;

    @Inject
    SetScanLabelMlKit(ScanRepository scanRepository) {
        this.scanRepository = scanRepository;
    }

    @Override
    Observable<List<MlKit>> buildUseCaseObservable(Void aVoid) {
        return this.scanRepository.setMlkitFields();
    }
}