package mahesh.kumar.phonepe.di.main;
import dagger.Module;
import dagger.Provides;
import mahesh.kumar.phonepe.network.GameService;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @Provides
    static GameService provideRepoService(Retrofit retrofit){
        return retrofit.create(GameService.class);
    }

/*    @Provides
    static LogoAdapter provideAdapter(){
        return new LogoAdapter();
    }*/
}
