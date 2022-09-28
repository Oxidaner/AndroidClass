package com.example.android006_xml.model;

public class User {
	
	private int id;
	private String name;
	private int age;
	private String skill;

	public User(int id, String name, int age, String skill) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.skill = skill;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				", skill='" + skill + '\'' +
				'}';
	}

	public User() {
		// TODO Auto-generated constructor stub
	}


}
