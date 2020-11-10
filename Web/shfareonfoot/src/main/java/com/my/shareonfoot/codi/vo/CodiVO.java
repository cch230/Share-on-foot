package com.my.shareonfoot.codi.vo;
import org.springframework.stereotype.Component;

@Component("codiVO")
public class CodiVO {
	
	private int codiNo; // PRIMARY KEY. AUTO INCREMENT
	private String codiName;
	private String season; //怨꾩젅
	private String place; //�옣�냼
	private String buyDate; //援ъ엯 �궇吏�
	private String comment; //肄붾찘�듃 500�옄
	private String favorite; //利먭꺼李얘린 �뿬遺�, DEFAULT 'no'
	private String fileName;
	private String filePath;
	private String addedDate; //�벑濡앹씪, DEFAULT CURRENT_TIMESTAMP
	private String userID; // FOREIGN KEY(CLOSET). not null
	
	//�엫�떆
	private int pageStart =-1;
	private int pageSize =-1;
	

	//�깮�꽦�옄
	public CodiVO() {
		System.out.println("CodiVO �깮�꽦�옄 �샇異�");
	}
	
	public CodiVO(int codiNo) {
		System.out.println("CodiVO �깮�꽦�옄 �샇異�");
		this.codiNo = codiNo;
	}

	public CodiVO(String userID) {
		System.out.println("CodiVO �깮�꽦�옄 �샇異�");
		this.userID = userID;
	}
	
	public CodiVO(String userID, String fileName) {
		System.out.println("CodiVO �깮�꽦�옄 �샇異�");
		this.userID = userID;
		this.fileName = fileName;
	}

	
	//寃뚰꽣&�꽭�꽣
	public int getCodiNo() {
		return codiNo;
	}

	public void setCodiNo(int codiNo) {
		this.codiNo = codiNo;
	}

	public String getCodiName() {
		return codiName;
	}

	public void setCodiName(String codiName) {
		this.codiName = codiName;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFavorite() {
		return favorite;
	}

	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
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
