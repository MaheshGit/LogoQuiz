package mahesh.kumar.phonepe.ui.list;


import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import io.reactivex.schedulers.Schedulers;
import mahesh.kumar.phonepe.network.GameService;
import mahesh.kumar.phonepe.network.model.Logo;
import mahesh.kumar.phonepe.utils.Resource;

public class ListViewModel extends ViewModel {
    private final GameService repoRepository;
    private MediatorLiveData repos;

    @Inject
    public ListViewModel(GameService repoRepository) {
        this.repoRepository = repoRepository;
    }

    public LiveData<Resource<List<Repo>>> observeRepos(){
        if(repos == null){

            repos = new MediatorLiveData();
            repos.setValue(Resource.loading(null));

            final LiveData<Resource<List<Logo>>> list = LiveDataReactiveStreams.
                    fromPublisher((Publisher<Resource<List<Logo>>>) repoRepository.getRepositories().onErrorReturn(new Function<Throwable, List<Repo>>() {
                        @Override
                        public List<Logo> apply(Throwable throwable) throws Exception {
                            ArrayList<Logo> repoArrayList = new ArrayList<>();
                            repoArrayList.add(repo);
                            return repoArrayList;
                        }
                    }).map(new Function<List<Repo>, Resource<List<Repo>>>() {
                        @Override
                        public Resource<List<Repo>> apply(List<Repo> repos) throws Exception {
                            if(repos.size() > 0){
                                if(repos.get(0).getId() == -1){
                                    return Resource.error("Something went wrong", null);
                                }
                            }
                            return Resource.success(repos);
                        }
                    }).subscribeOn(Schedulers.io()));

            repos.addSource(list, new Observer<Resource<List<Logo>>>() {
                @Override
                public void onChanged(Resource<List<Logo>> listResource) {
                    repos.setValue(listResource);
                    repos.removeSource(list);
                }
            });

        }
        return repos;
    }
}
