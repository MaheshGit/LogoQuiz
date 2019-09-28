package mahesh.kumar.phonepe.di;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mahesh.kumar.phonepe.R;
import mahesh.kumar.phonepe.utils.Constants;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Singleton
    @Provides
    static Context getApplicationContext(@NonNull Application application){
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(){
        return new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static RequestOptions provideRequestOptions(){
        return RequestOptions.placeholderOf(R.drawable.white_background)
                .error(R.drawable.white_background);
    }

    @Singleton
    @Provides
    static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions){
        return Glide.with(application).setDefaultRequestOptions(requestOptions);
    }
}
