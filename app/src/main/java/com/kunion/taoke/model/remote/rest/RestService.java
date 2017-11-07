package com.kunion.taoke.model.remote.rest;

import com.kunion.taoke.model.remote.rest.req.GetSaleByPage;
import com.kunion.taoke.model.remote.rest.resp.CheckVersionResp;
import com.kunion.taoke.model.remote.rest.resp.GroupsResp;
import com.kunion.taoke.model.remote.rest.resp.LoginResp;
import com.kunion.taoke.model.remote.rest.resp.SaleResp;
import com.kunion.taoke.model.remote.rest.resp.StatsResp;
import com.kunion.taoke.model.remote.rest.resp.StringResp;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

//    @POST("item/getSalesByPage")
//    Observable<SaleResp> getSalesByPage(@Body ("page") int page, @Query("size") int size, @Query("groups")List<String> groups);

    @POST("item/getSalesByPage")
    Observable<SaleResp> getSalesByPage(@Body GetSaleByPage body);

    @FormUrlEncoded
    @POST("item/getEffectByPage")
    Observable<StatsResp> getEffectByPage(@Field("page")int page, @Field("size")int size, @Field("group")String group);
}
