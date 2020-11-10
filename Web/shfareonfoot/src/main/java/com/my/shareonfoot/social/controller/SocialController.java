package com.my.shareonfoot.social.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.my.shareonfoot.board.vo.BoardVO;
import com.my.shareonfoot.social.vo.CommentFeedVO;
import com.my.shareonfoot.social.vo.CommentVO;
import com.my.shareonfoot.social.vo.DetailFeedVO;
import com.my.shareonfoot.social.vo.DetailFeedVO_Extended;
import com.my.shareonfoot.social.vo.ExpandedFeedVO;
import com.my.shareonfoot.social.vo.FeedVO;
import com.my.shareonfoot.social.vo.FollowVO;
import com.my.shareonfoot.social.vo.HeartVO;
import com.my.shareonfoot.social.vo.UserspaceVO;


public interface SocialController {
	
	public ModelAndView feedlist(HttpServletRequest request, HttpServletResponse response) throws Exception; //紐⑤뱺 �뵾�뱶 由ъ뒪�듃 議고쉶. �쎒 愿�由ъ슜.
	
	/*�뵾�뱶*/
	//public ResponseEntity<List<FeedVO>> showAllFeed(String myID, String page, String pageSize) throws Exception; //理쒖떊 �뵾�뱶 媛��졇�삤湲�
	//public ResponseEntity<List<FeedVO>> showFollowFeed(String userID, String page, String pageSize) throws Exception; //�뙏濡쒖슦 �뵾�뱶
	
	
	ResponseEntity<List<DetailFeedVO>> searchFeed(DetailFeedVO_Extended feedFilter,String page,String pageSize) throws Exception; //�뵾�뱶 議곌굔 寃��깋
	
	
	//public ResponseEntity<List<FeedVO>> showHeartFeed(String userID, String myID, String page,String pageSize) throws Exception; // �빐�떦 �궗�슜�옄媛� 醫뗭븘�슂�븳 �뵾�뱶
	
	//public ResponseEntity<List<DetailFeedVO>> showDetailFeed(String boardNo, String myID) throws Exception; // �빐�떦 寃뚯떆臾� �꽭遺� �궡�슜 媛��졇�삤湲�
	public ResponseEntity<List<CommentFeedVO>> showCommentInBoard(String boardNo, String page, String pageSize) throws Exception;
		
	/*�뙏濡쒖슦*/
	public ModelAndView followlist(HttpServletRequest request, HttpServletResponse response) throws Exception; // 紐⑤뱺 �뙏濡쒖슦 由ъ뒪�듃 議고쉶. �쎒 愿�由ъ슜.
	public ResponseEntity<List<FollowVO>> searchFollow(@RequestBody FollowVO followFilter) throws Exception; //�뙏濡쒖슦 寃��깋
	public ResponseEntity<String> revertFollow(@RequestBody FollowVO followInfo) throws Exception; //�뙏濡쒖슦 �긽�깭 蹂�寃�
	
	/*�븯�듃*/
	public ModelAndView heartlist(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity<List<HeartVO>> searchHeart(@RequestBody HeartVO heartFilter) throws Exception;
	public ResponseEntity<String> revertHeart(@RequestBody HeartVO heartInfo) throws Exception;
	
	/*�뙎湲�*/	
	public ResponseEntity<String> addComment(CommentVO commentInfo) throws Exception;
	public ResponseEntity<String> deleteComment(String commentNo, String boardNo) throws Exception;
					
	/*�쑀���뒪�럹�씠�뒪*/
	public ResponseEntity<UserspaceVO> showUserspace(String userID, String myID) throws Exception;
	
	/*肄붾뵒 異붿쿇*/
	public ResponseEntity<List<DetailFeedVO>> recommendFull(String myID, String tag) throws Exception;

	

	

	
}
