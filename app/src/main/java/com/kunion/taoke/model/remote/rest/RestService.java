package com.kunion.taoke.model.remote.rest;

import com.kunion.taoke.model.remote.rest.resp.CheckVersionResp;
import com.kunion.taoke.model.remote.rest.resp.GroupsResp;
import com.kunion.taoke.model.remote.rest.resp.LoginResp;
import com.kunion.taoke.model.remote.rest.resp.StringResp;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/10/10.
 */

public interface RestService {

    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginResp> login(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("tkacc/gettkaccByPage")
    Observable<GroupsResp> getGroups(@Field("page") int page, @Field("size") int size);

    @GET("tkacc/getDstGroup")
    Observable<StringResp> getDstGroup();

    @FormUrlEncoded
    @POST("tk/checkVersion")
    Observable<CheckVersionResp> checkVersion(@Field("version") int version);
}
