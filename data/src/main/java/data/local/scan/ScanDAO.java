package data.local.scan;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import data.local.scan.entities.ScanEntity;
import io.reactivex.Maybe;
import io.reactivex.Single;


@Dao
public interface ScanDAO {
    @Query("SELECT * FROM ScanEntity")
    Maybe<List<ScanEntity>> getScans();

    @Query("SELECT * FROM ScanEntity WHERE title = :title")
    Single<ScanEntity> getScan(String title);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertScan(ScanEntity scan);

    @Update
    void update(ScanEntity scanEntity);
}
