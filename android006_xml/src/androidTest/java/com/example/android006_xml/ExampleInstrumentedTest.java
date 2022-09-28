package com.example.android006_xml;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.android006_xml.model.User;
import com.example.android006_xml.service.DOMParse;
import com.example.android006_xml.service.Pull;
import com.example.android006_xml.service.SaxParse;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private Context context;

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.android006_xml", appContext.getPackageName());
    }

    private static final String Tag = "XmlTest";

    @Test
    public void testDOMParse() throws Throwable {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("assets/test.xml");
//        InputStream stream=getClass().getClassLoader().getResourceAsStream("res/xml/test.xml");
        List<User> list = DOMParse.getUsers(stream);
        for (User user : list) {
            Log.i(Tag, user.toString());
        }
    }


    @Test
    public void testSAXParse() throws Throwable {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("test.xml");
        List<User> list = new SaxParse().getUsers(stream);
        for (User user : list) {
            Log.i(Tag, user.toString());
        }
    }

    @Test
    public void testPullParse() throws Throwable {


        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        InputStream stream = getClass().getClassLoader().getResourceAsStream("assets/test.xml");
        List<User> list = Pull.getUsers(stream);
        for (User user : list) {
            Log.i(Tag, user.toString());
        }
    }

//    生成xml的测试
    @Test
    public void pullxmlSave() throws Exception {
        List<User> users = new ArrayList<User>();
        users.add(new User(25, "11", 25," "));
        users.add(new User(251, "112", 252," "));
        users.add(new User(252, "113", 253," "));
        users.add(new User(253, "114", 254," "));
        //输出到手机
        //	File file=new File(this.getContext().getFilesDir(),"user.xml");
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FileOutputStream stream = appContext.openFileOutput("user.xml", MODE_PRIVATE);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));

        //StringWriter writer=new StringWriter();
        Pull.saveXML(users, writer);
        //	Log.i(Tag, writer.toString());

    }
}