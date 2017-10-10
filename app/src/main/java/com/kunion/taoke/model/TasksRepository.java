package com.kunion.taoke.model;

import android.support.annotation.NonNull;

import com.kunion.taoke.model.remote.rest.resp.LoginResp;

import io.reactivex.Observable;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/10/10.
 */

public class TasksRepository implements TasksSource{

    private static TasksRepository INSTANCE = null;

    private final TasksSource mTasksRemoteSource;


    // Prevent direct instantiation.
    private TasksRepository(@NonNull TasksSource tasksRemoteSource) {
        mTasksRemoteSource = checkNotNull(tasksRemoteSource);

    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param tasksRemoteSource the backend data source
     * @return the {@link TasksRepository} instance
     */
    public static TasksRepository getInstance(TasksSource tasksRemoteSource) {
        if (INSTANCE == null) {
            INSTANCE = new TasksRepository(tasksRemoteSource);
        }
        return INSTANCE;
    }


    @Override
    public Observable<LoginResp> loginTask(@NonNull String name, @NonNull String password) {
        return mTasksRemoteSource.loginTask(name, password);
    }
}
