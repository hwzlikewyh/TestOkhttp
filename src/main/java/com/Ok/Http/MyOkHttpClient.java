package com.Ok.Http;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class MyOkHttpClient {
    static OkHttpClient getOKHttp(String urlName, CookieStore cookieStore){
        if (cookieStore==null){
            return null;
        }
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .cookieJar(cookieStore)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .cookieJar(new CookieJarImpl(cookieStore))
                .build();

        return mOkHttpClient;

    }
}
