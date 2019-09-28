package com.mobile.seoul.seoulstampapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferenceUtils {

    private static final String DEFAULT_PREFS = "default_shared_prefs";
    private static final int DEFAULT_NUMERIC_VALUE = 0;

    private final Context context;
    private final Gson gson;

    @Inject
    PreferenceUtils(Context context, Gson gson) {
        this.context = context;
        this.gson = gson;
    }

    public int getInteger(String key) {
        SharedPreferences prefs = context.getSharedPreferences(DEFAULT_PREFS, Context.MODE_PRIVATE);
        return prefs.getInt(key, DEFAULT_NUMERIC_VALUE);
    }

    public void setInteger(String key, int value) {
        SharedPreferences prefs = context.getSharedPreferences(DEFAULT_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key) {
        SharedPreferences prefs = context.getSharedPreferences(DEFAULT_PREFS, Context.MODE_PRIVATE);
        return prefs.getBoolean(key, false);
    }

    public void setBoolean(String key, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(DEFAULT_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
}
