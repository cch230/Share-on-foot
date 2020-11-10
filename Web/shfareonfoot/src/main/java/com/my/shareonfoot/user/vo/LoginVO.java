package com.my.shareonfoot.user.vo;

import org.springframework.stereotype.Component;

@Component("loginVO")
public class LoginVO {
	private String userID;
	private String pwd;
	
	
	public LoginVO() {
		System.out.println("LoginVO �깮�꽦�옄 �샇異�");
	}
	
	//getter & setter
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
