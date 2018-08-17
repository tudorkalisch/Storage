package injection.component;

import android.app.Application;
import android.content.Context;


import javax.inject.Singleton;

import dagger.Component;
import injection.annotation.ApplicationContext;
import injection.module.ApplicationModule;
import application.MyApplication;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MyApplication myApplication);

    @ApplicationContext
    Context context();

    Application application();

}
