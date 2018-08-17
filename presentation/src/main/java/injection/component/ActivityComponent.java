package injection.component;

import dagger.Component;
import injection.annotation.PerActivity;
import injection.module.ActivityModule;
import injection.module.LabelRoomModule;
import injection.module.MlKitModule;
import injection.module.ScanRoomModule;
import items.ItemListActivity;
import labels.add.AddLabelActivity;
import labels.edit.EditLabelActivity;
import main.MainActivity;
import main.fragments.LabelsFragment;
import main.fragments.ScansFragment;
import scans.add.AddScanActivity;
import scans.edit.EditScanActivity;

@PerActivity

@Component(dependencies = ApplicationComponent.class, modules = { ScanRoomModule.class, LabelRoomModule.class, MlKitModule.class, ActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(AddScanActivity addScanActivity);

    void inject(EditScanActivity editScanActivity);

    void inject(AddLabelActivity addLabelActivity);

    void inject(EditLabelActivity editLabelActivity);

    void inject(ItemListActivity itemListActivity);

    void inject(ScansFragment scansFragment);

    void inject(LabelsFragment labelsFragment);
}
