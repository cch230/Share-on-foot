package com.my.shareonfoot.codi.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.my.shareonfoot.codi.vo.CodiVO;

public interface CodiDAO {

	public List<CodiVO> selectAllCodi() throws DataAccessException; //紐⑤뱺 肄붾뵒 �꽑�깮
	public CodiVO selectThisCodi(String codiNo) throws DataAccessException; //codiNo濡� 肄붾뵒 �븯�굹 �꽑�깮
	public List<CodiVO> selectCodi(CodiVO codiFilter) throws DataAccessException; //議곌굔�쑝濡� 肄붾뵒 �꽑�깮
	
	public String addCodi(Map<String, Object> codiInfo) throws DataAccessException; //肄붾뵒 異붽� (�삁�쇅�쟻�쑝濡� �빐�돩留듭쑝濡� 諛쏆쓬)
	public String addCodiData(CodiVO codiInfo) throws DataAccessException;
	public String updateCodi(CodiVO codiInfo) throws DataAccessException; //肄붾뵒 �젙蹂� �닔�젙
	public String deleteCodi(String codiNo) throws DataAccessException; //肄붾뵒 �궘�젣 (1,2,3,4,5濡� �뿬�윭 媛� 媛��뒫) --二쇱쓽

}
