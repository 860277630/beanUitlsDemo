package com.example.demo.entity;

public class BookOwner {
	
	private String name;
	
	private int age;
	
	private StringBuilder  sex;//非基本类型  非不可变量

	private Books books;

	public BookOwner(String name, int age, StringBuilder sex, Books books) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.books = books;
	}

	public BookOwner() {
		super();
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

	public StringBuilder getSex() {
		return sex;
	}

	public void setSex(StringBuilder sex) {
		this.sex = sex;
	}

	public Books getBooks() {
		return books;
	}

	public void setBooks(Books books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "BookOwner [name=" + name + ", age=" + age + ", sex=" + sex + ", books=" + books + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
