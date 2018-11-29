package com.paularity.axcd.retrofitdaggerapp.Dagger;

import android.app.Application;

import com.paularity.axcd.retrofitdaggerapp.Dagger.Components.AppComponent;
import com.paularity.axcd.retrofitdaggerapp.Dagger.Components.DaggerAppComponent;
import com.paularity.axcd.retrofitdaggerapp.Dagger.Modules.ContextModule;
import com.paularity.axcd.retrofitdaggerapp.Models.Commitment;

public class MyApplication extends Application
{
    private AppComponent component;

    @Override
    public void onCreate()
    {
        super.onCreate();
        component = buildComponent();
    }

    private AppComponent buildComponent() //build dagger
    {
        return DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public AppComponent getComponent()
    {
        return this.component;
    } //to be injected
}
