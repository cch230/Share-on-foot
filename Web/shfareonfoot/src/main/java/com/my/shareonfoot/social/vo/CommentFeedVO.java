package com.my.shareonfoot.social.vo;
import org.springframework.stereotype.Component;

@Component("commentFeedVO")
public class CommentFeedVO {

	private String commenterID, commenterName, pfImagePath, contents, regDate;
	private int numGood, commentNo;
	

	//�엫�떆
	private int pageStart =-1;
	private int pageSize =-1;
	

	//�깮�꽦�옄
	public CommentFeedVO() {
		System.out.println("FeedVO �깮�꽦�옄 �샇異�");
	}


	
	//寃뚰꽣&�꽭�꽣
	public String getCommenterID() {
		return commenterID;
	}


	public void setCommenterID(String commenterID) {
		this.commenterID = commenterID;
	}


	public String getCommenterName() {
		return commenterName;
	}


	public void setCommenterName(String commenterName) {
		this.commenterName = commenterName;
	}


	public String getPfImagePath() {
		return pfImagePath;
	}


	public void setPfImagePath(String pfImagePath) {
		this.pfImagePath = pfImagePath;
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


	public int getNumGood() {
		return numGood;
	}


	public void setNumGood(int numGood) {
		this.numGood = numGood;
	}


	public int getCommentNo() {
		return commentNo;
	}


	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
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