package com.socialnetwork.main.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
    @Id
    private int id;
    private String name;
    private String department;
    private int backlogs;
    private double percentage;
    public Student() {
        // Default constructor
    }

    public Student(String name) {
        this.name = name;
    }
	public Student(int i, String string, String string2, int j, double d) {
		// TODO Auto-generated constructor stub
	}

//	public Student(int i, String string, String string2, int j, double d) {
//		// TODO Auto-generated constructor stub
//	}

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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getBacklogs() {
		return backlogs;
	}
	public void setBacklogs(int backlogs) {
		this.backlogs = backlogs;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
    
}
