package com.socialnetwork.main.model;

import java.io.Serializable;



import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;


import javax.persistence.Column;

@Entity
@Table(name = "users")
public class User implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; 

	private String userName; 
	@Column(name = "emailId")
	private String emailId;
	private String userrole;
	private String password;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int id, String userName, String emailId, String userrole, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.emailId = emailId;
		this.userrole = userrole;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userRole) {
		this.userrole = userRole;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", emailId=" + emailId + ", userRole=" + userrole
				+ ", password=" + password + "]";
	}
	
}