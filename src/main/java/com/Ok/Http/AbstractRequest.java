package com.Ok.Http;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class AbstractRequest {
    public OkHttpClient mOkHttpClient;

    private CookieStore cookieStore;
    public AbstractRequest(String urlName){
        cookieStore = new CookieStore();
        mOkHttpClient = MyOkHttpClient.getOKHttp(urlName,cookieStore);
    }

}
