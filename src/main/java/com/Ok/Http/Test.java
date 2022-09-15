package com.Ok.Http;

import okhttp3.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test{
    public static String domain = "http://abc.aiqutui.net";
    public static String UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36";
    public static String getPlatformDomain(){
        return domain;
    }
    public static String getUA(){
        return UA;
    }

    public static void main(String[] args) {
        OkHttpClient okHttpClient = new OkHttpClient();
        AbstractRequest abstractRequest = new AbstractRequest("https://www.zhihu.com/login/phone_num");
        OkHttpClient Client = abstractRequest.mOkHttpClient;
//        FormBody loginBody = new FormBody.Builder()
//                .add("_xsrf", "bf284aba4cc706ebfc5ebcba1c4f97fc")
//                .add("password", "cay1314159")
//                .add("captcha_type", "cn")
//                .add("remember_me", "true")
//                .add("phone_num", "15520762775")
//                .build();//账号密码自己填
//        //创建Request请求
//        Request loginRequest = new Request.Builder()
//                .url("https://www.zhihu.com/login/phone_num")
//                .post(loginBody)
//                .build();
//        try{
//            Response response = Client.newCall(loginRequest).execute();
//            System.out.println(new String(response.body().string()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Request attentionRequest = new Request.Builder()
//                .url("https://www.zhihu.com/people/chen-yan-xiang-83/followees")
//                .build();
//        try{
//            Response response2 = okHttpClient.newCall(attentionRequest).execute();
//            System.out.println(new String(response2.body().string()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=gbk"),"moduleid=2&username="+"18207113440"+"&password="+"qing87776981"+"&submit=登 录");

        Request request = new Request.Builder()
                .url(getPlatformDomain() + "/member/login.php")
                .header("User-Agent", getUA())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Referer", getPlatformDomain() + "/member/login.php")
                .post(body)
                .build();
        try{
            Response response = Client.newCall(request).execute();
//            System.out.println(unicodeDecode(new String(response.body().string())));
            System.out.println("***********************************************************************");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Request request2 = new Request.Builder()
                .url(getPlatformDomain() + "/member/")
                .header("User-Agent", getUA())
                .header("X-Requested-With", "XMLHttpRequest")
                .header("Accept", "text/html, */*; q=0.01")
                .build();
        try {
            Response response2 = Client.newCall(request2).execute();
            String result2 = response2.body().string();
            System.out.println(unicodeDecode(result2));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static String urlDecode(String params){
        try {
            return URLDecoder.decode(params,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }
        return params;
    }
    public static String unicodeDecode(String string) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(string);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            string = string.replace(matcher.group(1), ch + "");
        }
        return string;
    }

}
