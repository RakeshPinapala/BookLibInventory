package com.aryans.library.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"userId", "columnsMap"})
public class UserModel implements Model {
	long userId;
	String userType;
	boolean filled;
	String userName;
	String password;
	long parentUserId;
	
	//Need to add an entry for each column added.
	private static final Map<String, String> COLUMN_MAP = new HashMap<String,String>() {{
		put("userId","USER_ID");
		put("userType","USER_TYPE");
		put("filled","FILLED");
		put("password","PASSWORD");
		put("parentUserId","PARENT_USER_ID");
		put("userName","USER_NAME");
	}};
	
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
	
	@Override
	public Map<String, String> getColumnsMap() {
		return COLUMN_MAP;
	}
}
