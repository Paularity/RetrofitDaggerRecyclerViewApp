package com.paularity.axcd.retrofitdaggerapp.Helpers;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor
{
    private String token;

    public AuthInterceptor(String token)
    {
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Request request = chain.request();
        Request auth = request.newBuilder()
                .header("Authorization", token)
                .build();

        return chain.proceed(auth);
    }
}
