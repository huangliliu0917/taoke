package com.kunion.taoke.model.remote;

import android.support.annotation.NonNull;

import com.kunion.taoke.model.TasksSource;

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
    public void loginTask(@NonNull String name, @NonNull String password) {

    }
}
