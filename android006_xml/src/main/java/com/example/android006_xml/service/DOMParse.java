package com.example.android006_xml.service;

import com.example.android006_xml.model.User;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMParse { //创建一个xml的Dom方式的解析类

    public static List<User> getUsers(InputStream inputStream) throws Exception {
        List<User> users = new ArrayList<User>();

        //创建DOM解析器
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        //Dom方式的解析,装在xml文件
        Document doc = builder.parse(inputStream);//进行xml文件的解析操作

        //获取根元素
        Element root = doc.getDocumentElement();
        //获取所有的子节点链表
        NodeList nodelist = root.getElementsByTagName("user");
        //遍历此节点列表
        for (int i = 0; i < nodelist.getLength(); i++) {
            User u = new User();
            Element userElem = (Element) nodelist.item(i);
            //获取属性
            u.setId(new Short(userElem.getAttribute("id")));
            //获取user的子节点name和age
            NodeList userChild = userElem.getChildNodes();
            for (int j = 0; j < userChild.getLength(); j++) {
                if (userChild.item(j).getNodeType() == Node.ELEMENT_NODE) { //判断当前的节点是否是元素类型
                    Element e = (Element) userChild.item(j);
                    if ("name".equals(e.getNodeName())) {
                        u.setName(e.getFirstChild().getNodeValue());//获取文本节点的值
                    } else if ("age".equals(e.getNodeName())) {
                        u.setAge(new Short(e.getFirstChild().getNodeValue()));
                    } else {
                        StringBuffer stringBuffer = new StringBuffer();
                        for (int k = 1; k < e.getChildNodes().getLength(); k++) {
                            if (e.getChildNodes().item(k).getNodeType() == Node.ELEMENT_NODE) {
                                Element e1 = (Element) e.getChildNodes().item(k);
                                stringBuffer.append(" \" ");
                                stringBuffer.append(e1.getFirstChild().getNodeValue());
                                stringBuffer.append(" \" ");
                            }
                            u.setSkill(stringBuffer.toString());
                        }

                    }


                }

            }

            users.add(u);
        }


        return users;


    }

}
