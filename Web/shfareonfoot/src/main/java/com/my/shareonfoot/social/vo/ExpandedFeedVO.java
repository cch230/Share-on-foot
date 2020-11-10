package com.my.shareonfoot.social.vo;
import org.springframework.stereotype.Component;

@Component("expandedFeedVO")
public class ExpandedFeedVO {

	private String myID, writerID, writerName, pfImagePath, imagePath, contents, regDate;
	private int numHeart, numComment, boardNo;
	
	

	//�엫�떆
	private int pageStart =-1;
	private int pageSize =-1;
	

	//�깮�꽦�옄
	public ExpandedFeedVO() {
		System.out.println("FeedVO �깮�꽦�옄 �샇異�");
	}


	//寃뚰꽣&�꽭�꽣
	


	public String getMyID() {
		return myID;
	}


	public void setMyID(String myID) {
		this.myID = myID;
	}

	
	public String getWriterID() {
		return writerID;
	}


	public void setWriterID(String writerID) {
		this.writerID = writerID;
	}


	public String getWriterName() {
		return writerName;
	}


	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}


	public String getPfImagePath() {
		return pfImagePath;
	}


	public void setPfImagePath(String pfImagePath) {
		this.pfImagePath = pfImagePath;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public String getContents() {
		return contents;
	}


	public void setContents(String contents) {
		this.contents = contents;
	}


	public String getRegDate() {
		return regDate;
	}


	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}


	public int getNumHeart() {
		return numHeart;
	}


	public void setNumHeart(int numHeart) {
		this.numHeart = numHeart;
	}


	public int getNumComment() {
		return numComment;
	}


	public void setNumComment(int numComment) {
		this.numComment = numComment;
	}


	public int getBoardNo() {
		return boardNo;
	}


	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
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