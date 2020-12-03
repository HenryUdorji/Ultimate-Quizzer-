package com.codemountain.ultimatequizzer;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatDelegate;

import com.codemountain.ultimatequizzer.utils.SharedPref;

public class UltimateQuizzerApplication extends Application {

    private Context context;
    private static SharedPreferences sharedPref;

    public static SharedPreferences getSharedPref() {
        return sharedPref;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPref.init();
        context = getApplicationContext();
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

        //Initialize App in night or light mode
        if (SharedPref.getSharedPrefInstance().getMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
