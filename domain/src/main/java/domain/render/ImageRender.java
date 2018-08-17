package domain.render;

import android.graphics.Bitmap;

import domain.models.MlKit;

import java.util.List;

import io.reactivex.Observable;

public interface ImageRender {
    Observable<List<MlKit>> renderImage(Bitmap bitmap);
    
}
