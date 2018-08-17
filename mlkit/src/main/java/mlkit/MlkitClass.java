package mlkit;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector;

import java.util.ArrayList;
import java.util.List;

import domain.models.MlKit;
import domain.render.ImageRender;
import io.reactivex.Observable;
import mlkit.model.MlkitViewModel;

public class MlkitClass implements ImageRender {
    private List<MlKit> modelBlocks;

    public MlkitClass() {
        this.modelBlocks = new ArrayList<>();
    }

    @Override
    public Observable<List<MlKit>> renderImage(Bitmap bitmap) {
        this.detectTxt(bitmap);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Observable.just(modelBlocks);
    }

    private MlKit convertTo(MlkitViewModel mlkitViewModel) {
        return new MlKit(mlkitViewModel.getPolygonBound(), mlkitViewModel.getMlkitText());
    }

    private void detectTxt(Bitmap imageBitmap) {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(imageBitmap);
        FirebaseVisionTextDetector detector = FirebaseVision.getInstance().getVisionTextDetector();
        detector.detectInImage(image).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
            @Override
            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                processTxt(firebaseVisionText);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }

    private void processTxt(FirebaseVisionText text) {

        List<FirebaseVisionText.Block> blocks = text.getBlocks();
        if (blocks.size() == 0) {
            return;
        }
        for (FirebaseVisionText.Block block : text.getBlocks()) {


            for (int i=0;i<block.getLines().size();i++) {
                Rect boundingBox = block.getLines().get(i).getBoundingBox();
                String txt = block.getLines().get(i).getText();
                MlkitViewModel mlkitViewModel = new MlkitViewModel(boundingBox, txt);
                modelBlocks.add(convertTo(mlkitViewModel));
            }
        }

    }

}

