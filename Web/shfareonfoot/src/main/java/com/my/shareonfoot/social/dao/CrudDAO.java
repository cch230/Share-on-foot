
package com.my.shareonfoot.social.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.my.shareonfoot.board.vo.BoardVO;
import com.my.shareonfoot.social.vo.CommentFeedVO;
import com.my.shareonfoot.social.vo.CommentVO;
import com.my.shareonfoot.social.vo.FeedVO;
import com.my.shareonfoot.social.vo.FollowVO;
import com.my.shareonfoot.social.vo.HeartVO;

public interface CrudDAO {

	// �뙏濡쒖슦
	public List<FollowVO> selectFollow(FollowVO followFilter) throws DataAccessException; // �뙏濡쒖슦 select

	public String addFollow(FollowVO followInfo) throws DataAccessException; // �뙏濡쒖슦 異붽�

	public String deleteFollow(FollowVO followInfo) throws DataAccessException; // �뙏濡쒖슦 �궘�젣
	
	// �븯�듃
	public List<HeartVO> selectHeart(HeartVO heartFilter) throws DataAccessException;

	public String addHeart(HeartVO heartFilter) throws DataAccessException;

	public String deleteHeart(HeartVO heartFilter) throws DataAccessException;

	// �뙎湲�
	public String addComment(CommentVO commentFilter) throws DataAccessException;
	public String deleteComment(CommentVO commentFilter) throws DataAccessException;
	
	
	
	// �뙎湲� 醫뗭븘�슂
}
