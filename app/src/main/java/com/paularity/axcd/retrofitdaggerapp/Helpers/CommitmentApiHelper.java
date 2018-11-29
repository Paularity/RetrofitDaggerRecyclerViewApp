package com.paularity.axcd.retrofitdaggerapp.Helpers;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.paularity.axcd.retrofitdaggerapp.Adapters.CommitmentRecyclerViewAdapter;
import com.paularity.axcd.retrofitdaggerapp.Api.CommitmentApi;
import com.paularity.axcd.retrofitdaggerapp.Dagger.MyApplication;
import com.paularity.axcd.retrofitdaggerapp.Models.Commitment;
import com.paularity.axcd.retrofitdaggerapp.Models.Result;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.view.View.GONE;

public class CommitmentApiHelper
{
    @Inject
    Retrofit myRetro;

    @Inject
    Commitment commitment;

    @Inject
    Result result;

    private Context context;

    public CommitmentApiHelper(Context context)
    {
        ((MyApplication)context).getComponent().inject(this); //Dagger injection
        this.context = context;
    }

    public void getResponseCode(String code, String TAG, String ACTION_MESSAGE)
    {
        if(code.equals("404"))
        {
            Toast.makeText(context, TAG + " not found.", Toast.LENGTH_SHORT).show();
        }
        else if(Integer.parseInt(code) >= 200 || Integer.parseInt(code) < 300)
        {
            Toast.makeText(context, TAG + " has been successfully " + ACTION_MESSAGE, Toast.LENGTH_SHORT).show();
        }
        else if(code.equals("500"))
        {
            Toast.makeText(context, "Server is broken.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }

    }

    public void getResultList(final Context context, final ArrayList<Result> results, final RecyclerView recyclerView, final CommitmentRecyclerViewAdapter adapter, final ProgressBar progressBar)
    {
        progressBar.setVisibility(View.VISIBLE);

        myRetro.create(CommitmentApi.class) //get Data from client
                .getCommitment()
                .enqueue(new Callback<Commitment>()
                {
                    @Override
                    public void onResponse(Call<Commitment> call, Response<Commitment> response)
                    {
                        commitment = response.body();
                        if( response.isSuccessful() )
                        {
                            results.clear();
                            for( int i = 0; commitment.getCount() > i; i++ )
                            {
                                results.add(commitment.getResults().get(i));
                            }
                            RecyclerView.LayoutManager myLayoutManager = new LinearLayoutManager( context );
                            recyclerView.setLayoutManager(myLayoutManager);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            progressBar.setVisibility(GONE);
                        }

                    }

                    @Override
                    public void onFailure(Call<Commitment> call, Throwable t)
                    {
                        t.printStackTrace();
                        progressBar.setVisibility(GONE);
                    }

                });

    }

    public void insertData(String title, String description)
    {
        if(Utils.isNetworkAvailable(context) == false)
        {
            Toast.makeText(context, "Network Error.", Toast.LENGTH_SHORT).show();
        }
        else if(!title.equals("") && !description.equals(""))
        {
            myRetro.create(CommitmentApi.class)
                    .insertData("", title, description)
                    .enqueue(new Callback<Void>()
                    {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response)
                        {
                            getResponseCode(String.valueOf(response.code()), "Data", "added");
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t)
                        {
                            t.printStackTrace();
                        }
                    });
        }
        else
        {
            Toast.makeText(context, "Please fill in the blanks.", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateData(String id, String title, String description)
    {
        if(Utils.isNetworkAvailable(context) == false)
        {
            Toast.makeText(context, "Network Error.", Toast.LENGTH_SHORT).show();
        }
        else if(!id.equals("") && !title.equals("") && !description.equals(""))
        {
            myRetro.create(CommitmentApi.class)
                    .updateById(Integer.parseInt(id), title.toString(), description.toString())
                    .enqueue(new Callback<Void>()
                    {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response)
                        {
                            getResponseCode(String.valueOf(response.code()), "Data", "updated");
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t)
                        {
                            t.printStackTrace();
                        }
                    });
        }
        else
        {
            Toast.makeText(context, "Please fill in the blanks.", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteData(String id)
    {
        if(Utils.isNetworkAvailable(context) == false)
        {
            Toast.makeText(context, "Network Error.", Toast.LENGTH_SHORT).show();
        }
        else if( !id.equals("") )
        {
            myRetro.create(CommitmentApi.class)
                    .deleteById(Integer.parseInt(id))
                    .enqueue(new Callback<Void>()
                    {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response)
                        {
                            getResponseCode(String.valueOf(response.code()), "Data", "deleted");
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t)
                        {
                            t.printStackTrace();
                        }
                    });
        }
        else
        {
            Toast.makeText( context, "Please fill in the blanks.", Toast.LENGTH_SHORT).show();
        }
    }
}
