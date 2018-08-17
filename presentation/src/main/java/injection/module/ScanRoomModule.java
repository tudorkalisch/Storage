package injection.module;

import android.app.Activity;
import android.arch.persistence.room.Room;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dagger.Module;
import dagger.Provides;
import data.local.scan.ScanDAO;
import data.local.scan.ScanDatabase;
import data.local.scan.entities.mapper.ScanEntityDataMapper;
import data.repository.LocalScanRepositoryImpl;
import domain.repository.ScanRepositoryInterface;

@Module
public class ScanRoomModule {
    private Activity mActivity;

    public ScanRoomModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    public ScanDAO getScanDAO(ScanDatabase scanDatabase) {
        return scanDatabase.scanDAO();
    }

    @Provides
    public ScanDatabase getScanDatabase() {
        return Room.databaseBuilder(mActivity.getApplicationContext(), ScanDatabase.class, "scan.db").build();
    }

    @Provides
    public Executor getExecutor() {
        return Executors.newFixedThreadPool(2);
    }

    @Provides
    public ScanEntityDataMapper getMapper() {
        return new ScanEntityDataMapper();
    }

    @Provides
    public ScanRepositoryInterface getLocalRepo(Executor executor, ScanEntityDataMapper entityDataMapper, ScanDatabase scanDatabase) {
        return new LocalScanRepositoryImpl(executor, entityDataMapper, scanDatabase);
    }
}
