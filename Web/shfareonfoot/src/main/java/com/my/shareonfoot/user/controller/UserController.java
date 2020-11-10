package com.my.shareonfoot.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.my.shareonfoot.board.vo.BoardVO;
import com.my.shareonfoot.user.vo.LoginVO;
import com.my.shareonfoot.user.vo.UserVO;

public interface UserController {
	
	public ModelAndView userlist(HttpServletRequest request, HttpServletResponse response) throws Exception; //紐⑤뱺 �궗�슜�옄�쓽 �쉶�썝 �젙蹂� 由ъ뒪�듃 議고쉶
	
	public ResponseEntity<UserVO> myInfo(String userID) throws Exception; //�듅�젙 �궗�슜�옄�쓽 �쉶�썝 �젙蹂� 議고쉶
	public ResponseEntity<List<UserVO>> searchUser(UserVO userFilter, String page, String pageSize) throws Exception; //�듅�젙 議곌굔�쓽 �궗�슜�옄 由ъ뒪�듃 議고쉶
	
	public ResponseEntity<String> join(UserVO userInfo) throws Exception; //�쉶�썝 媛��엯
	public ResponseEntity<String> login(LoginVO loginVO, HttpSession session) throws Exception; //濡쒓렇�씤
	//�븘�씠�뵒 李얘린
	//鍮꾨�踰덊샇 李얘린
	public ResponseEntity<String> modifyUser(UserVO userInfo) throws Exception; //�쉶�썝 �젙蹂� �닔�젙
	public ResponseEntity<String> modifyProfileImage(MultipartHttpServletRequest multipartRequest, MultipartFile multipartFile) throws Exception; //�쉶�썝媛��엯
	
	
	public ResponseEntity<String> deleteAccount(String userID) throws Exception; //�쉶�썝�깉�눜

	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
