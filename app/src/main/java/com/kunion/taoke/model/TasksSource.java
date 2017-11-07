package com.kunion.taoke.model;

import android.support.annotation.NonNull;

import com.kunion.taoke.model.remote.rest.info.CheckVersion;
import com.kunion.taoke.model.remote.rest.resp.CheckVersionResp;
import com.kunion.taoke.model.remote.rest.resp.GroupsResp;
import com.kunion.taoke.model.remote.rest.resp.LoginResp;
import com.kunion.taoke.model.remote.rest.resp.SaleResp;
import com.kunion.taoke.model.remote.rest.resp.StatsResp;
import com.kunion.taoke.model.remote.rest.resp.StringResp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;

/**
 * Created by Administrator on 2017/10/10.
 */

public interface TasksSource {

    Observable<LoginResp> loginTask(@NonNull String name, @NonNull String password);

    Observable<GroupsResp> getGroups(int page, int size);

    Observable<StringResp> getDstGroup();

    Observable<CheckVersionResp> checkVersion(int version);

    Observable<SaleResp> getSalesByPage(int page, int size, @NonNull List<String> groups);

    Observable<StatsResp> getEffectByPage(int page, int size, @NonNull String group);

}
