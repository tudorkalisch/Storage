package data.local.scan;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import data.local.scan.entities.ScanEntity;


@Database(entities = ScanEntity.class, version = 2, exportSchema = false)
public abstract class ScanDatabase extends RoomDatabase {
    public abstract ScanDAO scanDAO();
}
