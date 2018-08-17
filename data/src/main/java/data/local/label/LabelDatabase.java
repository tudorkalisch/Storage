package data.local.label;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import data.local.label.entities.LabelEntity;


@Database(entities = LabelEntity.class, version = 1, exportSchema = false)
public abstract class LabelDatabase extends RoomDatabase {
    public abstract LabelDAO labelDAO();
}
