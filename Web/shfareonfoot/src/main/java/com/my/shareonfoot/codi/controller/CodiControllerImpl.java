package com.my.shareonfoot.codi.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.my.shareonfoot.clothes.vo.ClothesVO;
import com.my.shareonfoot.codi.dao.CodiDAO;
import com.my.shareonfoot.codi.service.CodiService;
import com.my.shareonfoot.codi.vo.CodiVO;
import com.my.shareonfoot.user.controller.UserControllerImpl;


@RestController("codiController")
@RequestMapping("/codi")
public class CodiControllerImpl implements CodiController {

	//Logger �겢�옒�뒪 媛앹껜 媛��졇�삤湲�
	private static final Logger logger = LoggerFactory.getLogger(CodiControllerImpl.class);
		
	@Autowired
	private CodiService codiService;
	@Autowired
	private CodiDAO codiDAO;
	@Autowired
	CodiVO codiVO;
	
	//肄붾뵒 異붽� �뀒�뒪�듃�슜 �뤌(�쎒. 愿�由ъ슜)
	@RequestMapping(value = "/addform")
	public ModelAndView addTestform() {
		return new ModelAndView("codi/addTestUploadForm");
	}
	
	//�쟾泥� 肄붾뵒 議고쉶 (�쎒. 愿�由ъ슜)
	@Override
	@RequestMapping(value = "/codilist", method = RequestMethod.GET)
	public ModelAndView codilist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName(request));
		List<CodiVO> codiList = codiService.listAllCodi();
		mav.addObject("codiList", codiList);
		return mav;
	}
	
	//�궡 肄붾뵒 �쟾遺� 議고쉶
	@Override
	@RequestMapping(value = "/share", method = RequestMethod.GET)
	public ResponseEntity<List<CodiVO>> myAllCodi(@RequestParam String userID, @RequestParam String page, @RequestParam String pageSize) throws Exception{
		List<CodiVO> myCodiList;
		try{
			CodiVO codiFilter = new CodiVO();
			if(!page.isEmpty()&&!pageSize.isEmpty()) {
				int pageInt = Integer.parseInt(page);
				int pageSizeInt = Integer.parseInt(pageSize);
				codiFilter.setPageStart(pageInt*pageSizeInt);
				codiFilter.setPageSize(pageSizeInt);
			}
			myCodiList = codiService.myAllCodi(userID, codiFilter);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<CodiVO>>(Collections.<CodiVO>emptyList(), HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<List<CodiVO>>(myCodiList, HttpStatus.OK);
	}
	
	//肄붾뵒 �젙蹂� 蹂닿린
	@Override
	@RequestMapping(value = "/info/{codiNo}", method = RequestMethod.GET)
	public ResponseEntity<CodiVO> infoCodi(@PathVariable("codiNo") String codiNo) throws Exception {
		CodiVO codiInfo;
		try {
			codiInfo = codiService.infoCodi(codiNo);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<CodiVO>(new CodiVO(), HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<CodiVO>(codiInfo, HttpStatus.OK);
	}

	//肄붾뵒 李얘린
	@Override
	@RequestMapping(value = "/search", method = RequestMethod.PUT)
	public ResponseEntity<List<CodiVO>> searchCodi(@RequestParam String userID, @RequestBody CodiVO codiFilter, @RequestParam String page, @RequestParam String pageSize) throws Exception {
		List<CodiVO> searched_codiList;
		try{
			if(!page.isEmpty()&&!pageSize.isEmpty()) {
				int pageInt = Integer.parseInt(page);
				int pageSizeInt = Integer.parseInt(pageSize);
				codiFilter.setPageStart(pageInt*pageSizeInt);
				codiFilter.setPageSize(pageSizeInt);
			}
			searched_codiList = codiService.searchCodi(userID, codiFilter);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<CodiVO>>(Collections.<CodiVO>emptyList(), HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<List<CodiVO>>(searched_codiList, HttpStatus.OK);
	}

	//肄붾뵒 異붽��븯湲�
	@Override
	@RequestMapping(value = "/add", method = RequestMethod.POST, headers="Content-Type=multipart/form-data")
	public ResponseEntity<String> addCodi(MultipartHttpServletRequest multipartRequest,
			@RequestPart(value = "file", required = false) MultipartFile multipartFile) throws Exception{
		
		logger.debug("#create: multipartFile({})", multipartFile);
		//System.out.println(multipartFile.getName()); //�쐢�룄�슦 �뀒�뒪�듃�떆 x
		
		String answer = null;
		try {
			answer = codiService.addCodi(multipartRequest);
			//�쐢�룄�슦 �떆�뿕�슜 : winAddCodi
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(answer, HttpStatus.OK);
	}

	
	//�뜲�씠�꽣濡� 肄붾뵒 異붽�
	@Override
	@RequestMapping(value = "/add/data", method = RequestMethod.POST)
	public ResponseEntity<String> addCodiFrData(@RequestBody CodiVO codiInfo) throws Exception {
		//@RequestBody : �쟾�넚�맂 �뙆�씪誘명꽣瑜� UserVO �빐�떦 �냽�꽦�뿉 �옄�룞�쑝濡� �꽕�젙 (JSON�쓣 VO濡� �옄�룞 蹂��솚)
		
		String answer = null;
		try {
			answer = codiDAO.addCodiData(codiInfo);			
		} catch (Exception e) {
			return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(answer, HttpStatus.OK);
	}		
		
	
	//肄붾뵒 �젙蹂� �닔�젙
	@Override
	@RequestMapping(value = "/modify", method = RequestMethod.PUT)
	public ResponseEntity<String> modifyCodi(@RequestBody CodiVO codiInfo) throws Exception {
		System.out.println("諛쏆븘�삩 肄붾뵒 �젙蹂� : "+codiInfo.getPlace()+codiInfo.getFileName());
				
		String answer = null;
		try {
			answer = codiService.modifyCodi(codiInfo);
		} catch (Exception e) {
			return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(answer, HttpStatus.OK);
	}

	//肄붾뵒 �궘�젣
	@Override
	@RequestMapping(value = "/delete/{codiNo}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCodi(@PathVariable("codiNo") String codiNo) throws Exception {
		String answer;
		try {
			answer= codiService.deleteCodi(codiNo);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("fail", HttpStatus.SERVICE_UNAVAILABLE);
		}
		// TODO Auto-generated method stub
		return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	private String getViewName(HttpServletRequest request) throws Exception {
		// �슂泥�紐� 援ы븯�뒗 �븿�닔. 泥ル쾲吏� �슂泥�紐낃퉴吏� �룷�븿. /user/join.do硫� -> user/join留� 異붿텧

		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String viewName = uri.substring(begin, end);
		if (viewName.indexOf(".") != -1) {
			viewName = viewName.substring(0, viewName.lastIndexOf("."));
		}
		if (viewName.lastIndexOf("/") != -1) {
			viewName = viewName.substring(viewName.lastIndexOf("/", 1), viewName.length());
		}
		return viewName;
	}
	
}
