package com.kunion.taoke;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.kunion.taoke.model.remote.rest.resp.LoginResp;
import com.kunion.taoke.util.SharePfeUtils;

/**
 * Created by Administrator on 2017/10/10.
 */

public class TKApp extends Application {

    public static TKApp instance = null;
    public static SharePfeUtils SPUtil;

    public static LoginResp sUser;
    public static UserInfoMSG sUIMSG;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        SPUtil = new SharePfeUtils(getApplicationContext());
        sUIMSG =  UserInfoMSG.getInstance();
    }


    /**
     　　* 获取版本号
     　　* @return 当前应用的版本号
     　　*/
    public int getVersionCode() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
