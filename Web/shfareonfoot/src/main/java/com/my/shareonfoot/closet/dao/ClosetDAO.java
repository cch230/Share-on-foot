package com.my.shareonfoot.closet.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.my.shareonfoot.closet.vo.ClosetVO;

public interface ClosetDAO {
	public List<ClosetVO> selectAllCloset() throws DataAccessException; //紐⑤뱺 �샆�옣 �꽑�깮
	public ClosetVO selectCloset(String userID, String closetName) throws DataAccessException; //�샆�옣 �꽑�깮
	public String insertCloset(ClosetVO closetVO) throws DataAccessException; //�샆�옣 異붽�
	public String updateCloset(ClosetVO closetVO) throws DataAccessException; //�샆�옣 �젙蹂� �닔�젙
	public String deleteCloset(String userID, String closetName) throws DataAccessException; //�샆�옣 �궘�젣
}
