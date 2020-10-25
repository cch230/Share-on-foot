package com.example.shareonfoot.HTTP.VO;

import android.os.Parcel;
import android.os.Parcelable;

public class DetailFeedVO implements Parcelable {

    private String userID, userName, userPfImagePath, userPfContents,
            userNumBoard, userNumFollower, userNumFollowing,
            user_if_following,
            user_following_friendsID, user_followig_friendsName, user_followig_friendsImgPath,
            board_if_hearting,
            boardNo, boardImagePath, boardContents, boardRegDate,
            cloNo, cloLocation, cloKind, cloCategory,
            cloDetailCategory, cloColor, cloIdentifier,
            cloSeason, cloBrand, cloImagePath, cloUserID;
    private String board_numHeart, board_numComment, board_numChild;

    public DetailFeedVO(){

    }

    protected DetailFeedVO(Parcel in) {
        userID = in.readString();
        userName = in.readString();
        userPfImagePath = in.readString();
        userPfContents = in.readString();
        userNumBoard = in.readString();
        userNumFollower = in.readString();
        userNumFollowing = in.readString();
        user_if_following = in.readString();
        user_following_friendsID = in.readString();
        user_followig_friendsName = in.readString();
        user_followig_friendsImgPath = in.readString();
        board_if_hearting = in.readString();
        boardNo = in.readString();
        boardImagePath = in.readString();
        boardContents = in.readString();
        boardRegDate = in.readString();
        cloNo = in.readString();
        cloLocation = in.readString();
        cloKind = in.readString();
        cloCategory = in.readString();
        cloDetailCategory = in.readString();
        cloColor = in.readString();
        cloIdentifier = in.readString();
        cloSeason = in.readString();
        cloBrand = in.readString();
        cloImagePath = in.readString();
        cloUserID = in.readString();
        board_numHeart = in.readString();
        board_numComment = in.readString();
        board_numChild = in.readString();
    }

    public static final Creator<DetailFeedVO> CREATOR = new Creator<DetailFeedVO>() {
        @Override
        public DetailFeedVO createFromParcel(Parcel in) {
            return new DetailFeedVO(in);
        }

        @Override
        public DetailFeedVO[] newArray(int size) {
            return new DetailFeedVO[size];
        }
    };

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPfImagePath() {
        return userPfImagePath;
    }

    public void setUserPfImagePath(String userPfImagePath) {
        this.userPfImagePath = userPfImagePath;
    }

    public String getUserPfContents() {
        return userPfContents;
    }

    public void setUserPfContents(String userPfContents) {
        this.userPfContents = userPfContents;
    }

    public String getUserNumBoard() {
        return userNumBoard;
    }

    public void setUserNumBoard(String userNumBoard) {
        this.userNumBoard = userNumBoard;
    }

    public String getUserNumFollower() {
        return userNumFollower;
    }

    public void setUserNumFollower(String userNumFollower) {
        this.userNumFollower = userNumFollower;
    }

    public String getUserNumFollowing() {
        return userNumFollowing;
    }

    public void setUserNumFollowing(String userNumFollowing) {
        this.userNumFollowing = userNumFollowing;
    }

    public String getUser_if_following() {
        return user_if_following;
    }

    public void setUser_if_following(String user_if_following) {
        this.user_if_following = user_if_following;
    }

    public String getUser_following_friendsID() {
        return user_following_friendsID;
    }

    public void setUser_following_friendsID(String user_following_friendsID) {
        this.user_following_friendsID = user_following_friendsID;
    }

    public String getUser_followig_friendsName() {
        return user_followig_friendsName;
    }

    public void setUser_followig_friendsName(String user_followig_friendsName) {
        this.user_followig_friendsName = user_followig_friendsName;
    }

    public String getUser_followig_friendsImgPath() {
        return user_followig_friendsImgPath;
    }

    public void setUser_followig_friendsImgPath(String user_followig_friendsImgPath) {
        this.user_followig_friendsImgPath = user_followig_friendsImgPath;
    }

    public String getBoard_if_hearting() {
        return board_if_hearting;
    }

