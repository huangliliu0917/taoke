package com.kunion.taoke.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.kunion.taoke.model.TasksSource;
import com.kunion.taoke.model.remote.rest.resp.LoginResp;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
        mTasksRepository.loginTask(bean.name.get(), bean.password.get()).subscribe(new Observer<LoginResp>(){
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
                Log.d("loginTask", "onSubscribe ");
            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull LoginResp loginResp) {
                Log.d("loginTask", "onNext ");
                mLoginView.showLoginSuccess();
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                Log.d("loginTask", "onError "+e.toString());
                mLoginView.showLoginFail();
            }

            @Override
            public void onComplete() {
                Log.d("loginTask", "onComplete");
            }
        });
    }

}
