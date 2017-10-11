package com.kunion.taoke.main;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.kunion.taoke.TKApp;
import com.kunion.taoke.login.LoginContract;
import com.kunion.taoke.model.TasksSource;
import com.kunion.taoke.model.remote.rest.info.CheckVersion;
import com.kunion.taoke.model.remote.rest.resp.CheckVersionResp;
import com.kunion.taoke.util.AutoInstall;
import com.kunion.taoke.util.SharePfeUtils;
import com.kunion.taoke.util.StringUtil;

import io.reactivex.functions.Consumer;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/10/11.
 */

public class MainPresenter implements MainContract.Presenter {

    @NonNull
    private TasksSource mTasksRepository;

    @NonNull
    private final MainContract.View mMainView;

    private CheckVersion mVersionInfo;

    public MainPresenter(@NonNull TasksSource tasksSource, @NonNull MainContract.View mainView) {
        mTasksRepository = checkNotNull(tasksSource);
        mMainView = checkNotNull(mainView);

        mMainView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void checkVersion() {
        int version = TKApp.instance.getVersionCode();
        mTasksRepository.checkVersion(version).subscribe(new Consumer<CheckVersionResp>() {
            @Override
            public void accept(CheckVersionResp checkVersionResp) throws Exception {
                CheckVersion version = checkVersionResp.getData();
                mVersionInfo = version;
                final String path = version.getApk();

                if (StringUtil.isBlank(path))
                    return;

                TKApp.SPUtil.putValue(SharePfeUtils.UVERSION_TIME, 0);

                if (version.isForceUpdate())
                    mMainView.forceVersion(path);
                else
                    mMainView.updateVersion(path);

            }
        });
    }

    @Override
    public void installAPK() {
        if(mVersionInfo != null) {
            AutoInstall.setUrl(mVersionInfo.getApk());
            AutoInstall.install(TKApp.instance);
        }
    }


    @Override
    public void downloadAPK() {
        if(mVersionInfo == null) return;

        int i=0;

    }
}
