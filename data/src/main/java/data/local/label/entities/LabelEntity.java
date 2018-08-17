package data.local.label.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

@Entity
public class LabelEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    @TypeConverters({data.local.label.typeconverter.TypeConverters.class})
    private List<String> fields;

    public LabelEntity(int id, String title, List<String> fields) {
        this.id = id;
        this.title = title;
        this.fields = fields;
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

    public List<String> getFields() {
        return fields;
    }
}
