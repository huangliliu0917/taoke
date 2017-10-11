package com.kunion.taoke.model;

import android.support.annotation.NonNull;

import com.kunion.taoke.model.remote.rest.info.CheckVersion;
import com.kunion.taoke.model.remote.rest.resp.CheckVersionResp;
import com.kunion.taoke.model.remote.rest.resp.GroupsResp;
import com.kunion.taoke.model.remote.rest.resp.LoginResp;
import com.kunion.taoke.model.remote.rest.resp.StringResp;

import io.reactivex.Observable;
import retrofit2.http.Field;

/**
 * Created by Administrator on 2017/10/10.
 */

public interface TasksSource {

    Observable<LoginResp> loginTask(@NonNull String name, @NonNull String password);

    Observable<GroupsResp> getGroups(@NonNull int page, @NonNull int size);

    Observable<StringResp> getDstGroup();

    Observable<CheckVersionResp> checkVersion(@NonNull int version);
}
