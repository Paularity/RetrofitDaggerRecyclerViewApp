package com.paularity.axcd.retrofitdaggerapp.Services;

import com.paularity.axcd.retrofitdaggerapp.Api.JSONPlaceHolderApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService
{
    //Singleton Instance
    public static NetworkService mInstance;

    //Retrofit setup
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private Retrofit retrofit;

    private OkHttpClient.Builder client;
    private HttpLoggingInterceptor interceptor;

    //Retrofit
    private NetworkService()
    {
        interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }

    //Singleton Pattern
    public static NetworkService getInstance()
    {
        if(mInstance == null)
        {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    //Bind RetrofitInstance to Interface
    public JSONPlaceHolderApi getJSONApi()
    {
        return retrofit.create(JSONPlaceHolderApi.class);
    }

}
