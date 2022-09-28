package com.example.android006_xml.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

import com.example.android006_xml.model.User;


public class Pull {

    //pull方式的xml的解析操作

    public static List<User> getUsers(InputStream stream)throws Exception{
        List<User> users=null;
        User user=null;
        StringBuffer stringBuffer = null;
        XmlPullParser parser=Xml.newPullParser();
        //创建pull方式的解析器
        parser.setInput(stream, "utf-8"); //读取xml文件
        //pull的解析 是采用 事件的触发方式和Dom4J的结合进行
        int eventType=parser.getEventType();//获取当前的xmdl的读取状态
        while(eventType!=XmlPullParser.END_DOCUMENT){//xml的文档没有结束

            switch (eventType) {

                case XmlPullParser.START_DOCUMENT:
                    users=new ArrayList<User>();
                    break;
                case XmlPullParser.START_TAG: //一个标签的开始
                    //获取标签的名称
                    String name=parser.getName();
                    if("user".equals(name)){
                        user=new User();
                        user.setId(new Integer(parser.getAttributeValue(0)));
                    }
                    if(user!=null){//读取的内容是user标签的子标签

                        if("name".equals(name)){
                            user.setName(parser.nextText().trim());
                        }
                        if("age".equals(name)){
                            user.setAge(new Short(parser.nextText()));
                        }
                        if ("skills".equals(name)){
                            stringBuffer = new StringBuffer();
                        } 
                        if("skill".equals(name)){
                            stringBuffer.append(parser.nextText().trim()+" ");
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if("user".equals(parser.getName())){
                        users.add(user);
                        user=null;
                        break;
                    }
                    if ("skills".equals(parser.getName())){
                        user.setSkill(""+stringBuffer);
                    }



            }
            eventType=parser.next();//读取下一行
        }


        return users;


    }

    //xml创建 --逐行的字符方式的输出
    public static void saveXML(List<User> users,Writer writer) throws Exception{
        //创建xml的生成器
        XmlSerializer serializer=Xml.newSerializer();//pull方式的xml序列话对象
        serializer.setOutput(writer);//设置输出流,字符方式
        serializer.startDocument("utf-8", true);

        serializer.startTag(null, "users");
        //遍历链表
        for (User user : users) {
            serializer.startTag(null, "user");
            serializer.attribute(null, "id", ""+user.getId());

            serializer.startTag(null, "name");
            serializer.text(user.getName());
            serializer.endTag(null, "name");

            serializer.startTag(null, "age");
            serializer.text(""+user.getAge());
            serializer.endTag(null, "age");

            serializer.endTag(null, "user");
        }


        serializer.endTag(null, "users");
        serializer.endDocument(); //关闭序列对象
        writer.flush();
        writer.close();
    }




}
