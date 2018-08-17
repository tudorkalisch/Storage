package data.local.scan.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;



import java.util.List;

import data.local.scan.entities.mapper.TypeConverterss;

@Entity
public class ScanEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String imagePath;
    private String imageName;
    @TypeConverters(TypeConverterss.class)
    private List<String> fields;
    @TypeConverters(TypeConverterss.class)
    private List<String> values;

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }


    public ScanEntity(int id, String title, String imagePath, List<String> fields, List<String> values, String imageName) {
       this.id=id;
        this.values = values;
        this.fields = fields;
        this.title = title;
        this.imagePath = imagePath;
        this.imageName = imageName;
    }

    public List<String> getFields() {
        return fields;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public String getImageName() {
        return imageName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
