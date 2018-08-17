package scans.edit;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import common.BasePresenter;
import domain.models.MlKit;
import domain.models.Scan;
import domain.usecase.GetScan;
import domain.usecase.GetScanLabelMlKit;
import domain.usecase.UpdateScan;
import io.reactivex.observers.DisposableObserver;
import scans.model.DrawRectangles;
import scans.model.ScanViewModel;
import timber.log.Timber;
import utils.Constants;

public class EditScanPresenter extends BasePresenter<EditScanContract.View> implements EditScanContract.Presenter {
    @Inject
    GetScan getScanUseCase;
    @Inject
    GetScanLabelMlKit getScanLabelMlKitUseCase;
    @Inject
    UpdateScan updateScan;

    @Inject
    EditScanPresenter(GetScan getScanUseCase, GetScanLabelMlKit mlKit, UpdateScan updateScan) {
        this.getScanUseCase = getScanUseCase;
        this.getScanLabelMlKitUseCase = mlKit;
        this.updateScan = updateScan;
    }

    public void getDataScan(String title) {
        this.getScanUseCase.execute(new ScanObserver(), title);
    }

    public Bitmap getImageFromGallery(int resultCode, int requestCode, Intent data, Context context) {
        Bitmap bitmap = null;
        if (requestCode == Constants.SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), data.getData());

                    } catch (IOException e) {
                        Timber.e(e);
                    }

                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                getView().onErrorMessage("Cancelled");
            }
        }
        return bitmap;
    }

    private String saveToInternalStorage(Bitmap bitmapImage, Context context) {
        ContextWrapper cw = new ContextWrapper(context.getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File mypath = new File(directory, bitmapImage.toString() + ".jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
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

    public void updateScan(ScanViewModel scanViewModel, Context context) {
        Scan scan = convertsTo(scanViewModel, context);
        this.updateScan.execute(new SaveScanObserver(), scan);
    }

    private Scan convertsTo(ScanViewModel scanViewModel, Context context) {
        return new Scan(scanViewModel.getId(), scanViewModel.getTitle(), saveToInternalStorage(scanViewModel.getBitmap(), context), scanViewModel.getBitmap().toString() + ".jpg",
                scanViewModel.getFieldList(), scanViewModel.getValueList());
    }

    private ScanViewModel convertTo(Scan scan) {
        return new ScanViewModel(scan.getId(), scan.getTitle(), scan.getFields(), scan.getValues(), loadImageFromStorage(scan.getImagePath(), scan.getImageName()));
    }

    private Bitmap loadImageFromStorage(String path,String name)
    {
        try {
            File f=new File(path, name);
            return BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    return null;
    }

    private void drawRectangles(List<MlKit> list){
        List<DrawRectangles> drawRectangles = convertFromList(list);
        getView().showRectangulars(drawRectangles);
    }

    private void showScan(Scan scan) {
        ScanViewModel scanViewModel = convertTo(scan);
        this.getView().showData(scanViewModel);
    }

    public void dispose() {
        this.getScanUseCase.dispose();
    }

    public void getDrawRectangles(Bitmap bitmap) {
        this.getScanLabelMlKitUseCase.execute(new GetScanMlKitObserver(), bitmap);
    }

    private List<DrawRectangles> convertFromList(List<MlKit> mlKitList) {
        List<DrawRectangles> drawRectanglesList = new ArrayList<>();
        for (MlKit mlKit : mlKitList) {
            drawRectanglesList.add(new DrawRectangles(mlKit.getPolygonBound(), mlKit.getMlkitText()));
        }
        return drawRectanglesList;
    }

    private class GetScanMlKitObserver extends DisposableObserver<List<MlKit>> {
        @Override
        public void onNext(List<MlKit> list) {
            EditScanPresenter.this.drawRectangles(list);
        }

        @Override
        public void onError(Throwable e) {
            getView().showError();
        }

        @Override
        public void onComplete() {

        }
    }

    private class ScanObserver extends DisposableObserver<Scan> {
        @Override
        public void onNext(Scan scan) {
            EditScanPresenter.this.showScan(scan);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
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
}
