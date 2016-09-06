package com.web.entity;

import java.io.Serializable;

public class User implements Serializable{
	
	private int uid;
	
	private String userName;
	
	private String userPassword;

	public User(int uid,String userName, String userPassword) {
		this.uid = uid;
		this.userName = userName;
		this.userPassword = userPassword;
	}

	
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public User() {}
	
}
