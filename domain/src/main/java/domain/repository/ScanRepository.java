package domain.repository;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import domain.models.MlKit;
import domain.models.Scan;
import domain.models.ScanItem;
import io.reactivex.Observable;
import timber.log.Timber;


public class ScanRepository {


    @Inject
    ScanRepository() {

    }

    public Observable<List<MlKit>> setMlkitFields() {
        List<MlKit> list=new ArrayList<>();
        Rect rect = new Rect(200, 100, 200, 300);
        String text = "Afiseaza5";
        Rect rect1 = new Rect(350, 300, 600, 550);
        String text2 = "Afiseaza6";
        MlKit mlKit=new MlKit(rect,text);
        MlKit mlKit2=new MlKit(rect1,text2);
        list.add(mlKit);
        list.add(mlKit2);
        return Observable.just(list);
    }

    public Observable<List<ScanItem>> getScans() {
        int[] images = {1, 2, 2, 31, 232, 13,};
        ScanItem s1 = new ScanItem(1, images, "Title 1", "7/10/2018");
        ScanItem s2 = new ScanItem(2, images, "Title 1", "7/10/2018");
        ScanItem s3 = new ScanItem(3, images, "Title 1", "7/10/2018");
        ScanItem s4 = new ScanItem(4, images, "Title 1", "7/10/2018");
        ScanItem s5 = new ScanItem(5, images, "Title 1", "7/10/2018");
        ScanItem s6 = new ScanItem(6, images, "Title 1", "7/10/2018");
        ScanItem s7 = new ScanItem(7, images, "Title 1", "7/10/2018");
        ScanItem s8 = new ScanItem(8, images, "Title 1", "7/10/2018");
        List<ScanItem> scanItems = new ArrayList<>();
        scanItems.add(s1);
        scanItems.add(s2);
        scanItems.add(s3);
        scanItems.add(s4);
        scanItems.add(s5);
        scanItems.add(s6);
        scanItems.add(s7);
        scanItems.add(s8);
        return Observable.just(scanItems);
    }

    public Observable<Void> saveScan(Scan scan) {
        Timber.i("Add scan sent to Data");
        return Observable.empty();
    }
}
