package com.my.shareonfoot.social.vo;

import org.springframework.stereotype.Component;

@Component("heartVO")
public class HeartVO {

	private String boardNo, hearterID, regDate;
	
	//�깮�꽦�옄
	public HeartVO() {
		System.out.println("HeartVO �깮�꽦�옄 �샇異�");
	}	

	

	public String getBoardNo() {
		return boardNo;
	}



	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}



	public String getHearterID() {
		return hearterID;
	}



	public void setHearterID(String hearterID) {
		this.hearterID = hearterID;
	}



	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

}
