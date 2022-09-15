package com.Ok.Http;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CookieStore implements CookieJar {
    private final Map<String, List<Cookie>> cookiesMap = new HashMap<String, List<Cookie>>();

    @NotNull
    @Override
    public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
        List<Cookie> cookiesList = cookiesMap.get(httpUrl.host());
        return cookiesList != null ? cookiesList : new ArrayList<Cookie>();
    }

    @Override
    public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {
        String host = httpUrl.host();
        List<Cookie> cookiesList = cookiesMap.get(host);
        if (cookiesList != null){
            cookiesMap.remove(host);
        }
        cookiesMap.put(host, list);
    }
}
