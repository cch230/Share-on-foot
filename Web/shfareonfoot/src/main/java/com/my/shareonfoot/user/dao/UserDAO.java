package com.my.shareonfoot.user.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.my.shareonfoot.user.vo.UserVO;

public interface UserDAO {

	public List<UserVO> selectAllUsers() throws DataAccessException; //紐⑤뱺 �궗�슜�옄
	
	public UserVO selectThisUser(String userID) throws DataAccessException; //id濡� �궗�슜�옄 �븯�굹 �꽑�깮
	public List<UserVO> selectUser(UserVO userFilter) throws DataAccessException; //議곌굔�쑝濡� �궗�슜�옄 �꽑�깮
	
	public String addUser(UserVO userInfo) throws DataAccessException; //�궗�슜�옄 異붽�
	//�븘�씠�뵒 李얘린
	//鍮꾨�踰덊샇 李얘린
	public String updateUser(UserVO userInfo) throws DataAccessException; //�궗�슜�옄 �젙蹂� �닔�젙
	public String deleteUser(String userID) throws DataAccessException; //�궗�슜�옄 �궘�젣
	
	public String checkExistUser(UserVO userInfo) throws DataAccessException; //�빐�떦 �쑀�� 議댁옱�뿬遺� �솗�씤
	public String verifyIdPwd(String userID, String pwd) throws DataAccessException; //ID, �뙣�뒪�썙�뱶 �쑀�슚�꽦 �솗�씤

}