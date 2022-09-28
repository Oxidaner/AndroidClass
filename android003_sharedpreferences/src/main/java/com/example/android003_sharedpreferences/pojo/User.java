package com.example.android003_sharedpreferences.pojo;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private int age;
    private String sex;
    private String hobby;
    private String position;
    private String text;

    public User(String name, int age, String sex, String hobby, String position, String text) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.hobby = hobby;
        this.position = position;
        this.text = text;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", hobby='" + hobby + '\'' +
                ", position='" + position + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
