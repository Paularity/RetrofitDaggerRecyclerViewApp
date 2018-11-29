package com.paularity.axcd.retrofitdaggerapp.Dagger.Modules;

import android.content.Context;

import com.paularity.axcd.retrofitdaggerapp.Helpers.AuthInterceptor;
import com.paularity.axcd.retrofitdaggerapp.Helpers.CacheInterceptor;
import com.paularity.axcd.retrofitdaggerapp.Helpers.Utils;

import java.io.File;
import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {FragmentModule.class, HelperModule.class})
public class NetworkModule
{

    private static final String BASE_URL = "http://206.189.85.106";
    private static final String TOKEN = "Token a0db7645fdcd6893ad77eb0c7bc760fc694978e0";
//    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @Provides
    CacheInterceptor cacheInterceptor(Context context)
    {
        return new CacheInterceptor(context);
    }

    @Provides
    HttpLoggingInterceptor provideInterceptor()
    {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    OkHttpClient.Builder provideClient(HttpLoggingInterceptor log_interceptor, CacheInterceptor cacheInterceptor, Cache cache)
    {
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        client.networkInterceptors().add(cacheInterceptor.REWRITE_CACHE_CONTROL_INTERCEPTOR);

        client.addInterceptor(log_interceptor)
                     .addInterceptor(new AuthInterceptor(TOKEN));

        client.cache(cache);

        return client;
    }

    @Provides
    File provideHttpCacheDirectory(Context context)
    {
        return new File(context.getCacheDir(), "okhttp-reponses");
    }

    @Provides
    Cache provideCache(File httpCacheDirectory)
    {
        int cacheSize = 10 * 1024 * 1024; //10MB Cache
        return new Cache(httpCacheDirectory, cacheSize);
    }

    @Provides
    Retrofit provideRetrofit(OkHttpClient.Builder client)
    {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }

}
