package com.example.android006_xml.service;

import com.example.android006_xml.model.User;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SaxParse { //轻巧灵活 ,利用率较高


    public List<User> getUsers(InputStream inputStream) throws Exception, SAXException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        UserParse p = new UserParse();//自定义的SAX的解析标准
        parser.parse(inputStream, p); //创建解析的规则
        return p.getUsers();

    }

    //以一个内部类的方式 创建一个SAX的解析标准
    private final class UserParse extends DefaultHandler {
        private List<User> users = null;
        private String Tag = null; //标记当前的读取标志(SAX解析 逐行读取方式)
        private User user = null;

        //借助DefaultHandler类中的方法,读取xml文件

        @Override
        public void startDocument() throws SAXException {
            users = new ArrayList<User>(); //创建一个空的链表

        }

        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException {
            //判断读到的元素节点名
            if ("user".equals(localName)) {
                user = new User();
                user.setId(new Integer(attributes.getValue(0)));
            }
            Tag = localName; //记载当前的读取的标记名
        }

        @Override
        public void characters(char[] ch, int start, int length)
                throws SAXException {
            if (Tag != null) {
                String data = new String(ch, start, length);
                if (Tag.equals("name")) {
                    user.setName(data);

                } else if ("age".equals(Tag)) {
                    user.setAge(new Integer(data));

                }
            }
        }


        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
            if ("user".equals(localName)) {
            }
            users.add(user);
            Tag = null;
        }

        public List<User> getUsers() {
            return users;
        }


    }


}

  