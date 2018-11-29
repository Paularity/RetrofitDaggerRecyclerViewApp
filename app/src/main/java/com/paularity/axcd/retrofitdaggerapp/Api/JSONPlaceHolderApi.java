package com.paularity.axcd.retrofitdaggerapp.Api;

import com.paularity.axcd.retrofitdaggerapp.Models.Post;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi
{
    @GET("/posts/{id}")
    public Call<Post> getPostWithID(@Path("id") int id); //{id} will be replace by @Path("id") int id
}
