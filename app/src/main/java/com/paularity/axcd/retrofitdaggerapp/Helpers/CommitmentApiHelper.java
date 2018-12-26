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
import com.paularity.axcd.retrofitdaggerapp.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
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

    public void getResultList(final ArrayList<Result> results, final ProgressBar responseProgress, final CommitmentRecyclerViewAdapter adapter, final Context context, final RecyclerView rv_layout)
    {
        responseProgress.setVisibility(View.VISIBLE);

        //get first all items in each pages
        myRetro.create(CommitmentApi.class) //get Data from client
                .getCommitment(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Commitment>()
                {
                    @Override
                    public void onSuccess(Commitment commitments)
                    {
                        float total_page_size = (commitments.getCount() > 10) ? (float)(commitments.getCount() / 10.0) : 1;

                        int page_size = (int) Math.ceil(total_page_size);

                        Log.e( "Total Page ", String.valueOf(page_size));
                        for( int i=1; i <= page_size; i++ )
                        {
                            //get each item for each page
                            myRetro.create(CommitmentApi.class) //get Data from client
                                    .getCommitment(i)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeWith(new DisposableSingleObserver<Commitment>()
                                    {
                                        @Override
                                        public void onSuccess(Commitment commitments)
                                        {
                                            Log.e( "Next Page", String.valueOf(commitments.getNext()) );
                                            for( int i = 0; commitments.getResults().size() > i; i++ )
                                            {
                                                results.add(commitments.getResults().get(i));
                                            }

                                            RecyclerView.LayoutManager myLayoutManager = new LinearLayoutManager( context );
                                            rv_layout.setLayoutManager(myLayoutManager);
                                            rv_layout.setAdapter(adapter);
                                            adapter.notifyDataSetChanged();
                                            responseProgress.setVisibility(GONE);
                                        }

                                        @Override
                                        public void onError(Throwable e)
                                        {
                                            e.printStackTrace();
                                        }
                                    });
                        }


                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        e.printStackTrace();
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
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableCompletableObserver()
                    {
                        @Override
                        public void onComplete()
                        {
                            getResponseCode(String.valueOf(200), "Data", "added");
                        }

                        @Override
                        public void onError(Throwable e)
                        {
                            getResponseCode(String.valueOf(404), "Data", "added");
                            e.printStackTrace();
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
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableCompletableObserver()
                    {
                        @Override
                        public void onComplete()
                        {
                            getResponseCode(String.valueOf(200), "Data", "updated");
                        }

                        @Override
                        public void onError(Throwable e)
                        {
                            getResponseCode(String.valueOf(404), "Data", "updated");
                            e.printStackTrace();
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
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableCompletableObserver()
                    {
                        @Override
                        public void onComplete()
                        {
                            getResponseCode(String.valueOf(200), "Data", "deleted");
                        }

                        @Override
                        public void onError(Throwable e)
                        {
                            getResponseCode(String.valueOf(404), "Data", "deleted");
                            e.printStackTrace();
                        }
                    });
        }
        else
        {
            Toast.makeText( context, "Please fill in the blanks.", Toast.LENGTH_SHORT).show();
        }
    }
}
