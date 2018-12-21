package com.example.a51044.myapp.utils;

import java.util.HashMap;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtils {
    private OkHttpClient okHttpClient;
    private OkHttpUtils(){
        okHttpClient=new OkHttpClient();
    }

    public static OkHttpUtils getInstance()
    {
        return OkHolder.okUtils;
    }

    static class OkHolder
    {
        private static final OkHttpUtils okUtils=new OkHttpUtils();
    }

    public void getAsync(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }




    public void asyncPost(String mUrl, HashMap<String, String> map, Callback callback) {
        FormBody.Builder body = new FormBody.Builder ();

        for (String key : map.keySet ()) {
            body.add (key, map.get (key));
        }
        Request request = new Request.Builder ().url (mUrl).post (body.build ()).build ();
        okHttpClient.newCall (request).enqueue (callback);
    }
}
