 package com.my.shareonfoot.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.my.shareonfoot.board.vo.BoardVO;
import com.my.shareonfoot.social.vo.CommentFeedVO;
import com.my.shareonfoot.social.vo.FeedVO;

public interface BoardDAO {

	public List<BoardVO> boardlist() throws DataAccessException; //紐⑤뱺 寃뚯떆�뙋
	public List<BoardVO> selectAllBoard(BoardVO boardFilter) throws DataAccessException; //紐⑤뱺 寃뚯떆�뙋

	public BoardVO selectThisBoard(String boardNo) throws DataAccessException; //boardNo濡� 肄붾뵒 �븯�굹 �꽑�깮
	public List<BoardVO> selectBoard(BoardVO boardFilter) throws DataAccessException; //議곌굔�쑝濡� 肄붾뵒 �꽑�깮
	
	public String addBoard(Map<String, Object> boardMap) throws DataAccessException; //肄붾뵒 異붽� (�삁�쇅�쟻�쑝濡� �빐�돩留듭쑝濡� 諛쏆쓬)
	public String updateBoard(BoardVO boardInfo) throws DataAccessException; //肄붾뵒 �젙蹂� �닔�젙
	public String deleteBoard(String boardNo) throws DataAccessException; //肄붾뵒 �궘�젣 (1,2,3,4,5濡� �뿬�윭 媛� 媛��뒫) --二쇱쓽

	public List<FeedVO> showAllFeed(BoardVO boardFilter) throws DataAccessException; //�뵾�뱶 媛��졇�삤湲�
	public FeedVO showOneFeed(BoardVO boardFilter) throws DataAccessException;
	public List<CommentFeedVO> showCommentInBoard(BoardVO boardFilter) throws DataAccessException; //�빐�떦 寃뚯떆湲� �뙎湲� 媛��졇�삤湲�
}
