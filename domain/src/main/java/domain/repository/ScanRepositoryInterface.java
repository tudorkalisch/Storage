package domain.repository;


import java.util.List;

import domain.models.Scan;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface ScanRepositoryInterface {

    Maybe<List<Scan>> getScans();

    Single<Scan> getScan(final String title);

    Observable<Void> saveScan(Scan scan);

    Observable<Void> updateScan (Scan scan);
}
