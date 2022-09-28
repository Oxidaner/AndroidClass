package com.example.android004_database.service;


import java.util.ArrayList;
import java.util.List;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android004_database.model.Person;

public class PersonService {
    // 创建对person表的服务类
    private DBHelper dbHelper; //引入数据库的辅助类

    //创建数据库
    public PersonService(Context context) {
        this.dbHelper = new DBHelper(context);
        //创建数据库 实际上一个数据文件
    }



    // 1.编写save方法
    public void savePerson(Person p) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();// 读写的方式打开数据库
        String sql = "insert into person(pname,age) values(?,?)";
        db.execSQL(sql, new Object[] { p.getPname(), p.getAge() });

    }

    // 2.delete

    public void delPerson(int pid) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();// 读写的方式打开数据库
        String sql = "delete from person where pid=?";
        db.execSQL(sql, new Object[] { pid });

    }

    // 3.update
    public void updatePerson(Person p) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();// 读写的方式打开数据库
        String sql = "update person set pname=? where pid=?";
        db.execSQL(sql, new Object[] { p.getPname(), p.getPid() });

    }

    // 4.select One
    public Person findById(Integer id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from person where pid=?",
                new String[] { id.toString() });
        if (cursor.moveToFirst()) { // 只能出现一个记录
            @SuppressLint("Range")
            int pid = cursor.getInt(cursor.getColumnIndex("pid"));
            @SuppressLint("Range")
            String name = cursor.getString(cursor.getColumnIndex("pname"));
            @SuppressLint("Range")
            int amount = cursor.getInt(cursor.getColumnIndex("age"));
            return new Person(pid, name, amount);
        }
        return null;
    }

    // 5.select Many
    public List<Person> findAll(Integer offset, Integer maxResult) { // 分页的技巧
        List<Person> ps = new ArrayList<Person>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from person  limit ? , ? ",
                new String[] { offset.toString(), maxResult.toString() });
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int pid = cursor.getInt(cursor.getColumnIndex("pid"));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("pname"));
            @SuppressLint("Range") int amount = cursor.getInt(cursor.getColumnIndex("age"));
            ps.add( new Person(pid, name, amount));
        }
        return ps;
    }

    // 6.function
    public long getCount() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*) from person ",null);
        cursor.moveToFirst();
        return cursor.getLong(0);
    }

}













