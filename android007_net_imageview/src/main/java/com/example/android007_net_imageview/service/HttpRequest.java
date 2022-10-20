package com.example.android007_net_imageview.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

//编写一个http的请求的服务类 以文本的方式 进行网络的访问
public class HttpRequest {

    //按照访问的内容 分成文本方式
    public String httpRequest(String path) throws Exception{
        //按照文本方式访问Web信息
        //创建访问的地址对象
        URL url=new URL(path);
        //打开URL链接
        URLConnection conn=url.openConnection();
        //类型转换成为 httpURLConnection
        HttpURLConnection connection=(HttpURLConnection) conn;

        //设定请求的方式post/get
        connection.setRequestMethod("GET");
        //设置等待的时间
        connection.setConnectTimeout(5*1000);

        //开启流通道 进行读写
        InputStream input=connection.getInputStream();
        byte[] data=readInputStream(input);


        //转换成为String
        String htmlString=new String(data, "ISO_8859-1");
        return htmlString;

    }
    public byte[] image(String path) throws Exception{
        //按照文本方式访问Web信息
        //创建访问的地址对象
        URL url=new URL(path);
        //打开URL链接
        URLConnection conn=url.openConnection();
        //类型转换成为 httpURLConnection
        HttpURLConnection connection=(HttpURLConnection) conn;

        //设定请求的方式post/get
        connection.setRequestMethod("GET");
        //设置等待的时间
        connection.setConnectTimeout(5*1000);

        //开启流通道 进行读写
        InputStream input=connection.getInputStream();
        byte[] data=readInputStream(input);
        System.out.println("12345678"+data.length);
        return data;

    }









    //创建一个通用的读取的方法
    public  byte[]	readInputStream(InputStream input) throws IOException{
        //避免内容过多 ,进行循环读取操作
        //创建一个移动端的内存输出流
        ByteArrayOutputStream out=new ByteArrayOutputStream();//存在于手机的内存中
        //创建一个人缓冲区
        byte[] buffer=new byte[1000];
        int len=0;
        while((len=input.read(buffer))!=-1){
            out.write(buffer,0,len);
        }
        input.close();
        return out.toByteArray();

    }



    //2进制的方法
}
