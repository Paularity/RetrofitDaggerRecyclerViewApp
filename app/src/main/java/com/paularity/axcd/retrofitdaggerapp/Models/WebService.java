package com.paularity.axcd.retrofitdaggerapp.Models;

import com.paularity.axcd.retrofitdaggerapp.Api.CommitmentApi;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WebService
{
    @Inject
    Retrofit myRetro;

    @Inject
    Commitment commitment;

    @Inject
    Result result;

    public ArrayList<Result> resultsArr;

    public ArrayList<Result> ResultList()
    {
        myRetro.create(CommitmentApi.class) //get Data from client
                .getCommitment()
                .enqueue(new Callback<Commitment>()
                {
                    @Override
                    public void onResponse(Call<Commitment> call, Response<Commitment> response)
                    {
                        commitment = response.body();

                        ArrayList<Result> results = new ArrayList<>();



                        for(int i = 0; i < commitment.getCount(); i++)
                        {
                            results.add( commitment.getResults().get(i) );
                        }

                        resultsArr = results;
                    }

                    @Override
                    public void onFailure(Call<Commitment> call, Throwable t)
                    {
                        t.printStackTrace();
                    }
                });

        return resultsArr;
    }

}
