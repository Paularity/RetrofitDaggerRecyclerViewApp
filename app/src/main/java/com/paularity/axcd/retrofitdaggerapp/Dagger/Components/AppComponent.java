package com.paularity.axcd.retrofitdaggerapp.Dagger.Components;

import com.paularity.axcd.retrofitdaggerapp.Dagger.Modules.ContextModule;
import com.paularity.axcd.retrofitdaggerapp.Dagger.Scopes.TestScope;
import com.paularity.axcd.retrofitdaggerapp.Fragments.DetailsFragment;
import com.paularity.axcd.retrofitdaggerapp.Fragments.HomeFragment;
import com.paularity.axcd.retrofitdaggerapp.Activities.MainActivity;
import com.paularity.axcd.retrofitdaggerapp.Helpers.CommitmentApiHelper;
import com.paularity.axcd.retrofitdaggerapp.Models.Commitment;
import com.paularity.axcd.retrofitdaggerapp.Models.Result;

import dagger.Component;

@Component(modules = ContextModule.class)
@TestScope
public interface AppComponent
{
    //Constructor injection bind
    Commitment provideCommitment();
    Result provideResult();

    //Method injection
    void inject(MainActivity activity);
    void inject(HomeFragment fragment);
    void inject(DetailsFragment fragment);
    void inject(CommitmentApiHelper helper);


}
