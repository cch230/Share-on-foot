package com.my.shareonfoot.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.my.shareonfoot.user.vo.UserVO;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	//紐⑤뱺 �궗�슜�옄
	@Override
	public List<UserVO> selectAllUsers() throws DataAccessException {
		List<UserVO> userlist = sqlSession.selectList("mapper.user.searchUser", new UserVO());
		return userlist;
	}
	//id濡� �궗�슜�옄 �븯�굹 �꽑�깮
	@Override
	public UserVO selectThisUser(String userID) throws DataAccessException {
		UserVO userInfo = (UserVO) sqlSession.selectOne("mapper.user.searchUser", new UserVO(userID));
		return userInfo;
	}
	//議곌굔�쑝濡� �궗�슜�옄 �꽑�깮
	@Override
	public List<UserVO> selectUser(UserVO userFilter) throws DataAccessException {
		List<UserVO> userlist = sqlSession.selectList("mapper.user.searchUser", userFilter);
		return userlist;
	}
	
	//�궗�슜�옄 異붽�
	@Override
	public String addUser(UserVO userInfo) throws DataAccessException {

		System.out.println("insert 荑쇰━ �떎�뻾 �쟾");
		int result = sqlSession.insert("mapper.user.insertUser", userInfo);
		System.out.println("insert 荑쇰━ �떎�뻾");
		
		if (result == 1) {
			System.out.println("insert �꽦怨�");
			return "ok"; //insert �꽦怨�
		}
		else {
			System.out.println("insert �떎�뙣");
			return "fail"; //insert �떎�뙣
		}		
	}


	//�궗�슜�옄 �젙蹂� �닔�젙
	@Override
	public String updateUser(UserVO userInfo) throws DataAccessException {
		int result = sqlSession.update("mapper.user.updateUser",userInfo);
		
		if (result==1)
			return "ok"; //update �꽦怨�
		else
			return "fail"; //update �떎�뙣
	}

	//�궗�슜�옄 �궘�젣
	@Override
	public String deleteUser(String userID) throws DataAccessException {
		int result = sqlSession.delete("mapper.user.deleteUser",userID);
		
		if (result==1)
			return "ok";
		else
			return "fail";
	}
	
	//�빐�떦 �쑀�� 議댁옱�뿬遺� �솗�씤
	@Override
	public String checkExistUser(UserVO userInfo) throws DataAccessException{
		String userID = userInfo.getUserID();
		String email = userInfo.getEmail();
		
		if(userID != null) {
			// �빐�떦 ID瑜� 媛�吏� �궗�슜�옄媛� �엳�뒗吏� 寃��깋
			UserVO selectedUser = (UserVO) sqlSession.selectOne("mapper.user.searchUser", new UserVO(userID));
			// 寃��깋 寃곌낵媛� �엳怨� 以묐났�맂 ID媛� �엳�쑝硫� "id" 諛섑솚
			if (selectedUser != null) {
				if (selectedUser.getUserID().equals(userID))
					return "id";
			}
		} else if(email != null) {
			// �빐�떦 �씠硫붿씪�쓣 媛�吏� �궗�슜�옄媛� �엳�뒗吏� 寃��깋
			UserVO userFilter = new UserVO();
			userFilter.setEmail(email);
			UserVO selectedUser = (UserVO) sqlSession.selectOne("mapper.user.searchUser", userFilter);
			// 寃��깋 寃곌낵媛� �엳怨� 以묐났�맂 �씠硫붿씪�씠 �엳�쑝硫� "email" 諛섑솚
			if (selectedUser != null) {
				if (selectedUser.getEmail().equals(email))
					return "email";
			}		
		}
		// �뼱�뒓 議곌굔�뿉�룄 �빐�떦 �븞 �맆 �떆 "ok" 諛섑솚
		return "ok";
	}
	
	//ID, �뙣�뒪�썙�뱶 �쑀�슚�꽦 �솗�씤
	@Override
	public String verifyIdPwd(String userID, String pwd) throws DataAccessException {
		
		// ID�� 鍮꾨�踰덊샇媛� �씪移섑븯�뒗 �궗�슜�옄媛� �엳�뒗吏� 寃��깋
		UserVO selectedUser = (UserVO) sqlSession.selectOne("mapper.user.searchUser", new UserVO(userID, pwd));

		// 寃��깋 寃곌낵媛� �엳怨� ID�� 鍮꾨�踰덊샇媛� �씪移섑븯硫� "true" 諛섑솚
		if (selectedUser != null) {
			if (selectedUser.getUserID().equals(userID) && selectedUser.getPwd().equals(pwd))
				return "true";
		}
		return "false";
	}

}
