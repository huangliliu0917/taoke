package com.kunion.taoke.main;


import android.view.View;

import com.kunion.taoke.BasePresenter;
import com.kunion.taoke.BaseView;

/**
 * Created by Administrator on 2017/10/11.
 */

public interface MainContract {
    interface ViewPresenter extends BaseView<MainContract.Presenter> {
        void updateVersion(String path);
        void forceVersion(String path);
        void updateProgress(int progress);

        void setCurPage(View v, int curIndex);
    }

    interface Presenter extends BasePresenter {
        void checkVersion();
        void installAPK();
        void downloadAPK();

        void switchPage(android.view.View view, int indexPage);
    }
}
