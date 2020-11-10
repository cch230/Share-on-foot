package com.my.shareonfoot.codi.controller;

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

import com.my.shareonfoot.codi.vo.CodiVO;

public interface CodiController {
	
	public ModelAndView codilist(HttpServletRequest request, HttpServletResponse response) throws Exception; //紐⑤뱺 肄붾뵒 由ъ뒪�듃 議고쉶
	public ResponseEntity<List<CodiVO>> myAllCodi(String userID, String page, String pageSize) throws Exception; //�궡 肄붾뵒 �쟾遺� 議고쉶
	
	public ResponseEntity<CodiVO> infoCodi(String codiNo) throws Exception; //肄붾뵒 �젙蹂� 蹂닿린
	public ResponseEntity<List<CodiVO>> searchCodi(String userID, CodiVO codiFilter, String page, String pageSize) throws Exception; //肄붾뵒 李얘린
	
	public ResponseEntity<String> addCodi(MultipartHttpServletRequest multipartRequest, MultipartFile multipartFile) throws Exception; //肄붾뵒 異붽�
	public ResponseEntity<String> addCodiFrData(@RequestBody CodiVO codiInfo) throws Exception;
	
	public ResponseEntity<String> modifyCodi(CodiVO codiInfo) throws Exception; //肄붾뵒 �젙蹂� �닔�젙
	public ResponseEntity<String> deleteCodi(String codiNo) throws Exception; //肄붾뵒 �궘�젣
	
}
