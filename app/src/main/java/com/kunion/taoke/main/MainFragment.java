package com.kunion.taoke.main;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kunion.taoke.R;
import com.kunion.taoke.TKApp;
import com.kunion.taoke.databinding.LoginFragBinding;
import com.kunion.taoke.databinding.MainFragBinding;
import com.kunion.taoke.login.LoginBean;
import com.kunion.taoke.login.LoginContract;
import com.kunion.taoke.login.LoginPresenter;
import com.kunion.taoke.util.AutoInstall;
import com.kunion.taoke.util.SharePfeUtils;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/10/10.
 */

public class MainFragment extends Fragment implements MainContract.View, EasyPermissions.PermissionCallbacks {
    private static final int REQUEST_CODE_SAVE_IMG = 10;
    private final String TAG = getClass().getSimpleName();
    private MainContract.Presenter mPresenter;

    private LoginBean mLoginBean;

    @Override
    public void setPresenter(@NonNull MainContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainFragBinding binding = DataBindingUtil.inflate(inflater, R.layout.main_frag, container, false);
        mLoginBean = new LoginBean();

        mLoginBean.name.set(TKApp.SPUtil.getValue("username", ""));
        mLoginBean.password.set(TKApp.SPUtil.getValue("password", ""));

        binding.setLoginbean(mLoginBean);
//        binding.setPresenter((MainPresenter) mPresenter);
        return binding.getRoot();
    }

    @Override
    public void updateVersion(String path) {

        initDownloadPermission();
    }

    @Override
    public void forceVersion(String path) {
        new AlertDialog.Builder(getContext())
                .setTitle(R.string.version_update)
                .setMessage(R.string.to_update)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        initDownloadPermission();
                    }
                }).show();
    }

    public void initDownloadPermission() {

        if (Build.VERSION.SDK_INT >= 23) {
            //读取sd卡的权限
            String[] mPermissionList = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (EasyPermissions.hasPermissions(getContext(), mPermissionList)) {
                //已经同意过
                mPresenter.downloadAPK();
            } else {
                //未同意过,或者说是拒绝了，再次申请权限
                EasyPermissions.requestPermissions(
                        this,  //上下文
                        "下载最新版本App", //提示文言
                        REQUEST_CODE_SAVE_IMG, //请求码
                        mPermissionList //权限列表
                );
            }
        } else {
            mPresenter.downloadAPK();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //跳转到onPermissionsGranted或者onPermissionsDenied去回调授权结果
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //同意授权
    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        Log.i(TAG, "onPermissionsGranted:" + requestCode + ":" + list.size());
        mPresenter.downloadAPK();
    }

    //拒绝授权
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.i(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            //打开系统设置，手动授权
            new AppSettingsDialog.Builder(this).build().show();
        }
    }


    public ProgressDialog dialog;
    public void showDialog(boolean isForce) {
        dialog = new ProgressDialog(getContext());
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 设置进度条的形式为圆形转动的进度条
        if (isForce) {
            dialog.setCancelable(false);// 设置是否可以通过点击Back键取消
            dialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        }else {

            dialog.setCancelable(true);// 设置是否可以通过点击Back键取消
            dialog.setCanceledOnTouchOutside(true);// 设置在点击Dialog外是否取消Dialog进度条
            // 设置可点击的按钮，最多有三个(默认情况下)
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.background_download),
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub

                        }
                    });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            //拒绝授权后，从系统设置了授权后，返回APP进行相应的操作
            Log.i(TAG, "onPermissionsDenied:------>自定义设置授权后返回APP");
            mPresenter.downloadAPK();
        }
    }

    @Override
    public void updateProgress(int progress) {
        if (0 == progress && null != dialog && !dialog.isShowing()) {
            dialog.show();
        }

        if (null != dialog && dialog.isShowing())
            dialog.setProgress(progress);
        if (100 == progress) {
            mPresenter.installAPK();
            if (null != dialog && dialog.isShowing())
                dialog.dismiss();
        }
    }
}