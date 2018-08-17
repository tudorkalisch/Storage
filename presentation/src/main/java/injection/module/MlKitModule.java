package injection.module;

import dagger.Module;
import dagger.Provides;
import domain.render.ImageRender;
import mlkit.MlkitClass;

@Module
public class MlKitModule {
    @Provides
    public ImageRender getMlKit() {
        return new MlkitClass();
    }
}
