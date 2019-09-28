package mahesh.kumar.phonepe.di;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import mahesh.kumar.phonepe.di.main.MainFragmentBuildersModule;
import mahesh.kumar.phonepe.di.main.MainModule;
import mahesh.kumar.phonepe.di.main.MainViewModelsModule;
import mahesh.kumar.phonepe.ui.main.MainActivity;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = {
            MainFragmentBuildersModule.class,
            MainViewModelsModule.class,
            MainModule.class
    })
    abstract MainActivity contributeMainActivity();
}
