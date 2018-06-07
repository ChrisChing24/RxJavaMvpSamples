package com.xiaoxin.demo.global;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by yuanlili on 2016/12/27.
 * okhttp配置
 */

public class OkhttpSet {
    public OkHttpClient set(Context context) {
        HttpLoggingInterceptor interceptor2 = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("RetrofitLog", "retrofitBack = " + message);
            }
        });
        interceptor2.setLevel(HttpLoggingInterceptor.Level.BODY);

        //拦截器，公共token，后台版本，不用每次接口都写了
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
//                        .newBuilder()
//                        .addHeader("Content-Type", "application/json;charset=UTF-8")
//                        .addHeader("token", ConstantUtils.token)
//                        .build();
                HttpUrl url = request.url().newBuilder()
                        .addQueryParameter("Content-Type", "application/json;charset=UTF-8")
                        .addQueryParameter("token", Constant.token)
                        .addQueryParameter("v", Constant.version)
                        .build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        };


        return new OkHttpClient.Builder()
                .connectTimeout(15000L, TimeUnit.MILLISECONDS)       //设置连接超时
                .readTimeout(15000L, TimeUnit.MILLISECONDS)          //设置读取超时
                .writeTimeout(15000L, TimeUnit.MILLISECONDS)         //设置写入超时
                .cache(new Cache(context.getCacheDir(), 10 * 1024 * 1024))   //设置缓存目录和10M缓存
                .addInterceptor(interceptor)    //添加日志拦截器（该方法也可以设置公共参数，头信息）
                .addInterceptor(interceptor2)
                .build();
    }
}
