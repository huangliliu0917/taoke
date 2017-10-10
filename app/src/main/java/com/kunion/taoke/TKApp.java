package com.kunion.taoke;

import android.app.Application;

import com.kunion.taoke.util.SharePfeUtils;

/**
 * Created by Administrator on 2017/10/10.
 */

public class TKApp extends Application {

    public static TKApp instance = null;
    public static SharePfeUtils SPUtil;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        SPUtil = new SharePfeUtils(getApplicationContext());
    }
}
