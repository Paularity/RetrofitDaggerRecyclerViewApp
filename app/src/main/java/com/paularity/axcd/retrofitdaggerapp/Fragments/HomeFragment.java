package com.paularity.axcd.retrofitdaggerapp.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.paularity.axcd.retrofitdaggerapp.Adapters.CommitmentRecyclerViewAdapter;
import com.paularity.axcd.retrofitdaggerapp.Api.CommitmentApi;
import com.paularity.axcd.retrofitdaggerapp.Dagger.MyApplication;
import com.paularity.axcd.retrofitdaggerapp.Helpers.CommitmentApiHelper;
import com.paularity.axcd.retrofitdaggerapp.Models.Commitment;
import com.paularity.axcd.retrofitdaggerapp.Models.Result;
import com.paularity.axcd.retrofitdaggerapp.Models.WebService;
import com.paularity.axcd.retrofitdaggerapp.R;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment
{
    @Inject
    DetailsFragment detailsFragment;

    @Inject
    Retrofit myRetro;

    @Inject
    Commitment commitment;

    @Inject
    CommitmentApiHelper commitmentApiHelper;

    @BindView(R.id.recyclerview_layout)
    RecyclerView rv_layout;

    @BindView(R.id.selectedTxt)
    TextView textView;

    @BindView(R.id.responseProgress)
    ProgressBar responseProgress;

    ArrayList<Result> results = new ArrayList<>();

    CommitmentRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ((MyApplication)getActivity().getApplication()).getComponent().inject(this); //Dagger injection
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        initList();
    }

    private void initList()
    {
        responseProgress.getIndeterminateDrawable().setColorFilter(
                getResources().getColor(R.color.colorPrimaryDark),
                android.graphics.PorterDuff.Mode.SRC_IN);
        responseProgress.setVisibility(View.GONE);

        adapter = new CommitmentRecyclerViewAdapter( getActivity(), results );
        //RecyclerView.LayoutManager myLayoutManager = new GridLayoutManager(this,3);
        commitmentApiHelper.getResultList(getActivity(), results, rv_layout, adapter, responseProgress);
    }
}
