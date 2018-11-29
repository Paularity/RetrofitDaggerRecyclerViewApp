package com.paularity.axcd.retrofitdaggerapp.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.paularity.axcd.retrofitdaggerapp.Dagger.MyApplication;
import com.paularity.axcd.retrofitdaggerapp.Fragments.DetailsFragment;
import com.paularity.axcd.retrofitdaggerapp.Fragments.HomeFragment;
import com.paularity.axcd.retrofitdaggerapp.Models.Commitment;
import com.paularity.axcd.retrofitdaggerapp.Models.Result;
import com.paularity.axcd.retrofitdaggerapp.R;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity
{
    private static String TAG = "MainActivity";

    @Inject
    Retrofit myRetrofit;

    @Inject
    Commitment commitment;
    @Inject
    Result result;

    @Inject
    HomeFragment homeFragment;
    @Inject
    DetailsFragment detailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 //      new Helper().updateAndroidSecurityProvider(this);

        ButterKnife.bind(this); //Butterknife Inject

        ((MyApplication)getApplication()).getComponent().inject(this); //Dagger injection

        openFragment(homeFragment);
//        myRetrofit.create(JSONPlaceHolderApi.class)
//                .getPostWithID(1)
//                .enqueue(new Callback<Post>()
//                {
//                    @Override
//                    public void onResponse(Call<Post> call, Response<Post> response)
//                    {
//                        Post post = response.body();
//                        Log.d("ID", String.valueOf(post.getId()) );
//                        Log.d("UserID", String.valueOf(post.getUserId()) );
//                        Log.d("Title", String.valueOf(post.getTitle()) );
//                        Log.d("Body", String.valueOf(post.getBody())) ;
//                    }
//
//                    @Override
//                    public void onFailure(Call<Post> call, Throwable t)
//                    {
//                        textView.setText("Error occurred while getting request!");
//                        t.printStackTrace();
//                    }
//                });
//
    }

    private void openFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @OnClick(R.id.btn_next)
    public void onClick(View view)
    {
        if(detailsFragment.isAdded())
        {
            openFragment(homeFragment);
        }
        else
        {
            openFragment(detailsFragment);
        }
        //detailsFragment.getData("aaaa");
    }

}
