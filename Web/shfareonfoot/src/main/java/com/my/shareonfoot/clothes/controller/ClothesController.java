package com.my.shareonfoot.clothes.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.my.shareonfoot.clothes.vo.ClothesVO;

public interface ClothesController {
	
	public ModelAndView clotheslist(HttpServletRequest request, HttpServletResponse response) throws Exception; //紐⑤뱺 �샆 由ъ뒪�듃 議고쉶
	public ResponseEntity<List<ClothesVO>> myAllClothes(String userID, String page, String pageSize) throws Exception; //�궡 �샆 �쟾遺� 議고쉶
	
	public ResponseEntity<ClothesVO> infoClothes(String no) throws Exception; //�샆 �젙蹂� 蹂닿린
	public ResponseEntity<List<ClothesVO>> searchClothes(ClothesVO clothesVO, String userID, String page, String pageSize) throws Exception; //�샆 李얘린
	public ResponseEntity<List<ClothesVO>> searchClothesByList(
			@RequestBody HashMap map,
			@RequestParam String userID, 
			@RequestParam String mode,
			@RequestParam String page, 
			@RequestParam String pageSize) throws Exception; //由ъ뒪�듃濡� �샆 李얘린
	
	public ResponseEntity<String> addClothes(MultipartHttpServletRequest multipartRequest,MultipartFile multipartFile) throws Exception; //�샆 異붽�
	public ResponseEntity<String> addClothesFrData(@RequestBody ClothesVO cloInfo) throws Exception;
	
	public ResponseEntity<String> modifyClothes(ClothesVO clothesInfo) throws Exception; //�샆 �젙蹂� �닔�젙
	public ResponseEntity<String> deleteClothes(String no) throws Exception; //�샆 �궘�젣
	
}
