package mahesh.kumar.phonepe.di;

import android.app.Application;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import mahesh.kumar.phonepe.PhonePeApplication;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                AppModule.class,
                ActivityBuildersModule.class,
                ViewModelFactoryModule.class
        }
)
public interface AppComponent extends AndroidInjector<PhonePeApplication> {

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

}
