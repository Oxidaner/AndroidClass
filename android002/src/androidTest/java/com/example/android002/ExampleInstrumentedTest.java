package com.example.android002;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.android002.service.FileService;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.android002", appContext.getPackageName());
    }

    @Test
    public void testFileSave()throws Exception{
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FileService service=new FileService(appContext);
        service.save("aaa.txt", "hello a , miximixiba.gyhg b谷歌...");

    }

    @Test
    public void testFileRead()throws Exception{
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FileService service=new FileService(appContext);
        String result = service.readFile("aaa.txt");
        System.out.println(result);
        Log.i("FileServiceTest", result);
    }



    //测试文件的读入
    public void testReadFile()throws Exception{
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FileService service=new FileService(appContext);
        String result=service.readFile("testFile.txt"); //因为是当前的项目 ,因此可以忽略路径
        Log.i("FileTest", result);
    }


    //测试私有方法的文件的保存
    //复写
    @Test
    public void testSave() throws Exception{
        //生成文件服务类对象
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FileService service=new FileService(appContext);
        service.save("testFile.txt", "www.baidu2222.com");
    }
    //追加保存
    @Test
    public void testSaveAppend() throws Exception{
        //生成文件服务类对象
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FileService service=new FileService(appContext);
        service.saveAppend("testFile.txt", "www.126.com");
    }
    //SDCard的保存
    @Test
    public void testSaveToSDCard() throws Exception{
        //生成文件服务类对象
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FileService service=new FileService(appContext);
        service.saveToSDCard("testFile.txt", "你好 , 完成的不错");
    }
    //公共文件的保存
    @Test
    public void testRW() throws Exception{
        //生成文件服务类对象
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FileService service=new FileService(appContext);
        service.saveRW("testFile.txt", "测试外部程序的读写操作");
    }
}