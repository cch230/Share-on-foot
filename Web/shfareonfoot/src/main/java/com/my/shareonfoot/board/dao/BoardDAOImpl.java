package com.my.shareonfoot.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.my.shareonfoot.board.vo.BoardVO;
import com.my.shareonfoot.social.vo.CommentFeedVO;
import com.my.shareonfoot.social.vo.FeedVO;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<BoardVO> boardlist() throws DataAccessException {
		//鍮� �븘�꽣瑜� 蹂대깂�쑝濡쒖뜥 紐⑤뱺 寃뚯떆�뙋 議고쉶
		List<BoardVO> boardlist = sqlSession.selectList("mapper.board.searchBoard", new BoardVO());
		return boardlist;
	}
	
	@Override
	public List<BoardVO> selectAllBoard(BoardVO boardFilter) throws DataAccessException {
		//�럹�씠吏� �븘�꽣瑜� �쟻�슜�븳 紐⑤뱺 寃뚯떆湲� 議고쉶
		List<BoardVO> boardlist = sqlSession.selectList("mapper.board.searchBoard", boardFilter);
		return boardlist;
	}

	
	
	@Override
	public BoardVO selectThisBoard(String boardNo) throws DataAccessException {
		//怨좎쑀 踰덊샇 �븘�꽣 �깮�꽦
		BoardVO boardFilter = new BoardVO();
		boardFilter.setBoardNo(Integer.parseInt(boardNo));
		//�븘�꽣濡� �꽌移�
		BoardVO board = sqlSession.selectOne("mapper.board.searchBoard",boardFilter);
		return board;
	}

	@Override
	public List<BoardVO> selectBoard(BoardVO boardFilter) throws DataAccessException {
		//�븘�꽣濡� �꽌移�
		List<BoardVO> boardList = sqlSession.selectList("mapper.board.searchBoard", boardFilter);
		return boardList;
	}

	@Override
	public String addBoard(Map<String, Object> boardMap) throws DataAccessException {

		System.out.println("insert 荑쇰━ �떎�뻾 �쟾");
		int result= sqlSession.insert("mapper.board.insertBoard", boardMap);
		System.out.println("insert 荑쇰━ �떎�뻾");
		
		int inserted_boardNo = (int) boardMap.get("inserted_boardNo");
		
		System.out.println("insert�맂 boardNo : "+inserted_boardNo);
		
		
		HashMap<String, Object> relMap = new HashMap<String, Object>();
		
		
		for(int i=0; i<10; i++) {
			if( boardMap.containsKey("child"+i+"_cloNo") ) {
				relMap.put("boardNo", inserted_boardNo);
				relMap.put("cloNo", boardMap.get("child"+i+"_cloNo"));
				result = sqlSession.insert("mapper.board.insertRelation", relMap);
				if (result == 1) {
					System.out.println(inserted_boardNo+"由대젅�씠�뀡 荑쇰━ �꽦怨�"+boardMap.get("child"+i+"_cloNo"));
				}
				else
					System.out.println("由대젅�씠�뀡 荑쇰━ �떎�뙣");
			}
		}
		
		if (result == 1) {
			System.out.println("荑쇰━ �꽦怨�");
			return "ok"; //insert �꽦怨�
		}
		else {
			System.out.println("荑쇰━ �떎�뙣");
			return "fail"; //insert �떎�뙣
		}			
	}

	@Override
	public String updateBoard(BoardVO boardInfo) throws DataAccessException {
		int result = sqlSession.update("mapper.board.updateBoard",boardInfo);
		
		if (result==1)
			return "ok"; //update �꽦怨�
		else
			return "fail"; //update �떎�뙣
	}

	@Override
	public String deleteBoard(String boardNo) throws DataAccessException {
		int result = sqlSession.delete("mapper.board.deleteBoard",boardNo);
		
		if (result==1)
			return "ok";
		else
			return "fail";
	}
	
	@Override
	public List<FeedVO> showAllFeed(BoardVO boardFilter) throws DataAccessException {
		List<FeedVO> feedList = sqlSession.selectList("mapper.social.showFeed",boardFilter);
		return feedList;
	}
	
	@Override
	public FeedVO showOneFeed(BoardVO boardFilter) throws DataAccessException {
		FeedVO feed = sqlSession.selectOne("mapper.social.showOneFeed",boardFilter);
		return feed;
	}
	
	@Override
	public List<CommentFeedVO> showCommentInBoard(BoardVO boardFilter) throws DataAccessException {
		List<CommentFeedVO> commentList = sqlSession.selectList("mapper.social.showCommentInBoard",boardFilter);
		return commentList;
	}

}