    public void setBoard_if_hearting(String board_if_hearting) {
        this.board_if_hearting = board_if_hearting;
    }

    public String getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(String boardNo) {
        this.boardNo = boardNo;
    }

    public String getBoardImagePath() {
        return boardImagePath;
    }

    public void setBoardImagePath(String boardImagePath) {
        this.boardImagePath = boardImagePath;
    }

    public String getBoardContents() {
        return boardContents;
    }

    public void setBoardContents(String boardContents) {
        this.boardContents = boardContents;
    }

    public String getBoardRegDate() {
        return boardRegDate;
    }

    public void setBoardRegDate(String boardRegDate) {
        this.boardRegDate = boardRegDate;
    }

    public String getCloNo() {
        return cloNo;
    }

    public void setCloNo(String cloNo) {
        this.cloNo = cloNo;
    }

    public String getCloLocation() {
        return cloLocation;
    }

    public void setCloLocation(String cloLocation) {
        this.cloLocation = cloLocation;
    }

    public String getCloKind() {
        return cloKind;
    }

    public void setCloKind(String cloKind) {
        this.cloKind = cloKind;
    }

    public String getCloCategory() {
        return cloCategory;
    }

    public void setCloCategory(String cloCategory) {
        this.cloCategory = cloCategory;
    }

    public String getCloDetailCategory() {
        return cloDetailCategory;
    }

    public void setCloDetailCategory(String cloDetailCategory) {
        this.cloDetailCategory = cloDetailCategory;
    }

    public String getCloColor() {
        return cloColor;
    }

    public void setCloColor(String cloColor) {
        this.cloColor = cloColor;
    }

    public String getCloIdentifier() {
        return cloIdentifier;
    }

    public void setCloIdentifier(String cloIdentifier) {
        this.cloIdentifier = cloIdentifier;
    }

    public String getCloSeason() {
        return cloSeason;
    }

    public void setCloSeason(String cloSeason) {
        this.cloSeason = cloSeason;
    }

    public String getCloBrand() {
        return cloBrand;
    }

    public void setCloBrand(String cloBrand) {
        this.cloBrand = cloBrand;
    }

    public String getCloImagePath() {
        return cloImagePath;
    }

    public void setCloImagePath(String cloImagePath) {
        this.cloImagePath = cloImagePath;
    }

    public String getCloUserID() {
        return cloUserID;
    }

    public void setCloUserID(String cloUserID) {
        this.cloUserID = cloUserID;
    }

    public String getBoard_numHeart() {
        return board_numHeart;
    }

    public void setBoard_numHeart(String board_numHeart) {
        this.board_numHeart = board_numHeart;
    }

    public String getBoard_numComment() {
        return board_numComment;
    }

    public void setBoard_numComment(String board_numComment) {
        this.board_numComment = board_numComment;
    }

    public String getBoard_numChild() {
        return board_numChild;
    }

    public void setBoard_numChild(String board_numChild) {
        this.board_numChild = board_numChild;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userID);
        dest.writeString(userName);
        dest.writeString(userPfImagePath);
        dest.writeString(userPfContents);
        dest.writeString(userNumBoard);
        dest.writeString(userNumFollower);
        dest.writeString(userNumFollowing);
        dest.writeString(user_if_following);
        dest.writeString(user_following_friendsID);
        dest.writeString(user_followig_friendsName);
        dest.writeString(user_followig_friendsImgPath);
        dest.writeString(board_if_hearting);
        dest.writeString(boardNo);
        dest.writeString(boardImagePath);
        dest.writeString(boardContents);
        dest.writeString(boardRegDate);
        dest.writeString(cloNo);
        dest.writeString(cloLocation);
        dest.writeString(cloKind);
        dest.writeString(cloCategory);
        dest.writeString(cloDetailCategory);
        dest.writeString(cloColor);
        dest.writeString(cloIdentifier);
        dest.writeString(cloSeason);
        dest.writeString(cloBrand);
        dest.writeString(cloImagePath);
        dest.writeString(cloUserID);
        dest.writeString(board_numHeart);
        dest.writeString(board_numComment);
        dest.writeString(board_numChild);
    }
}
