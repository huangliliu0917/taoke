package com.kunion.taoke.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.kunion.taoke.R;
import com.kunion.taoke.TKApp;
import com.kunion.taoke.model.TasksSource;
import com.kunion.taoke.model.remote.rest.info.UserGroupInfo;
import com.kunion.taoke.model.remote.rest.resp.GroupsResp;
import com.kunion.taoke.model.remote.rest.resp.LoginResp;
import com.kunion.taoke.model.remote.rest.resp.StringResp;
import com.kunion.taoke.util.StringUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/10/10.
 */

public class LoginPresenter implements LoginContract.Presenter {

    @NonNull
    private TasksSource mTasksRepository;

    @NonNull
    private final LoginContract.View mLoginView;

    public LoginPresenter(@NonNull TasksSource tasksSource, @NonNull LoginContract.View loginView) {
        mTasksRepository = checkNotNull(tasksSource);
        mLoginView = checkNotNull(loginView);

        mLoginView.setPresenter(this);
    }

    @Override
    public void start() {

    }


    @Override
    public void loginTask(@NonNull LoginBean bean) {

        final String name = bean.name.get();
        final String password = bean.password.get();
        if(StringUtil.isBlank(name)) {
            mLoginView.showLoginFail(R.string.no_name);
            return ;
        }

        if(StringUtil.isBlank(password)) {
            mLoginView.showLoginFail(R.string.no_password);
            return ;
        }

        mTasksRepository.loginTask(bean.name.get(), bean.password.get()).subscribe(new Observer<LoginResp>(){
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                Log.d("loginTask", "onSubscribe ");
            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull LoginResp loginResp) {
                Log.d("loginTask", "onNext ");
                mLoginView.showLoginSuccess();
                TKApp.SPUtil.putValue("username", name);
                TKApp.SPUtil.putValue("password", password);
                TKApp.sUser = loginResp;
                initUserInfo();
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                Log.d("loginTask", "onError "+e.toString());
                mLoginView.showLoginFail(R.string.login_fail);
            }

            @Override
            public void onComplete() {
                Log.d("loginTask", "onComplete");
            }
        });
    }

    @Override
    public void initUserInfo() {
        Observable.zip(mTasksRepository.getDstGroup(), mTasksRepository.getGroups(0, 999), new BiFunction<StringResp, GroupsResp, Boolean>() {
            @Override
            public Boolean apply(@io.reactivex.annotations.NonNull StringResp stringResp, @io.reactivex.annotations.NonNull GroupsResp groupsResp) throws Exception {

                String group = stringResp.getData();
                TKApp.sUIMSG.setDestGroup(group);

                List<UserGroupInfo> groups = groupsResp.getData();
                TKApp.sUIMSG.setTMObjects(groups);

                return true;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull Boolean aBoolean) {
                mLoginView.goMain();
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                mLoginView.showLoginFail(R.string.login_fail);
            }

            @Override
            public void onComplete() {

            }
        });
    }


}
