package com.kunion.taoke.login;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.kunion.taoke.BasePresenter;
import com.kunion.taoke.BaseView;

/**
 * Created by Administrator on 2017/10/10.
 */

public class LoginContract {

    interface View extends BaseView<Presenter> {
        void showLoginSuccess();
        void showLoginFail();

    }

    interface Presenter extends BasePresenter {
        void loginTask(@NonNull LoginBean bean);
    }
}
