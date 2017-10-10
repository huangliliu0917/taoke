package com.kunion.taoke.login;

import android.support.annotation.NonNull;

import com.kunion.taoke.model.TasksSource;

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
        mTasksRepository.loginTask(bean.name.get(), bean.password.get());
    }

}
