package scans.add;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import common.BasePresenter;
import domain.models.Label;
import domain.models.MlKit;
import domain.models.Scan;
import domain.usecase.GetLabel;
import domain.usecase.GetScanLabelMlKit;
import domain.usecase.SaveScan;
import io.reactivex.observers.DisposableObserver;
import scans.model.DrawRectangles;
import scans.model.ScanViewModel;

public class AddScanPresenter extends BasePresenter<AddScanContract.View> implements AddScanContract.Presenter {
    @Inject
    SaveScan saveScanUseCase;
    @Inject
    GetLabel getLabelUseCase;
    @Inject
    GetScanLabelMlKit getScanLabelMlKitUseCase;

    @Inject
    AddScanPresenter(SaveScan saveScanUseCase, GetLabel getLabelUseCase,
                     GetScanLabelMlKit getScanLabelMlKitUseCase) {
        this.getLabelUseCase = getLabelUseCase;
        this.saveScanUseCase = saveScanUseCase;
        this.getScanLabelMlKitUseCase = getScanLabelMlKitUseCase;
    }

    private String saveToInternalStorage(Bitmap bitmapImage, Context context) {
        ContextWrapper cw = new ContextWrapper(context.getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File mypath = new File(directory, bitmapImage.toString() + ".jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 30, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert fos != null;
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    private Scan convertTo(ScanViewModel scanViewModel, Context context) {
        return new Scan(scanViewModel.getId(), scanViewModel.getTitle(), saveToInternalStorage(scanViewModel.getBitmap(), context), scanViewModel.getBitmap().toString() + ".jpg",
                scanViewModel.getFieldList(), scanViewModel.getValueList());
    }


    private ScanViewModel convertFrom(Label label) {
        String id = "";
        List<String> values = new ArrayList<>();
        for (int i = 0; i < label.getLabels().size(); i++) {
            values.add("");
        }
        List<String> fields = new ArrayList<>(label.getLabels());

        return new ScanViewModel(id, fields, values);
    }

    private List<DrawRectangles> convertFromList(List<MlKit> mlKitList) {
        List<DrawRectangles> drawRectanglesList = new ArrayList<>();
        for (MlKit mlKit : mlKitList) {
            drawRectanglesList.add(new DrawRectangles(mlKit.getPolygonBound(), mlKit.getMlkitText()));
        }
        return drawRectanglesList;
    }

    public void getDataLabel(String itemName) {
        this.getLabelUseCase.execute(new LabelObserver(), itemName);
    }

    public void getDrawRectangles(Bitmap bitmap) {
        this.getScanLabelMlKitUseCase.execute(new GetScanMlKitObserver(), bitmap);
    }

    public void addScan(ScanViewModel scanViewModel, Context context) {
        Scan scan = convertTo(scanViewModel, context);
        saveScanUseCase.execute(new SaveScanObserver(), scan);
    }


    public void dispose() {
        this.saveScanUseCase.dispose();
        this.getScanLabelMlKitUseCase.dispose();
        this.getLabelUseCase.dispose();
    }

    private void showRectangulars(List<MlKit> mlKitList) {
        List<DrawRectangles> drawRectangles = convertFromList(mlKitList);
        this.getView().showRectangulars(drawRectangles);
    }

    private void showLabel(Label label) {
        ScanViewModel scanViewModel = convertFrom(label);
        this.getView().showData(scanViewModel);
    }

    private class SaveScanObserver extends DisposableObserver<Void> {
        @Override
        public void onNext(Void aVoid) {
        }

        @Override
        public void onError(Throwable e) {
            getView().showError();
        }

        @Override
        public void onComplete() {
            getView().showSuccess();
        }
    }

    private class GetScanMlKitObserver extends DisposableObserver<List<MlKit>> {
        @Override
        public void onNext(List<MlKit> list) {
            AddScanPresenter.this.showRectangulars(list);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }

    private class LabelObserver extends DisposableObserver<Label> {
        @Override
        public void onNext(Label label) {
            AddScanPresenter.this.showLabel(label);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }


}
