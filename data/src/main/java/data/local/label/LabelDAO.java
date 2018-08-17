package data.local.label;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import data.local.label.entities.LabelEntity;
import io.reactivex.Maybe;
import io.reactivex.Single;


@Dao
public interface LabelDAO {
    @Query("SELECT * FROM LabelEntity")
    Maybe<List<LabelEntity>> getLabels();

    @Query("SELECT * FROM LabelEntity WHERE title = :title")
    Single<LabelEntity> getLabel(String title);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLabel(LabelEntity labelEntity);

    @Update
    void update(LabelEntity labelEntity);
}
