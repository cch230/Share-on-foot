package com.my.shareonfoot.board.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.my.shareonfoot.board.vo.BoardVO;

public interface BoardController {
	
	public ModelAndView boardlist(HttpServletRequest request, HttpServletResponse response) throws Exception; //紐⑤뱺 寃뚯떆湲� 由ъ뒪�듃 議고쉶. �쎒 愿�由ъ슜.
	
	
	public ResponseEntity<List<BoardVO>> AllBoard(String page, String pageSize) throws Exception; //紐⑤뱺 寃뚯떆湲� 由ъ뒪�듃 議고쉶

	public ResponseEntity<List<BoardVO>> myAllBoard(HttpSession session, String page, String pageSize) throws Exception; //�궡 寃뚯떆湲� �쟾遺� 議고쉶
	public ResponseEntity<List<BoardVO>> usersAllBoard(String userID, String page, String pageSize) throws Exception; //�듅�젙 �쑀�� 寃뚯떆湲� �쟾遺� 議고쉶
	
	public ResponseEntity<BoardVO> infoBoard(String boardNo) throws Exception; //�듅�젙 寃뚯떆湲� 議고쉶
	public ResponseEntity<List<BoardVO>> searchBoard(BoardVO boardFilter, String page, String pageSize) throws Exception; //�듅�젙 議곌굔�쓽 寃뚯떆湲� 由ъ뒪�듃 議고쉶
	
	public ResponseEntity<String> addBoard(MultipartHttpServletRequest multipartRequest, MultipartFile multipartFile) throws Exception; //寃뚯떆湲� 異붽�

	public ResponseEntity<String> modifyBoard(BoardVO boardInfo) throws Exception; //寃뚯떆湲� �닔�젙
	public ResponseEntity<String> deleteBoard(String boardNo) throws Exception; //寃뚯떆湲� �궘�젣
	
}
