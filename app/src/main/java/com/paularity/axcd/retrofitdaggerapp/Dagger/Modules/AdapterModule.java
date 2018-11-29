package com.paularity.axcd.retrofitdaggerapp.Dagger.Modules;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.paularity.axcd.retrofitdaggerapp.Adapters.CommitmentRecyclerViewAdapter;
import com.paularity.axcd.retrofitdaggerapp.Models.Commitment;
import com.paularity.axcd.retrofitdaggerapp.Models.Result;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class AdapterModule
{
    private ArrayList<Result> results;

    @Provides
    CommitmentRecyclerViewAdapter provideCommitmentRecyclerViewAdapter(Context context, RecyclerView recyclerView, ArrayList<Result> results)
    {
        CommitmentRecyclerViewAdapter adapter = new CommitmentRecyclerViewAdapter(context, results);
        RecyclerView.LayoutManager myLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return adapter;
    }
}
