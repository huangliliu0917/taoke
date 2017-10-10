package com.kunion.taoke.util;


import android.content.Context;
import android.content.SharedPreferences;

import com.kunion.taoke.R;

import java.util.HashSet;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by liyao on 15/9/22.
 */
public class SharePfeUtils {

    private static final String obj = "a";
    public static final String PREF_COOKIES = "pref_cookie";
    SharedPreferences mPreference;

    public SharePfeUtils(Context context) {
        mPreference = context.getSharedPreferences(
                context.getResources().getString(R.string.app_name),
                MODE_PRIVATE);

    }

    public void putValue(String key, Boolean value) {
        SharedPreferences.Editor editor = mPreference.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getValue(String key, boolean def) {
        return mPreference.getBoolean(key, def);
    }

    public String getValue(String key, String def) {
        return mPreference.getString(key, def);
    }

    public void putValue(String key, String value) {
        SharedPreferences.Editor editor = mPreference.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void putValue(String key, long value) {
        SharedPreferences.Editor editor = mPreference.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public long getValue(String key, long def) {
        return mPreference.getLong(key, def);
    }

    public void putValue(String key, int value) {
        SharedPreferences.Editor editor = mPreference.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getValue(String key, int def) {
        return mPreference.getInt(key, def);
    }

    public void putValue(String key, Set<String> value) {
        SharedPreferences.Editor editor = mPreference.edit();
        editor.putStringSet(key, value);
        editor.apply();
    }

    public Set<String> getValue(String key, Set<String> value) {
        return mPreference.getStringSet(key, value);
    }
}
