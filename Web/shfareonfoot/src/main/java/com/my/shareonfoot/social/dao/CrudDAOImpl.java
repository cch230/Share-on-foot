package com.my.shareonfoot.social.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.my.shareonfoot.board.vo.BoardVO;
import com.my.shareonfoot.social.vo.CommentFeedVO;
import com.my.shareonfoot.social.vo.CommentVO;
import com.my.shareonfoot.social.vo.FeedVO;
import com.my.shareonfoot.social.vo.FollowVO;
import com.my.shareonfoot.social.vo.HeartVO;
import com.my.shareonfoot.user.vo.UserVO;

@Repository("crudDAO")
public class CrudDAOImpl implements CrudDAO {

	@Autowired
	private SqlSession sqlSession;

	// 議곌굔�쑝濡� �뙏濡쒖슦 �꽑�깮
	@Override
	public List<FollowVO> selectFollow(FollowVO followFilter) throws DataAccessException {
		List<FollowVO> followList = sqlSession.selectList("mapper.social_crud.searchFollow", followFilter);
		return followList;
	}

	// �뙏濡쒖슦 異붽�
	@Override
	public String addFollow(FollowVO followInfo) throws DataAccessException {

		System.out.println("insert 荑쇰━ �떎�뻾 �쟾");
		int result = sqlSession.insert("mapper.social_crud.insertFollow", followInfo);
		System.out.println("insert 荑쇰━ �떎�뻾");

		String numFollow = followInfo.getFollowedID();

		if (result == 1) {
			System.out.println("insert �꽦怨�");
			return numFollow; // insert �꽦怨�
		} else {
			System.out.println("insert �떎�뙣");
			return "fail"; // insert �떎�뙣
		}
		
		
		
	}

	// �뙏濡쒖슦 �궘�젣
	@Override
	public String deleteFollow(FollowVO followInfo) throws DataAccessException {
		int result = sqlSession.delete("mapper.social_crud.deleteFollow", followInfo);

		if (result == 1) {
			System.out.println("delete �꽦怨�");
			int res = sqlSession.selectOne("mapper.social_crud.numFollow", followInfo);
			return Integer.toString(res); // insert �꽦怨�
		} else {
			System.out.println("delete �떎�뙣");
			return "fail"; // insert �떎�뙣
		}
		
	}

	
	
	
	/*�븯�듃*/
	// 議곌굔�쑝濡� �븯�듃 �꽑�깮
	@Override
	public List<HeartVO> selectHeart(HeartVO heartFilter) throws DataAccessException {
		List<HeartVO> heartList = sqlSession.selectList("mapper.social_crud.searchHeart", heartFilter);
		return heartList;
	}

	// �븯�듃 異붽�
	@Override
	public String addHeart(HeartVO heartFilter) throws DataAccessException {

		System.out.println("insert 荑쇰━ �떎�뻾 �쟾");
		int result = sqlSession.insert("mapper.social_crud.insertHeart", heartFilter);
		System.out.println("insert 荑쇰━ �떎�뻾");
		
		String numHeart = heartFilter.getBoardNo();

		if (result == 1) {
			System.out.println("insert �꽦怨�");
			return numHeart; // insert �꽦怨�
		} else {
			System.out.println("insert �떎�뙣");
			return "fail"; // insert �떎�뙣
		}
	}

	// �븯�듃 �궘�젣
	@Override
	public String deleteHeart(HeartVO heartFilter) throws DataAccessException {
		int result = sqlSession.delete("mapper.social_crud.deleteHeart", heartFilter);


		if (result == 1) {
			System.out.println("delete �꽦怨�");
			int res = sqlSession.selectOne("mapper.social_crud.numHeart", heartFilter);
			return Integer.toString(res); // insert �꽦怨�
		} else {
			System.out.println("delete �떎�뙣");
			return "fail"; // insert �떎�뙣
		}
	}	
	
	
	
	
	// 肄붾찘�듃 異붽�
	@Override
	public String addComment(CommentVO commentFilter) throws DataAccessException {

		System.out.println("insert 荑쇰━ �떎�뻾 �쟾");
		int result = sqlSession.insert("mapper.social_crud.insertComment", commentFilter);
		System.out.println("insert 荑쇰━ �떎�뻾");
		
		String numComment = commentFilter.getBoardNo();

		if (result == 1) {
			System.out.println("insert �꽦怨�");
			return numComment; // insert �꽦怨�
		} else {
			System.out.println("insert �떎�뙣");
			return "fail"; // insert �떎�뙣
		}
	}

	// 肄붾찘�듃 �궘�젣
	@Override
	public String deleteComment(CommentVO commentFilter) throws DataAccessException {
		int result = sqlSession.delete("mapper.social_crud.deleteComment", commentFilter);


		if (result == 1) {
			System.out.println("delete �꽦怨�");
			int res = sqlSession.selectOne("mapper.social_crud.numComment", commentFilter);
			return Integer.toString(res); // insert �꽦怨�
		} else {
			System.out.println("delete �떎�뙣");
			return "fail"; // insert �떎�뙣
		}
	}		
		
	
	
}
