package com.example.android004_database;


import android.content.Context;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.android004_database.model.Person;
import com.example.android004_database.service.DBHelper;
import com.example.android004_database.service.PersonService;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class DateBaseTest {

    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    //编写一个创建数据表的测试方法
    @Test
    public void testCreateBD_Table(){
        //1.如何创建数据库

        DBHelper helper=new DBHelper(appContext);
        //2.通过执行read/write方法进行数据表的创建
        helper.getWritableDatabase();//会自动触发onCreate方法 进行数据表的创建

    }
    @Test
    public void testSave(){
        Person p=new Person(6, "王五", 19);
        PersonService service=new PersonService(appContext);
        service.savePerson(p);


    }
    @Test
    public void testFindAll(){
        PersonService service=new PersonService(appContext);
        List<Person> ps = service.findAll(0, 10);
        for (Person p : ps) {
            Log.i("PersonService", p.toString());
        }

    }

}
