package com.kunion.taoke.model;

import android.support.annotation.NonNull;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/10/10.
 */

public class TasksRepository implements TasksSource{

    private static TasksRepository INSTANCE = null;

    private final TasksSource mTasksRemoteSource;


    // Prevent direct instantiation.
    private TasksRepository(@NonNull TasksSource tasksRemoteSourcee) {
        mTasksRemoteSource = checkNotNull(tasksRemoteSourcee);

    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param TasksSource the backend data source
     * @return the {@link TasksRepository} instance
     */
    public static TasksRepository getInstance(TasksSource tasksRemoteSourcee) {
        if (INSTANCE == null) {
            INSTANCE = new TasksRepository(tasksRemoteSourcee);
        }
        return INSTANCE;
    }


    @Override
    public void loginTask(@NonNull String name, @NonNull String password) {
        mTasksRemoteSource.loginTask(name, password);
    }
}
