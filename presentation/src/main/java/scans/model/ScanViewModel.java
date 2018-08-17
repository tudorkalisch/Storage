package scans.model;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class ScanViewModel {
    private int id;
    private String title;
    private Bitmap bitmap;
    private List<String> fieldList;
    private List<String> valueList;

    public ScanViewModel() {
        this.fieldList = new ArrayList<>();
        this.valueList = new ArrayList<>();
    }

    public ScanViewModel (int id, String title, List<String> fields
            , List<String> values, Bitmap bitmap){
        this.id = id;
        this.title = title;
        this.fieldList = fields;
        this.valueList = values;
        this.bitmap = bitmap;

    }

    public ScanViewModel(String title, List<String> fields, List<String> values) {
        this.title = title;
        this.fieldList = fields;
        this.valueList = values;
    }

    public ScanViewModel(String title, List<String> fields, List<String> values,Bitmap bitmap) {
        this.title = title;
        this.fieldList = fields;
        this.valueList = values;
        this.bitmap=bitmap;
    }

    public ScanViewModel(Bitmap bitmap) {
        this.bitmap = bitmap;
    }


    public ScanViewModel(int id, String title, List<String> fields, List<String> values){
        this.id = id;
        this.title = title;
        this.fieldList = fields;
        this.valueList = values;

    }

    public List<String> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<String> fieldList) {
        this.fieldList = fieldList;
    }

    public List<String> getValueList() {
        return valueList;
    }

    public void setValueList(List<String> valueList) {
        this.valueList = valueList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int size() {
        if (fieldList.size() == valueList.size()) {
            return fieldList.size();
        } else {
            throw new IndexOutOfBoundsException("Size of value and field are not the same, check add");
        }
    }

    public void setValue(int position, String value) {
        valueList.set(position, value);
    }

    public String getField(int position) {
        return this.fieldList.get(position);
    }

    public String getValue(int position) {
        return this.valueList.get(position);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
