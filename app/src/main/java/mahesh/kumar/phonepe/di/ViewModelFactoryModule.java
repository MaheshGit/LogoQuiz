package mahesh.kumar.phonepe.di;

import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import mahesh.kumar.phonepe.utils.ViewModelProviderFactory;

@Module
public abstract class ViewModelFactoryModule {
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);
}
