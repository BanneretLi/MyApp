package com.example.a51044.mymoni.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtils {
    public static  String getString(String urlStr) throws Exception {
        URL url=new URL(urlStr);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setConnectTimeout(5000);
        InputStream inputStream = urlConnection.getInputStream();
        String str=getStr(inputStream);
        return str;
    }

    private static String getStr(InputStream inputStream) throws IOException {
        BufferedReader read=new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer buffer=new StringBuffer();
        String con="";
        while ((con=read.readLine())!=null)
        {
            buffer.append(con);
        }
        read.close();
        return buffer.toString();
    }
}
