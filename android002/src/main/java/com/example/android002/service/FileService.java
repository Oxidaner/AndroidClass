package com.example.android002.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import android.content.Context;
import android.os.Environment;

public class FileService {
    // 编写一个File操作类
    /**
     * 以私有的方式 读写文件 什么是私有方式: 创建的文件只能由创建它的Activity进行读取,其他的应用程序不能读取
     */

    // 创建一个手机容器对象,当前手机容器,我们将当前运行的Activity视为容器对象
    private Context context;
    // 创建一个构造方法,给容器对象赋值
    public FileService() {
        // TODO Auto-generated constructor stub
    }
    public FileService(Context context) {//该容器对象 指的就是当前的Activity
        this.context = context;
    }
    //文件的保存
    public void save(String filename, String content) throws Exception {
        // 默认的情况下 文件的保存使用2进制的方式
        // 获取文件的输出流
        // 此时需要一个手机提供给我一个 标准的当前的环境对象,通过该环境对象进行文件的保存
        FileOutputStream outputStream = this.context.openFileOutput(filename, Context.MODE_PRIVATE);
        // mode:文件保存的操作的模式 私有和共有的设置
        /**
         * model的取值 ,决定了创建的文件的访问权限 有4种方式
         */
        // 向外进行文件的输出
        outputStream.write(content.getBytes());// 将文件的内容由字符串转换成为字节进行输出
        outputStream.close();
    }
    //文件的读取
    public String readFile(String filename) throws Exception{
        //创建一个指向手机内部的输入流
        FileInputStream inputStream=this.context.openFileInput(filename);//此时的filename 要求是包含路径的
        //文件的读取--内存流byteArrayOutStream
        //创建一个缓冲区 1K
        byte buf[] =new byte[1024];
        int len=0;
        //使用内存流
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        while ((len=inputStream.read(buf))!=-1) {
            outputStream.write(buf,0,len);
        }
        //获取整个文件内容
        byte[] data=outputStream.toByteArray();//整个文件的2进制信息
        outputStream.close();
        inputStream.close();
        return new String(data);

    }
    // 问题:如何进行文件的追加
    public void saveAppend(String filename, String content) throws Exception {

        FileOutputStream outStream = context.openFileOutput(filename,
                Context.MODE_APPEND);
        // mode:文件保存的操作的模式

        outStream.write(content.getBytes());
        outStream.close();

    }

    // 问题:体积较大的文件如何保存 保存在SD卡上
    // 前提的要求: 当前的android程序 必须具有向SDCard写入的权限
    public void saveToSDCard(String filename, String content) throws Exception {
        // 创建一个指向SDCard的文件的对象
        File file = new File(Environment.getExternalStorageDirectory(), filename);
        // path:指向SDCard 此处文件的创建是属于业务层次
        FileOutputStream outStream = new FileOutputStream(file, true);// 追加的编写
        outStream.write(content.getBytes());
        outStream.close();

    }

    // 公有文件的保存 当前的Android项目创建的文件,可以由其他的Android项目进行读写
    // 只读Readable
    // 读写Writeable
    public void saveRW(String filename, String content) throws Exception {
        FileOutputStream outStream = context.openFileOutput(filename,
                Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
        /**
         * Context.MODE_WORLD_READABLE : 允许外部程序读取
         * Context.MODE_WORLD_WRITEABLE : 允许外部程序修改
         */
        outStream.write(content.getBytes());
        outStream.close();

    }

}
