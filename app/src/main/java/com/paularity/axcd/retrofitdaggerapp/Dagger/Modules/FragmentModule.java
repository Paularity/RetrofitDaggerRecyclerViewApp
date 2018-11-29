package com.paularity.axcd.retrofitdaggerapp.Dagger.Modules;

import com.paularity.axcd.retrofitdaggerapp.Dagger.Scopes.TestScope;
import com.paularity.axcd.retrofitdaggerapp.Fragments.DetailsFragment;
import com.paularity.axcd.retrofitdaggerapp.Fragments.HomeFragment;
import com.paularity.axcd.retrofitdaggerapp.Helpers.CommitmentApiHelper;
import com.paularity.axcd.retrofitdaggerapp.Helpers.Helper;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule
{
    @Provides
    @TestScope
    HomeFragment provideHomeFragment()
    {
        return new HomeFragment();
    }

    @Provides
    @TestScope
    DetailsFragment provideDetailsFragment()
    {
        return new DetailsFragment();
    }
}
