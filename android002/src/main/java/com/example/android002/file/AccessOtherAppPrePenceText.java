package com.example.android002.file;

import android.content.Context;
import android.content.SharedPreferences;
import android.test.AndroidTestCase;
import android.util.Log;
import junit.framework.TestCase;

public class AccessOtherAppPrePenceText extends AndroidTestCase {

    public void testAccessAppPerference() throws Exception {
        // 如何进行文件的定位
        /*
         * String path="data/data/com.share/shared_prefs/param.xml"; new
         * file(path);
         */

        //由于属性文件的特殊性,可以用过容器进行文件的定位
        Context contextOther=getContext().createPackageContext("com.share", Context.CONTEXT_IGNORE_SECURITY);
        /*
         * packageName:指定的资源文件的所在的包
         * flags:读取该文件的方式
         * */
        SharedPreferences preferences = contextOther.getSharedPreferences("param", Context.MODE_PRIVATE);
        String name=preferences.getString("name", "");
        int age=preferences.getInt("age", 20);
        Log.i("AccessOtherAppPrePenceText", "hahah");
        Log.i("AccessOtherAppPrePenceText", "name= "+name +"  , age= "+age);

    }

}
