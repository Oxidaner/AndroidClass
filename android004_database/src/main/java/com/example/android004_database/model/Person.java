package com.example.android004_database.model;

public class Person {
    private int pid;
    private String pname;
    private int age;


    @Override
    public String toString() {
        return "Person{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", age=" + age +
                '}';
    }

    public Person(int pid, String pname, int age) {
        this.pid = pid;
        this.pname = pname;
        this.age = age;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
