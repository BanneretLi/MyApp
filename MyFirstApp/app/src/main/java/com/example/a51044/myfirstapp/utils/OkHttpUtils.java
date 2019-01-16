package com.example.a51044.myfirstapp.utils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtils {
    private OkHttpClient okHttpClient;

    public OkHttpUtils() {
        okHttpClient = new OkHttpClient();
    }

    public static OkHttpUtils getInstance() {
        return OkHolder.okutils;
    }


    static class OkHolder {
        private static final OkHttpUtils okutils = new OkHttpUtils();
    }

    public void getAsync(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public void getAsync2(String url, HashMap<String,String>map,Callback callback) {
        FormBody.Builder body = new FormBody.Builder();
        for (String key : map.keySet()) {

            body.add(key, map.get(key));
        }
        Request request = new Request.Builder().url(url).addHeader("userId", map.get("userId")).addHeader("sessionId", map.get("sessionId")).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public void asyncPost(String mUrl, HashMap<String, String> map, Callback callback) {

        FormBody.Builder body = new FormBody.Builder();
        for (String key : map.keySet()) {

            body.add(key, map.get(key));
        }
        Request request = new Request.Builder().url(mUrl).post(body.build()).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public void asyncPost2(String mUrl, HashMap<String, String> map, Callback callback) {

        FormBody.Builder body = new FormBody.Builder();
        for (String key : map.keySet()) {

            body.add(key, map.get(key));
        }
        Request request = new Request.Builder().url(mUrl).addHeader("userId", map.get("userId")).addHeader("sessionId", map.get("sessionId")).post(body.build()).build();
        okHttpClient.newCall(request).enqueue(callback);
    }


    public void doDelete(String url,Map<String,String> map,Callback callback) {
        FormBody.Builder body=new FormBody.Builder();
        for (String key : map.keySet()) {

            body.add(key, map.get(key));
        }
        RequestBody requestBody=body.build();
        Request request=new Request.Builder()
                .url(url)
                .addHeader("userId",map.get("userId"))
                .addHeader("sessionId",map.get("sessionId"))
                .delete(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(callback);
    }


    public void doPut(String url,Map<String,String> map,Callback callback) {
        FormBody.Builder body=new FormBody.Builder();
        for (String key : map.keySet()) {
            body.add(key, map.get(key));
        }
        RequestBody requestBody=body.build();
        Request request=new Request.Builder()
                .url(url)
                .addHeader("userId",map.get("userId"))
                .addHeader("sessionId",map.get("sessionId"))
                .put(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }


}
