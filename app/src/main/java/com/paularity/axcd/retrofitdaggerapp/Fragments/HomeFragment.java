package com.paularity.axcd.retrofitdaggerapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.paularity.axcd.retrofitdaggerapp.Adapters.CommitmentRecyclerViewAdapter;
import com.paularity.axcd.retrofitdaggerapp.Api.CommitmentApi;
import com.paularity.axcd.retrofitdaggerapp.Dagger.MyApplication;
import com.paularity.axcd.retrofitdaggerapp.Helpers.CommitmentApiHelper;
import com.paularity.axcd.retrofitdaggerapp.Models.Commitment;
import com.paularity.axcd.retrofitdaggerapp.Models.Result;
import com.paularity.axcd.retrofitdaggerapp.R;

import java.util.ArrayList;
import java.util.Collections;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

import static android.view.View.GONE;

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

    @BindView(R.id.responseProgress)
    ProgressBar responseProgress;

    @BindView(R.id.spinner)
    Spinner spinner;

    ArrayList<Result> results = new ArrayList<>();

    CommitmentRecyclerViewAdapter adapter;

    ArrayList<Integer> total_page = new ArrayList<>();
    ArrayAdapter<Integer> page_adapter;

    int current_page = 1;

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
        paginate();
    }

    private void paginate()
    {
        total_page.add(1);
        total_page.add(2);

        page_adapter = new ArrayAdapter<Integer>(getActivity(), android.R.layout.simple_spinner_dropdown_item, total_page);
        spinner.setAdapter(page_adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                current_page = total_page.get(position);
                fetchAllData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {
                current_page = 1;
                fetchAllData();
            }

        });
    }

    private void fetchAllData()
    {
        responseProgress.getIndeterminateDrawable().setColorFilter(
                getResources().getColor(R.color.colorPrimaryDark),
                android.graphics.PorterDuff.Mode.SRC_IN);
        responseProgress.setVisibility(View.GONE);

        adapter = new CommitmentRecyclerViewAdapter( getActivity(), results );
        commitmentApiHelper.getResultList(current_page, results, responseProgress, adapter, getActivity(), rv_layout);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        total_page.clear();
    }


}
