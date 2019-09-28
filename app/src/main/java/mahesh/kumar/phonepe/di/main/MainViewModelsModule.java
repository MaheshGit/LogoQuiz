package mahesh.kumar.phonepe.di.main;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import mahesh.kumar.phonepe.di.ViewModelKey;
import mahesh.kumar.phonepe.ui.list.ListViewModel;

@Module
public abstract class MainViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel.class)
    public abstract ViewModel bindProfileViewModel(ListViewModel listViewModel);

/*    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    public abstract ViewModel bindPostsViewModel(PostsViewModel profileViewModel);*/
}
