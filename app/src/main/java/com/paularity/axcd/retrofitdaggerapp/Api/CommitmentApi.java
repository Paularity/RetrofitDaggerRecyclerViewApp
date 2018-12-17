package com.paularity.axcd.retrofitdaggerapp.Api;

import com.paularity.axcd.retrofitdaggerapp.Models.Commitment;
import com.paularity.axcd.retrofitdaggerapp.Models.Post;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CommitmentApi
{
    @POST("/api/commitment")
    Single<Commitment> getCommitment( @Query("page") int page );
    //Single<Commitment> getCommitment() (@Path("page_id") int page_id);

    @FormUrlEncoded
    @POST("/api/commitment/")
    Completable insertData(@Field("id") String id, @Field("title") String title, @Field("description") String description);

    @FormUrlEncoded
    @PUT("api/commitment/{id}/")
    Completable updateById(@Path("id") int id, @Field("title") String title, @Field("description") String description); //{id} will be replace by @Path("id") int id

    @DELETE("api/commitment/{id}/")
    Completable deleteById(@Path("id") int id); //{id} will be replace by @Path("id") int id
}
