package com.example.android007_net_imageview;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.android007_net_imageview.service.HttpRequest;

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
        assertEquals("com.example.android007_net_imageview", appContext.getPackageName());
    }
    @Test
    public void testNetRequst() throws Exception{
        HttpRequest request=new HttpRequest();
        String htmlString = request.httpRequest("http://10.0.2.2:8080/");
        Log.i("NetTest",htmlString);
    }
    @Test
    public void testNetRequst02() throws Exception{
        HttpRequest request=new HttpRequest();
        String htmlString = request.httpRequest("http://82.156.15.234:8080/index.jsp");
        Log.i("NetTest",htmlString);
    }

}