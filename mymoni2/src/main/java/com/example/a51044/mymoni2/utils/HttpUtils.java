package com.example.a51044.mymoni2.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtils {
    public static String post(String urlStr,String name,String pwd) throws Exception {
        String inputStr="";
        URL url=new URL(urlStr);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);
        OutputStream outputStream = urlConnection.getOutputStream();
        String params="mobile="+name+"&password="+pwd;
        outputStream.write(params.getBytes());
        if(urlConnection.getResponseCode()==200)
        {
            InputStream inputStream = urlConnection.getInputStream();
            inputStr=getStr(inputStream);
        }
        return inputStr;
    }

    private static String getStr(InputStream inputStream) throws Exception {
        BufferedReader read=new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer buffer=new StringBuffer();
        String con="";
        while ((con=read.readLine())!=null)
        {
            buffer.append(con);
        }
        return buffer.toString();
    }

    public static String get(String urlStr) throws Exception {
        URL url=new URL(urlStr);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        String str=getStr(inputStream);
        return str;
    }
}
