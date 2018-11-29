package com.paularity.axcd.retrofitdaggerapp.Dagger.Modules;

import android.content.Context;

import com.paularity.axcd.retrofitdaggerapp.Dagger.Scopes.TestScope;
import com.paularity.axcd.retrofitdaggerapp.Helpers.CommitmentApiHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class HelperModule
{
    @Provides
    @TestScope
    CommitmentApiHelper provideCommitmentApiHelper(Context context)
    {
        return new CommitmentApiHelper(context);
    }
}
