package com.kunion.taoke.model;

import com.kunion.taoke.model.remote.TasksRemoteSource;

/**
 * Created by Administrator on 2017/10/10.
 */

public class ModelHelper {

    public static TasksRepository providerTasksRepository() {
        return TasksRepository.getInstance(TasksRemoteSource.getInstance());
    }
}
