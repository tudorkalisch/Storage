package injection.module;

import android.app.Activity;
import android.arch.persistence.room.Room;
import java.util.concurrent.Executor;

import dagger.Module;
import dagger.Provides;
import data.local.label.LabelDAO;
import data.local.label.LabelDatabase;
import data.local.label.entities.mapper.LabelEntityDataMapper;
import data.repository.LocalLabelRepositoryImpl;
import domain.repository.LabelRepositoryInterface;

@Module
public class LabelRoomModule {
    private Activity mActivity;

    public LabelRoomModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    public LabelDAO getLabelDAO(LabelDatabase labelDatabase) {
        return labelDatabase.labelDAO();
    }

    @Provides
    public LabelDatabase getLabelDatabase() {
        return Room.databaseBuilder(mActivity.getApplicationContext(), LabelDatabase.class, "label.db").build();
    }

    @Provides
    public LabelEntityDataMapper getLabelMapper() {
        return new LabelEntityDataMapper();
    }

    @Provides
    public LabelRepositoryInterface getLocalLabelRepo(Executor executor, LabelEntityDataMapper labelEntityDataMapper, LabelDatabase labelDatabase) {
        return new LocalLabelRepositoryImpl(executor,labelEntityDataMapper, labelDatabase);
    }
}
