package com.example.android004_database;

import android.content.Context;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.android004_database.model.Person;
import com.example.android004_database.service.OtherDataBaseService;

import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class OtherDateBaseTest {

    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    public void testSave() {
        Person p = new Person(21, "大大", 18);
        OtherDataBaseService service = new OtherDataBaseService(appContext);
        service.save(p);

    }

    public void testupdate() {
        Person p = new Person(21, "小小", 18);
        OtherDataBaseService service = new OtherDataBaseService(appContext);
        service.update(p);

    }

    // 获取某一个记录
    public void testFindById() {
        OtherDataBaseService service = new OtherDataBaseService(appContext);
        Person p = service.find(21);

        Log.i("PersonService", p.toString());

    }

    public void testFindAll() {
        OtherDataBaseService service = new OtherDataBaseService(appContext);
        List<Person> ps = service.findAll(0, 10);
        for (Person p : ps) {
            Log.i("PersonService", p.toString());
        }

    }

    // 测试聚合函数
    public void testgetCount() {
        OtherDataBaseService service = new OtherDataBaseService(appContext);

        Log.i("PersonService", " 共有 : " + service.getCount() + "  人");

    }


}
