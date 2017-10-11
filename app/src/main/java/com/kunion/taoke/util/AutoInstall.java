package com.kunion.taoke.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

public class AutoInstall {
	private static String mUrl;
	private static Context mContext;

	/**
	 * 外部传进来的url以便定位需要安装的APK
	 * 
	 * @param url
	 */
	public static void setUrl(String url) {
		mUrl = url;
	}

	/**
	 * 安装
	 * 
	 * @param context
	 *            接收外部传进来的context
	 */
	public static void install(Context context) {
		mContext = context;
		// 核心是下面几句代码
		Intent intent = new Intent(Intent.ACTION_VIEW);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			Uri contentUri = FileProvider.getUriForFile(context, "com.mmjoyinc.tk.fileprovider", new File(mUrl));
			intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
		} else {
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setDataAndType(Uri.fromFile(new File(mUrl)),
					"application/vnd.android.package-archive");
		}
		mContext.startActivity(intent);
	}
}