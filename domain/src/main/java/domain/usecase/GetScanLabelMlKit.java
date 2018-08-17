package domain.usecase;

import android.graphics.Bitmap;

import java.util.List;

import javax.inject.Inject;

import domain.models.MlKit;
import domain.render.ImageRender;
import io.reactivex.Observable;


public class GetScanLabelMlKit extends UseCase<List<MlKit>, Bitmap> {
    private ImageRender render;
    @Inject
    GetScanLabelMlKit(ImageRender render){
        this.render = render;
    }

    @Override
    protected Observable<List<MlKit>> buildUseCaseObservable(Bitmap bitmap) {
        return render.renderImage(bitmap);
    }

}
