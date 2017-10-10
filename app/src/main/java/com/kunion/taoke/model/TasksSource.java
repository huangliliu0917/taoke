package com.kunion.taoke.model;

import android.support.annotation.NonNull;

import com.kunion.taoke.model.remote.rest.resp.LoginResp;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/10/10.
 */

public interface TasksSource {

    Observable<LoginResp> loginTask(@NonNull String name, @NonNull String password);

}
