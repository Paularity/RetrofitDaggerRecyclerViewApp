package com.paularity.axcd.retrofitdaggerapp.Dagger.Modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {NetworkModule.class})
public class ContextModule
{
    private Context context;

    public ContextModule(Context context)
    {
        this.context = context;
    }

    @Provides
    Context provideContext()
    {
        return this.context;
    }
}
