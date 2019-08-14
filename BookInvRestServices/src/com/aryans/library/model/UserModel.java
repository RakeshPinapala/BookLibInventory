package com.aryans.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"userId"})
public class UserModel {
	long userId;
	String userType;
	boolean filled;
	String userName;
	String password;
	long parentUserId;
	
	//Users table columns
	public static final String USER_ID = "USER_ID";
	public static final String USER_TYPE = "USER_TYPE";
	public static final String FILLED = "FILLED";
	public static final String PASSWORD = "PASSWORD";
	public static final String PARENT_USER_ID = "PARENT_USER_ID";
	public static final String USER_NAME = "USER_NAME";
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public boolean isFilled() {
		return filled;
	}
	public void setFilled(boolean filled) {
		this.filled = filled;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getParentUserId() {
		return parentUserId;
	}
	public void setParentUserId(long parentUserId) {
		this.parentUserId = parentUserId;
	}
}
