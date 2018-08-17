package domain.models;

import android.graphics.Bitmap;

import java.util.List;


public class Scan {
    private int id;
    private String title;
    private String imagePath;
    private String imageName;
    private List<String> fields;
    private Bitmap bitmap;

    private List<String> values;

    public Scan(int id, String title, String imagePath) {
        this.id = id;
        this.title = title;
        this.imagePath = imagePath;

    }

    public Scan(int id, String title, String imagePath, String imageName, List<String> fields, List<String> values) {
        this.id=id;
        this.fields = fields;
        this.values = values;
        this.title = title;
        this.imagePath = imagePath;
        this.imageName = imageName;
    }

    public Scan(int id, String title, List<String> fieldList, List<String> valueList, String imagePath) {
        this.id = id;
        this.title = title;
        this.fields = fieldList;
        this.values = valueList;
        this.imagePath = imagePath;
    }

    public Scan(int id, String title, List<String> fieldList, List<String> valueList) {
        this.id = id;
        this.title = title;
        this.fields = fieldList;
        this.values = valueList;
    }

    public Scan(int id, String title, List<String> valueList, List<String> fieldList, Bitmap bitmap) {

        this.id =id;
        this.title = title;
         this.values = valueList;
         this.fields = fieldList;
         this.bitmap = bitmap;
    }


    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Scan(int[] images, List<String> labels, List<String> values) {
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
