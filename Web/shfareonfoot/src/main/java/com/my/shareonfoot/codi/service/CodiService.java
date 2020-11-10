package com.my.shareonfoot.codi.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.my.shareonfoot.codi.vo.CodiVO;

public interface CodiService {
	
	public List<CodiVO> listAllCodi() throws DataAccessException; //紐⑤뱺 肄붾뵒�쓽 肄붾뵒 �젙蹂� 由ъ뒪�듃 議고쉶. �쎒 愿�由ъ슜.
	public List<CodiVO> myAllCodi(String userID, CodiVO codiFilter) throws DataAccessException; //�궡 肄붾뵒 �쟾遺� 議고쉶
	
	public CodiVO infoCodi(String codiNo) throws DataAccessException; //�듅�젙 肄붾뵒�쓽 肄붾뵒 �젙蹂� 議고쉶
	public List<CodiVO> searchCodi(String userID, CodiVO codiFilter) throws DataAccessException; //�듅�젙 議곌굔�쓽 肄붾뵒 �젙蹂� 由ъ뒪�듃 議고쉶
	
	public String winAddCodi(MultipartHttpServletRequest multipartRequest) throws DataAccessException; //�쎒 �떎�뿕�슜 肄붾뵒 異붽�
	public String addCodi(MultipartHttpServletRequest multipartRequest) throws DataAccessException; //肄붾뵒 異붽�
	public String modifyCodi(CodiVO codiInfo) throws DataAccessException; //肄붾뵒 �젙蹂� �닔�젙
	//�궗吏꾩쓣 蹂�寃쏀븷 寃쎌슦�쓽 �닔瑜� �깮媛곹빐�빞 �븿.
	public String deleteCodi(String codiNo) throws DataAccessException; //肄붾뵒 �궘�젣 (1,2,3,4,5濡� �뿬�윭 媛� 媛��뒫) --二쇱쓽
	//FileDeleteController 援ы쁽 �썑 洹� �븿�닔 �궗�슜.
}
