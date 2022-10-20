package com.example.android007_net_imageview.service;

import com.example.android007_net_imageview.tool.StreamTool;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ImageService {
    //编写一个方法 ,接受一个字符串的地址 ,得到byte[]数据
    public static byte[] getImage(String path) throws Exception{
        URL url=new URL(path);
        URLConnection conn = url.openConnection();
        HttpURLConnection connection=(HttpURLConnection) conn;
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5*1000);
        InputStream input = connection.getInputStream();//一定采用2进制的


        return StreamTool.readInputStream(input);


    }

    public static byte[] getImage2(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection httpURLconnection = (HttpURLConnection)url.openConnection();
        httpURLconnection.setRequestMethod("GET");
        httpURLconnection.setReadTimeout(6*1000);
        InputStream in = null;
        byte[] b = new byte[1024];
        int len = -1;
        if (httpURLconnection.getResponseCode() == 200) {
            in = httpURLconnection.getInputStream();
            byte[] result = StreamTool.readInputStream(in);
            in.close();
            return result;

        }
        return null;
    }
}
