package com.example.android007_net_imageview.tool;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamTool {
    public static byte[] readInputStream(InputStream in) throws Exception {
        //具体的读取操作
        //如果不进行数据的保存操作,创建一个内存的输出流进行内存保存
        ByteArrayOutputStream out = new ByteArrayOutputStream();//将数据保存到内存中
        //创建缓冲区
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        out.close();
        in.close();

        return out.toByteArray();

    }

}