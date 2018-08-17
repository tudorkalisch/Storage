package mlkit.model;

import android.graphics.Rect;

public class MlkitViewModel {
    private Rect polygonBound;
    private String mlkitText;

    public MlkitViewModel( Rect polygonBound, String mlkitText) {
        this.polygonBound = polygonBound;
        this.mlkitText = mlkitText;
    }

    public Rect getPolygonBound() {
        return polygonBound;
    }

    public void setPolygonBound(Rect polygonBound) {
        this.polygonBound = polygonBound;
    }

    public String getMlkitText() {
        return mlkitText;
    }

    public void setMlkitText(String mlkitText) {
        this.mlkitText = mlkitText;
    }
}