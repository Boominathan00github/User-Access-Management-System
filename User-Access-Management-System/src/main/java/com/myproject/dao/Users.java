package com.myproject.dao;

public class Users {
	
	private int userId;
	private String userName;
	private String passWord;
	private String role;
	
	
	
	public Users(int userId, String userName, String passWord, String role) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.passWord = passWord;
		this.role = role;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	

}
