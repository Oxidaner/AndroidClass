//package com.example.android002.file;
//
//
//
//import android.content.ContentProvider;
//import android.content.ContentResolver;
//import android.content.ContentValues;
//import android.database.Cursor;
//import android.net.Uri;
//import android.test.AndroidTestCase;
//import android.util.Log;
//
//public class AccessContenyProviderTest  extends Test{
//    /**
//     *
//     *针对于数据库的文件方式的CRUD操作
//     */
//
//
//
//    //测试删除方法
//    public void testDelete() throws Exception{
//        //获取手机中的内容提供者
//        ContentResolver resolver = this.getContext().getContentResolver();//获取全部的内容提供者
//        //编写请求的URI
//        Uri deleteURI=Uri.parse("content://com.provideies.personProvider/person/1");
//        resolver.delete(deleteURI, null, null);
//
//    }
//    public void testSave() throws Exception{
//        //获取手机中的内容提供者
//        ContentResolver resolver=this.getContext().getContentResolver();//获取全部的内容提供者
//        //编写请求的URI
//        Uri insertURI=Uri.parse("content://com.provideies.personProvider/person");
//        ContentValues values=new ContentValues();
//        values.put("name", "小王");
//        values.put("amount", 10000);
//        resolver.insert(insertURI, values);
//
//    }
//
//    public void testupdate() throws Exception{
//        //获取手机中的内容提供者
//        ContentResolver resolver=this.getContext().getContentResolver();//获取全部的内容提供者
//        //编写请求的URI
////		Uri deleteURI=Uri.parse("content://com.provideies.personProvider/person/2");
//        Uri deleteURI=Uri.parse("content://com.provideies.personProvider/person/2");
//        ContentValues values=new ContentValues();
//        values.put("amount", 10);
//        resolver.update(deleteURI, values, null, null);
//
//    }
//    public void testfinde() throws Exception{
//        //获取手机中的内容提供者
//        ContentResolver resolver=this.getContext().getContentResolver();//获取全部的内容提供者
//        //编写请求的URI
////		Uri findURI=Uri.parse("content://com.provideies.personProvider/person");
//        Uri findURI=Uri.parse("content://com.provideies.personProvider/person/2");
//        Cursor c = resolver.query(findURI, null, null, null, "name desc");
//        while (c.moveToNext()) {
//            int id = c.getInt(c.getColumnIndex("personid"));
//            String n = c.getString(c.getColumnIndex("name"));
//            int amount = c.getInt(c.getColumnIndex("amount"));
//            Log.i("query", id+"   "+n+"    "+amount);
//        }
//
//
//    }
//
//
//}
