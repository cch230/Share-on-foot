
package com.my.shareonfoot.social.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.my.shareonfoot.board.vo.BoardVO;
import com.my.shareonfoot.social.vo.CommentFeedVO;
import com.my.shareonfoot.social.vo.DetailFeedVO;
import com.my.shareonfoot.social.vo.DetailFeedVO_Extended;
import com.my.shareonfoot.social.vo.ExpandedFeedVO;
import com.my.shareonfoot.social.vo.FeedVO;
import com.my.shareonfoot.social.vo.FollowVO;
import com.my.shareonfoot.social.vo.HeartVO;
import com.my.shareonfoot.social.vo.UserspaceVO;

public interface SocialDAO {

	public List<FeedVO> showAllFeed(FollowVO followFilter) throws DataAccessException; //�뵾�뱶 媛��졇�삤湲�
	public List<FeedVO> showFollowFeed(FollowVO followFilter) throws DataAccessException; //�뙏濡쒖슦 �뵾�뱶 媛��졇�삤湲�
	
	
	public List<DetailFeedVO> searchFeed(DetailFeedVO_Extended feedFilter) throws DataAccessException; //�뵾�뱶 議곌굔 寃��깋
	
	
	public List<FeedVO> showHeartFeed(FollowVO followFilter) throws DataAccessException; // �빐�떦 �궗�슜�옄媛� 醫뗭븘�슂�븳 �뵾�뱶
	public List<DetailFeedVO> showDetailFeed(HeartVO feedFilter) throws DataAccessException;
	public List<CommentFeedVO> showCommentInBoard(BoardVO boardFilter) throws DataAccessException; //�빐�떦 寃뚯떆湲� �뙎湲� 媛��졇�삤湲�

	public UserspaceVO showUserSpace(FollowVO followFilter) throws DataAccessException;
	
	public List<DetailFeedVO> recommendFull(HashMap<String, Object> recoFilter) throws DataAccessException;
}
