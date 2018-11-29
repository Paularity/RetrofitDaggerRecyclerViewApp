package com.paularity.axcd.retrofitdaggerapp.Api;

import com.paularity.axcd.retrofitdaggerapp.Models.Commitment;
import com.paularity.axcd.retrofitdaggerapp.Models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CommitmentApi
{
    @GET("/api/commitment/")
    public Call<Commitment> getCommitment();

    @FormUrlEncoded
    @POST("/api/commitment/")
    Call<Void> insertData(@Field("id") String id, @Field("title") String title, @Field("description") String description);

    @FormUrlEncoded
    @PUT("api/commitment/{id}/")
    Call<Void> updateById(@Path("id") int id, @Field("title") String title, @Field("description") String description); //{id} will be replace by @Path("id") int id

    @DELETE("api/commitment/{id}/")
    Call<Void> deleteById(@Path("id") int id); //{id} will be replace by @Path("id") int id
}
