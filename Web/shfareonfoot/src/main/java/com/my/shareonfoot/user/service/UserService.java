package com.my.shareonfoot.user.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.my.shareonfoot.board.vo.BoardVO;
import com.my.shareonfoot.user.vo.LoginVO;
import com.my.shareonfoot.user.vo.UserVO;

public interface UserService {

	public List<UserVO> listAllUsers() throws DataAccessException; //紐⑤뱺 �궗�슜�옄�쓽 �쉶�썝 �젙蹂� 由ъ뒪�듃 議고쉶
	
	public UserVO infoUser(String userID) throws DataAccessException; //�듅�젙 �궗�슜�옄�쓽 �쉶�썝 �젙蹂� 議고쉶
	public List<UserVO> searchUser(UserVO userFilter, String page, String pageSize) throws DataAccessException; //議곌굔�쑝濡� �궗�슜�옄 由ъ뒪�듃 寃��깋
	
	public String join(UserVO userInfo) throws DataAccessException; //�쉶�썝媛��엯
	public String login(LoginVO loginVO) throws DataAccessException; //濡쒓렇�씤
	//�븘�씠�뵒 李얘린
	//鍮꾨�踰덊샇 李얘린
	public String modifyUser(UserVO userInfo) throws DataAccessException; //�쉶�썝 �젙蹂� �닔�젙
	public String modifyProfileImage(MultipartHttpServletRequest multipartRequest) throws DataAccessException; //�쉶�썝媛��엯
	public String winmodifyProfileImage(MultipartHttpServletRequest multipartRequest) throws DataAccessException; //�쉶�썝媛��엯
	
	
	public String deleteAccount(String userID) throws DataAccessException; //�쉶�썝�깉�눜
	//�빐�떦 �샆�옣�씠�옉 �샆 �뜲�씠�꽣踰좎씠�뒪, �궗吏� �뙆�씪�뱾 �쟾遺� 媛숈씠 �궘�젣�빐�빞 �븿.
	
}
