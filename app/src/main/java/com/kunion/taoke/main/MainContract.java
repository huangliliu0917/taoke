package com.kunion.taoke.main;

import android.support.annotation.NonNull;

import com.kunion.taoke.BasePresenter;
import com.kunion.taoke.BaseView;
import com.kunion.taoke.login.LoginBean;
import com.kunion.taoke.login.LoginContract;

/**
 * Created by Administrator on 2017/10/11.
 */

public interface MainContract {
    interface View extends BaseView<MainContract.Presenter> {
        void updateVersion(String path);
        void forceVersion(String path);
        void updateProgress(int progress);
    }

    interface Presenter extends BasePresenter {
        void checkVersion();
        void installAPK();
        void downloadAPK();
    }
}
