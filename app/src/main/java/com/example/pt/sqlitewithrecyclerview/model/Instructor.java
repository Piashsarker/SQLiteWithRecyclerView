package com.example.pt.sqlitewithrecyclerview.model;

public class Instructor {
	private String id;

	private String name;
	private String dept_name;
	private int salary;
	private String email;




	public Instructor() {

	}
	public Instructor(String id){
		this.id = id;
	}

	public Instructor(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Instructor(String name, String dept_name, int salary) {
		this.name = name;
		this.dept_name = dept_name;
		this.salary = salary;
	}
	public Instructor(String name , String email, String dept , int salary){
		this.name = name;
		this.email = email;
		this.dept_name = dept;
		this.salary = salary;
	}

	public Instructor(String id, String name, String dept_name) {
		this.id = id;
		this.name = name;
		this.dept_name = dept_name;
	}


	public Instructor(String id, String name,String email, String dept_name, int salary) {
		this.id = id;
		this.name = name;
		this.dept_name = dept_name;
		this.salary = salary;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
