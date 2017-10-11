package com.kunion.taoke.model.remote.rest.info;

/**
 * Created by Administrator on 2017/6/15.
 *
 *
 public static final String DB="item";
 public static final String CPrice="c_price";
 public static final String Type="type";
 public static final String PrePrice="pre_price";
 public static final String CRate="c_rate";
 public static final String CurPricce="cur_price";
 public static final String Title="title";
 public static final String Img="img";
 public static final String Link="link";
 public static final String CLink="clink";
 }

 */

public class CheckVersion extends AVBaseBean {

    public String apk;
    public boolean forceUpdate;
    public int version;

    public String getApk() {
        return apk;
    }

    public void setApk(String apk) {
        this.apk = apk;
    }

    public boolean isForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
