package com.paularity.axcd.retrofitdaggerapp.Helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class CacheInterceptor
{
    private Context context;

    public CacheInterceptor(Context context)
    {
        this.context = context;
    }

    public Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor()
    {
        @Override
        public Response intercept(Chain chain) throws IOException
        {
            Response originalResponse = chain.proceed(chain.request());
            if ( Utils.isNetworkAvailable(context) )
            {
//                int maxAge = 60; // read from cache for 1 minute
                int maxAge = 0; // read from cache for 0 minute
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            }
            else
            {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };

}
