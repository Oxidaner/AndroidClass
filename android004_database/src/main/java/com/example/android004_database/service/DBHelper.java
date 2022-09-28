package com.example.android004_database.service;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**

 编写一个SQLite的子类,该类用于创建数据库和创建数据表
 此类有一个特点:被动触发执行

 */
public class DBHelper extends SQLiteOpenHelper{

    //该构造方法 用于创建数据库,Android中每一个数据库就是一个数据文件

    private static final String DataBaseName="db_test.db";
    private static final int DataBaseVersion=1;

    public DBHelper(Context context) {
        super(context, DataBaseName, null, DataBaseVersion);

        /*
         * context:当前的Android程序运行的环境
         * DB_name: 当前需要创建的数据库名称
         * CursorFactory:游标工厂,主要是用于获取select的执行结果,但这种方式很少使用
         * version:当前的数据库的版本号
         * */
    }

    //该方法只有在执行read/write方法时,被触发调用,且只执行一次
    public void onCreate(SQLiteDatabase db) {
        //在该方法中创建数据表
        String sql=" create table person( pid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, pname varchar(20), age INTEGER(10) )";
        db.execSQL(sql); //执行当前的sql语句

    }

    //该方法只有在执行数据库更新的时候,被触发调用,且只执行一次
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}








