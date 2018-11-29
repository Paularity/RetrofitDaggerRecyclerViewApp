package com.paularity.axcd.retrofitdaggerapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.paularity.axcd.retrofitdaggerapp.Dagger.MyApplication;
import com.paularity.axcd.retrofitdaggerapp.Helpers.CommitmentApiHelper;
import com.paularity.axcd.retrofitdaggerapp.Models.Commitment;
import com.paularity.axcd.retrofitdaggerapp.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;

public class DetailsFragment extends Fragment
{
    @BindView(R.id.details_text) TextView textDetails;
    @BindView(R.id.edt_title) EditText edt_title;
    @BindView(R.id.edt_desc) EditText edt_desc;

    @BindView(R.id.edt_id) EditText edt_id;

    @BindView(R.id.edt_id_edit) EditText edt_id_edit;
    @BindView(R.id.edt_title_edit) EditText edt_title_edit;
    @BindView(R.id.edt_desc_edit) EditText edt_desc_edit;

    @Inject
    Retrofit myRetro;

    @Inject
    Commitment commitment;

    @Inject
    CommitmentApiHelper commitmentApiHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ((MyApplication)getActivity().getApplication()).getComponent().inject(this); //Dagger injection

        View view = inflater.inflate(R.layout.fragment_details, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @OnClick({R.id.btn_submit, R.id.btn_delete, R.id.btn_update})
    public void onClick(View view)
    {
        String delete_id = edt_id.getText().toString();
        String title = edt_title.getText().toString();
        String description = edt_desc.getText().toString();
        String edit_id = edt_id_edit.getText().toString();
        String edit_title = edt_title_edit.getText().toString();
        String edit_description = edt_desc_edit.getText().toString();

        switch (view.getId())
        {
            case R.id.btn_submit:
                commitmentApiHelper.insertData(title, description);
                break;

            case R.id.btn_update:
                commitmentApiHelper.updateData(edit_id, edit_title, edit_description );
                break;

            case R.id.btn_delete:
                commitmentApiHelper.deleteData( delete_id );
                break;

            default:
                break;
        }

    }

}
