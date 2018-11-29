package com.paularity.axcd.retrofitdaggerapp.Helpers;

import android.app.Activity;
import android.util.Log;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.security.ProviderInstaller;
import com.paularity.axcd.retrofitdaggerapp.Services.NetworkService;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import dagger.Provides;

public class Helper
{
    public void updateAndroidSecurityProvider(Activity activity)
    {
        try
        {
            ProviderInstaller.installIfNeeded(activity);
        }
        catch (GooglePlayServicesRepairableException e)
        {
            GooglePlayServicesUtil.getErrorDialog(e.getConnectionStatusCode(), activity, 0);
        }
        catch (GooglePlayServicesNotAvailableException e)
        {
            Log.e("SecurityException", "Google Play Services not available.");
        }
    }
}
