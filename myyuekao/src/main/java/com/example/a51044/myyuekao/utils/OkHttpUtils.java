package com.example.a51044.myyuekao.utils;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpUtils {
    private OkHttpClient okHttpClient=new OkHttpClient();
    public void OkHttpUtils()
    {
        okHttpClient=new OkHttpClient();
    }

    public static OkHttpUtils getInstance()
    {
        return OkHttpHolder.okutils;
    }

    static class OkHttpHolder
    {
        private static final OkHttpUtils okutils=new OkHttpUtils();
    }

    public void getAsync(String url, Callback callback)
    {
        Request request=new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
