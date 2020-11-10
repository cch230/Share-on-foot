package com.my.shareonfoot.clothes.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.my.shareonfoot.clothes.vo.ClothesVO;

public interface ClothesService {
	
	public List<ClothesVO> listAllClothes() throws DataAccessException; //紐⑤뱺 �샆�쓽 �샆 �젙蹂� 由ъ뒪�듃 議고쉶
	public List<ClothesVO> myAllClothes(String userID, ClothesVO clothesVO) throws DataAccessException; //�궡 �샆 �쟾遺� 議고쉶
	
	public ClothesVO infoClothes(String no) throws DataAccessException; //�듅�젙 �샆�쓽 �샆 �젙蹂� 議고쉶 -- int? String?
	public List<ClothesVO> searchClothes(String userID, ClothesVO clothesVO) throws DataAccessException; //�듅�젙 議곌굔�쓽 �샆 �젙蹂� 由ъ뒪�듃 議고쉶
	
	public String winAddClothes(MultipartHttpServletRequest multipartRequest) throws DataAccessException;
	public String addClothes(MultipartHttpServletRequest multipartRequest) throws DataAccessException; //�샆 異붽�
	public String modifyClothes(ClothesVO clothesInfo) throws DataAccessException; //�샆 �젙蹂� �닔�젙
	//�궗吏꾩쓣 蹂�寃쏀븷 寃쎌슦�쓽 �닔瑜� �깮媛곹빐�빞 �븿.
	public String deleteClothes(String no) throws DataAccessException; //�샆 �궘�젣 (1,2,3,4,5濡� �뿬�윭 媛� 媛��뒫) --二쇱쓽
	//FileDeleteController 援ы쁽 �썑 洹� �븿�닔 �궗�슜.
	
}
