package com.kunion.taoke.model.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.kunion.taoke.model.TasksSource;
import com.kunion.taoke.model.remote.rest.RestService;
import com.kunion.taoke.model.remote.rest.resp.LoginResp;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/10/10.
 */

public class TasksRemoteSource implements TasksSource{

    private static TasksRemoteSource INSTANCE;

    public static TasksRemoteSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TasksRemoteSource();
        }
        return INSTANCE;
    }

    private TasksRemoteSource() {
    }

    @Override
    public Observable<LoginResp> loginTask(@NonNull String name, @NonNull String password) {
        RestService service = ServiceGenerator.RestService();
        return service.login(name, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());


    }
}
