package com.my.shareonfoot.user.vo;

import org.springframework.stereotype.Component;

@Component("userVO")
public class UserVO {
	private String userID; //�쑀�� �븘�씠�뵒 (45). NOT NULL PRIMARY
	private String nickname; //�땳�꽕�엫 (50)
	private String email; //�씠硫붿씪 (70)
	private String pwd; //鍮꾨�踰덊샇 (45)
	private String gender; //�꽦蹂� (10)
	private String birth; //�깮�뀈�썡�씪 (DATE)
	private String emailChecked; //硫붿씪 泥댄겕 �뿬遺� (45)
	private String pfImageName; //�봽濡쒗븘 �씠誘몄� �뙆�씪紐� (50)
	private String pfImagePath; //�봽濡쒗븘 �씠誘몄� �뙆�씪 寃쎈줈 (80)
	private String pfContents; //�봽濡쒗븘 �냼媛쒓� (300)
	
	//�엫�떆
	private int pageStart =-1;
	private int pageSize =-1;
	
	//�깮�꽦�옄
	public UserVO() {
		System.out.println("UserVO �깮�꽦�옄 �샇異�");
	}
	
	public UserVO(String userID) {
		System.out.println("UserVO �깮�꽦�옄 �샇異�");
		this.userID = userID;
	}

	public UserVO(String userID, String pwd) {
		System.out.println("UserVO �깮�꽦�옄 �샇異�");
		this.userID = userID;
		this.pwd = pwd;
	}
	
	public UserVO(String userID, String nickname, String email) {
		System.out.println("UserVO �깮�꽦�옄 �샇異�");
		this.userID = userID;
		this.nickname = nickname;
		this.email = email;
	}

	public UserVO(String userID, String pwd, String nickname, String email) {
		System.out.println("UserVO �깮�꽦�옄 �샇異�");
		this.userID = userID;
		this.pwd = pwd;
		this.nickname = nickname;
		this.email = email;
	}

	

	//getter & setter
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getEmailChecked() {
		return emailChecked;
	}

	public void setEmailChecked(String emailChecked) {
		this.emailChecked = emailChecked;
	}

	public String getPfImageName() {
		return pfImageName;
	}

	public void setPfImageName(String pfImageName) {
		this.pfImageName = pfImageName;
	}

	public String getPfImagePath() {
		return pfImagePath;
	}

	public void setPfImagePath(String pfImagePath) {
		this.pfImagePath = pfImagePath;
	}

	public String getPfContents() {
		return pfContents;
	}

	public void setPfContents(String pfContents) {
		this.pfContents = pfContents;
	}

	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
