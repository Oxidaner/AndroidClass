package com.example.android002.file;


import com.service.FileService;

import android.test.AndroidTestCase;
import android.util.Log;

public class FileTest extends AndroidTestCase{//将android的测试类理解成一个Activity

    //测试文件的读入
    public void testReadFile()throws Exception{
        FileService service=new FileService(this.getContext());
        String result=service.readFile("testFile.txt"); //因为是当前的项目 ,因此可以忽略路径
        Log.i("FileTest", result);
    }


    //测试私有方法的文件的保存
    //复写
    public void testSave() throws Exception{
        //生成文件服务类对象
        FileService service=new FileService(this.getContext());
        service.save("testFile.txt", "www.baidu2222.com");
    }
    //追加保存
    public void testSaveAppend() throws Exception{
        //生成文件服务类对象
        FileService service=new FileService(this.getContext());
        service.saveAppend("testFile.txt", "试试追加的效果");
    }
    //SDCard的保存
    public void testSaveToSDCard() throws Exception{
        //生成文件服务类对象
        FileService service=new FileService(this.getContext());
        service.saveToSDCard("testFile.txt", "你好 , 完成的不错");
    }
    //公共文件的保存
    public void testRW() throws Exception{
        //生成文件服务类对象
        FileService service=new FileService(this.getContext());
        service.saveRW("testFile.txt", "测试外部程序的读写操作");
    }
}
