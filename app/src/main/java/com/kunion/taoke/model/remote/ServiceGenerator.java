package com.kunion.taoke.model.remote;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kunion.taoke.BuildConfig;
import com.kunion.taoke.TKApp;
import com.kunion.taoke.model.remote.rest.RestService;
import com.kunion.taoke.util.SharePfeUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Administrator on 2017/10/10.
 */

public class ServiceGenerator  {
    public static final String API_BASE_URL = "http://tk.owngiftc.com";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit mRetrofit;

    public static Retrofit initRetrofit() {

        if(mRetrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClient = httpClient.addInterceptor(logging);
                httpClient.connectTimeout(3, TimeUnit.SECONDS);
                httpClient.readTimeout(2, TimeUnit.SECONDS);
                httpClient.writeTimeout(2, TimeUnit.SECONDS);

                httpClient.interceptors().add(new Interceptor(){
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request request = chain.request();

                        long t1 = System.nanoTime();
                        Log.d("haha", String.format("Sending request %s on %s%n%s",
                                request.url(), chain.connection(), request.headers()));

                        Response response = chain.proceed(request);

                        long t2 = System.nanoTime();
                        Log.d("haha", String.format("Received response for %s in %.1fms%n%s",
                                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

                        return response;
                    }
                });

            } else {
                httpClient.connectTimeout(15, TimeUnit.SECONDS);
                httpClient.readTimeout(15, TimeUnit.SECONDS);
                httpClient.writeTimeout(15, TimeUnit.SECONDS);
            }

            httpClient.interceptors().add(ReadCookiesInterceptor);
            httpClient.interceptors().add(SaveCookiesInterceptor);


            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
//                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient.build())
                    .build();

        }
        return mRetrofit;
    }

    public static <S> S  createService(Class<S> serviceClass) {
        return initRetrofit().create(serviceClass);
    }

    public static RestService RestService() {
        return createService(RestService.class);
    }


    static Interceptor ReadCookiesInterceptor = new Interceptor() {

        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            String cookie = (String)TKApp.SPUtil.getValue(SharePfeUtils.PREF_COOKIES, "");
            builder.addHeader("Cookie", cookie);
            Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp

            return chain.proceed(builder.build());
        }
    };

    static Interceptor SaveCookiesInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                StringBuffer cookieBuffer = new StringBuffer();

                for (String header : originalResponse.headers("Set-Cookie")) {

                    String[] cookieArray = header.split(";");
                    cookieBuffer.append(cookieArray[0]).append(";");
                }


                TKApp.SPUtil.putValue(SharePfeUtils.PREF_COOKIES, cookieBuffer.toString());
            }
            return originalResponse;
        }
    };
}
