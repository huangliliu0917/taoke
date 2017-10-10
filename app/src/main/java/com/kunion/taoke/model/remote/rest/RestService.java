package com.kunion.taoke.model.remote.rest;

import com.kunion.taoke.model.remote.rest.resp.LoginResp;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/10/10.
 */

public interface RestService {

    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginResp> login(@Field("username") String username, @Field("password") String password);

}
