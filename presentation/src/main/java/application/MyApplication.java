package application;

import android.app.Application;
import android.content.Context;
import com.facebook.stetho.Stetho;

import injection.component.ApplicationComponent;
import injection.component.DaggerApplicationComponent;
import injection.module.ApplicationModule;

public class MyApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

}
