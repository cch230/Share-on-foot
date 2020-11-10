package com.my.shareonfoot.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.my.shareonfoot.board.vo.BoardVO;

public interface BoardService {
	
	public List<BoardVO> boardlist() throws DataAccessException; //紐⑤뱺 寃뚯떆湲� 由ъ뒪�듃 議고쉶. �쎒 愿�由ъ슜.
	
	public List<BoardVO> listAllBoard(String page, String pageSize) throws DataAccessException; //紐⑤뱺 寃뚯떆湲� 由ъ뒪�듃 議고쉶

	public List<BoardVO> myAllBoard(HttpSession session, String page, String pageSize) throws DataAccessException; //�궡 寃뚯떆湲� �쟾遺� 議고쉶
	public List<BoardVO> usersAllBoard(String userID, String page, String pageSize) throws DataAccessException; //�듅�젙 �쑀�� 寃뚯떆湲� �쟾遺� 議고쉶
	
	public BoardVO infoBoard(String boardNo) throws DataAccessException; //�듅�젙 寃뚯떆湲� 議고쉶
	public List<BoardVO> searchBoard(BoardVO boardFilter, String page, String pageSize) throws DataAccessException; //�듅�젙 議곌굔�쓽 寃뚯떆湲� 由ъ뒪�듃 議고쉶
	
	public String winAddBoard(MultipartHttpServletRequest multipartRequest) throws DataAccessException;
	public String addBoard(MultipartHttpServletRequest multipartRequest) throws DataAccessException; //寃뚯떆湲� 異붽�
	
	public String modifyBoard(BoardVO boardInfo) throws DataAccessException; //寃뚯떆湲� �닔�젙
	public String deleteBoard(String boardNo) throws DataAccessException; //寃뚯떆湲� �궘�젣 (1,2,3,4,5濡� �뿬�윭 媛� 媛��뒫) --二쇱쓽
	
}
