package mahesh.kumar.phonepe.di.main;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import mahesh.kumar.phonepe.ui.list.GameFragment;

@Module
public abstract class MainFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract GameFragment contributeProfileFragment();
}
