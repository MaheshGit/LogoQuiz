package mahesh.kumar.phonepe;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import mahesh.kumar.phonepe.di.DaggerAppComponent;

public class PhonePeApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends dagger.android.support.DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
