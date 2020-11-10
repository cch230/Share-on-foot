package com.my.shareonfoot.clothes.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.my.shareonfoot.clothes.vo.ClothesVO;

public interface ClothesDAO {

	public List<ClothesVO> selectAllClothes() throws DataAccessException; //紐⑤뱺 �샆 �꽑�깮
	public ClothesVO selectThisClothes(String no) throws DataAccessException; //no濡� �샆 �븯�굹 �꽑�깮 -- int? String?
	public List<ClothesVO> selectClothes(ClothesVO clothesVO) throws DataAccessException; //議곌굔�쑝濡� �샆 �꽑�깮
	public List<ClothesVO> selectClothesByList(HashMap keywordMap) throws DataAccessException;
	
	public String addClothes(Map<String, Object> clothesMap) throws DataAccessException; //�샆 異붽� (�삁�쇅�쟻�쑝濡� �빐�돩留듭쑝濡� 諛쏆쓬)
	public String addClothesData(ClothesVO cloInfo) throws DataAccessException; //�샆 �뜲�씠�꽣濡� 異붽�
	
	public String updateClothes(ClothesVO clothesInfo) throws DataAccessException; //�샆 �젙蹂� �닔�젙
	public String deleteClothes(String no) throws DataAccessException; //�샆 �궘�젣 (1,2,3,4,5濡� �뿬�윭 媛� 媛��뒫) --二쇱쓽

}
