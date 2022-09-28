package com.example.android004_database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.android004_database.model.Person;
import com.example.android004_database.service.DBHelper;

import java.util.ArrayList;
import java.util.List;

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
        assertEquals("com.example.android004_database", appContext.getPackageName());
    }

    @Test
    public void testDBHelper(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DBHelper dbHelper = new DBHelper(appContext);
        dbHelper.getWritableDatabase();
    }


}