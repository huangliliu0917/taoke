package com.kunion.taoke.model.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.kunion.taoke.TKApp;
import com.kunion.taoke.model.TasksSource;
import com.kunion.taoke.model.remote.rest.RestService;
import com.kunion.taoke.model.remote.rest.info.UserGroupInfo;
import com.kunion.taoke.model.remote.rest.req.GetSaleByPage;
import com.kunion.taoke.model.remote.rest.resp.CheckVersionResp;
import com.kunion.taoke.model.remote.rest.resp.GroupsResp;
import com.kunion.taoke.model.remote.rest.resp.LoginResp;
import com.kunion.taoke.model.remote.rest.resp.SaleResp;
import com.kunion.taoke.model.remote.rest.resp.StatsResp;
import com.kunion.taoke.model.remote.rest.resp.StringResp;


import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Field;

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

    @Override
    public Observable<GroupsResp> getGroups(int page, int size) {
        RestService service = ServiceGenerator.RestService();
        return service.getGroups(page, size);
    }

    @Override
    public Observable<StringResp> getDstGroup() {
        RestService service = ServiceGenerator.RestService();
        return service.getDstGroup();
    }

    @Override
    public Observable<CheckVersionResp> checkVersion(int version) {
        RestService service = ServiceGenerator.RestService();
        return service.checkVersion(version).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<SaleResp> getSalesByPage(int page, int size, @NonNull List<String> groups) {
        RestService service = ServiceGenerator.RestService();

        return service.getSalesByPage(new GetSaleByPage(page, size, groups)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<StatsResp> getEffectByPage(int page, int size, @NonNull String group) {
        RestService service = ServiceGenerator.RestService();

        return service.getEffectByPage(page, size, group).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
