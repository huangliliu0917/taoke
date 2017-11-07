package com.kunion.taoke.util;

import android.content.Context;

import com.kunion.taoke.TKApp;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2017/10/30.
 */

public class FormatUtil {

    public static String getTextString(int resId, String desStr) {
        if(StringUtil.isNotBlank(desStr)) {
            return TKApp.instance.getString(resId, desStr);
        } else {
            return "";
        }
    }

    public static String getTextNumber(int resId, Number desNumber) {
        if (desNumber != null) {
            return TKApp.instance.getString(resId, desNumber);
        } else {
            return "";
        }
    }

    public static String getTextNumber(int resId, int desNumber) {
        return TKApp.instance.getString(resId, desNumber+"");
    }

    public static String getTextNumber(int resId, float desNumber) {
        return TKApp.instance.getString(resId, desNumber+"");
    }

    public static String payIncome(float realincome) {
        int deviceRate = 10;
        int rate = TKApp.sUser.getRate();
        if(rate > 0) {
            float payIncome = realincome * (100-deviceRate) * rate / 10000;
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            return decimalFormat.format(payIncome);
        } else {
            return String.valueOf(realincome);
        }
    }

    public static String getMMDDHHSS(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
        return sdf.format(date);
    }
}
