package com.my.shareonfoot.clothes.controller;

import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
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

import com.my.shareonfoot.clothes.dao.ClothesDAO;
import com.my.shareonfoot.clothes.service.ClothesService;
import com.my.shareonfoot.clothes.vo.ClothesVO;
import com.my.shareonfoot.user.controller.UserControllerImpl;
import com.my.shareonfoot.user.vo.UserVO;



//3. 500 �뿉�윭 �썝�씤 �빐紐�
//4. �뙆�씪 �궘�젣 �쑀�떥
//5. �븳 踰덉뿉 �뿬�윭 媛� �궘�젣
//7. �븯�쐞 �젅肄붾뱶媛� �궓�븘 �엳�쑝硫� �긽�쐞 �젅肄붾뱶瑜� 吏��슱 �닔 �뾾�뒗 臾몄젣 �빐寃�
//8. �샆 �젙蹂� �닔�젙�븯湲� 援ы쁽


@RestController("clothesController")
@RequestMapping("/clothes")
public class ClothesControllerImpl implements ClothesController {

	//Logger �겢�옒�뒪 媛앹껜 媛��졇�삤湲�
	private static final Logger logger = LoggerFactory.getLogger(ClothesControllerImpl.class);
	
	
	@Autowired
	private ClothesService clothesService;
	@Autowired
	private ClothesDAO clothesDAO;
	@Autowired
	ClothesVO clothesVO;
	
	//�샆 異붽� �뀒�뒪�듃�슜 �뤌(�쎒. 愿�由ъ슜)
	@RequestMapping(value = "/addform")
	public ModelAndView addTestform() {
		return new ModelAndView("clothes/addTestUploadForm");
	}
	
	//�쟾泥� �샆 議고쉶 (�쎒. 愿�由ъ슜)
	@Override
	@RequestMapping(value = "/clolist", method = RequestMethod.GET)
	public ModelAndView clotheslist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName(request));
		List<ClothesVO> clothesList = clothesService.listAllClothes();
		mav.addObject("clothesList", clothesList);
		return mav;
	}
	
	//�궡 �샆 �쟾遺� 議고쉶
	@Override
	@RequestMapping(value = "/share", method = RequestMethod.GET)
	public ResponseEntity<List<ClothesVO>> myAllClothes(@RequestParam String userID, @RequestParam String page, @RequestParam String pageSize) throws Exception{
		List<ClothesVO> myclolist;
		try{
			ClothesVO cloVO = new ClothesVO();
			if(!page.isEmpty()&&!pageSize.isEmpty()) {
				int pageInt = Integer.parseInt(page);
				int pageSizeInt = Integer.parseInt(pageSize);
				cloVO.setPageStart(pageInt*pageSizeInt);
				cloVO.setPageSize(pageSizeInt);
			}
			myclolist = clothesService.myAllClothes(userID, cloVO);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ClothesVO>>(Collections.<ClothesVO>emptyList(), HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<List<ClothesVO>>(myclolist, HttpStatus.OK);
	}
	
	//�샆 �젙蹂� 蹂닿린
	@Override
	@RequestMapping(value = "/info/{no}", method = RequestMethod.GET)
	public ResponseEntity<ClothesVO> infoClothes(@PathVariable("no") String no) throws Exception {
		ClothesVO clothesInfo;
		try {
			clothesInfo = clothesService.infoClothes(no);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ClothesVO>(new ClothesVO(), HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<ClothesVO>(clothesInfo, HttpStatus.OK);
	}

	//�샆 李얘린
	@Override
	@RequestMapping(value = "/search", method = RequestMethod.PUT)
	public ResponseEntity<List<ClothesVO>> searchClothes(@RequestBody ClothesVO clothesVO, @RequestParam String userID,
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "pageSize", required = false) String pageSize) throws Exception {
		List<ClothesVO> searched_clolist;
		try{
			if(page!=null&&pageSize!=null&&!page.isEmpty()&&!pageSize.isEmpty()) {
				int pageInt = Integer.parseInt(page);
				int pageSizeInt = Integer.parseInt(pageSize);
				clothesVO.setPageStart(pageInt*pageSizeInt);
				clothesVO.setPageSize(pageSizeInt);
			}
			searched_clolist = clothesService.searchClothes(userID, clothesVO);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ClothesVO>>(Collections.<ClothesVO>emptyList(), HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<List<ClothesVO>>(searched_clolist, HttpStatus.OK);
	}
	
	//�샆 李얘린
	@Override
	@RequestMapping(value = "/search/by_list", method = RequestMethod.PUT)
	public ResponseEntity<List<ClothesVO>> searchClothesByList(
			@RequestBody HashMap map,
			@RequestParam String userID, 
			@RequestParam String mode,
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "pageSize", required = false) String pageSize) throws Exception {
		List<ClothesVO> searched_clolist;
		HashMap keywordMap = new HashMap();
		
		List<String> strArray = (List<String>) map.get("list");
		
		//String decodeResult = URLDecoder.decode(list, "UTF-8");
		//String[] strArray = decodeResult.split(",");
		
		if(map.containsKey("kind"))
			keywordMap.put("kind",map.get("kind"));
		keywordMap.put("userID",userID);
		keywordMap.put("location","private");
		keywordMap.put("mode", mode); //議곌굔 移쇰읆(ex detailCategory), 洹� 由ъ뒪�듃
		keywordMap.put(mode, strArray);
		
		if(page!=null&&pageSize!=null&&!page.isEmpty()&&!pageSize.isEmpty()) {
			int pageInt = Integer.parseInt(page);
			int pageSizeInt = Integer.parseInt(pageSize);
			keywordMap.put("pageStart",pageInt*pageSizeInt);
			keywordMap.put("pageSize",pageSizeInt);
		}else {
			keywordMap.put("pageStart",-1);
			keywordMap.put("pageSize",-1);
		}
		System.out.println("////////////"+userID+mode+strArray);
		
		for(String str : strArray) {
			System.out.println(str);
		}
		
		
		try{
			searched_clolist = clothesDAO.selectClothesByList(keywordMap);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ClothesVO>>(Collections.<ClothesVO>emptyList(), HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<List<ClothesVO>>(searched_clolist, HttpStatus.OK);
	}	

	//�샆 異붽��븯湲�
	@Override
	@RequestMapping(value = "/add", method = RequestMethod.POST, headers="Content-Type=multipart/form-data")
	public ResponseEntity<String> addClothes(MultipartHttpServletRequest multipartRequest,
			@RequestPart(value = "file", required = false) MultipartFile multipartFile) throws Exception{
		
		logger.debug("#create: multipartFile({})", multipartFile);
		//System.out.println(multipartFile.getName()); //�쐢�룄�슦 �뀒�뒪�듃�떆 x
		
		String answer = null;
		try {
			answer = clothesService.addClothes(multipartRequest);
			//�쐢�룄�슦 �떆�뿕�슜 : winAddClothes
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(answer, HttpStatus.OK);
	}

	
	//�뜲�씠�꽣濡� �샆 異붽�
	@Override
	@RequestMapping(value = "/add/data", method = RequestMethod.POST)
	public ResponseEntity<String> addClothesFrData(@RequestBody ClothesVO cloInfo) throws Exception {
		//@RequestBody : �쟾�넚�맂 �뙆�씪誘명꽣瑜� UserVO �빐�떦 �냽�꽦�뿉 �옄�룞�쑝濡� �꽕�젙 (JSON�쓣 VO濡� �옄�룞 蹂��솚)
		
		String answer = null;
		try {
			answer = clothesDAO.addClothesData(cloInfo);			
		} catch (Exception e) {
			return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(answer, HttpStatus.OK);
	}	
	
	
	
	
	//�샆 �젙蹂� �닔�젙
	@Override
	@RequestMapping(value = "/modify", method = RequestMethod.PUT)
	public ResponseEntity<String> modifyClothes(@RequestBody ClothesVO clothesInfo) throws Exception {
		System.out.println("諛쏆븘�삩 �샆 �젙蹂� : "+clothesInfo.getCloNo()+clothesInfo.getFavorite());
		
		String answer = null;
		try {
			answer = clothesService.modifyClothes(clothesInfo);
		} catch (Exception e) {
			return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(answer, HttpStatus.OK);
	}

	//�샆 �궘�젣
	@Override
	@RequestMapping(value = "/delete/{cloNo}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteClothes(@PathVariable("cloNo") String cloNo) throws Exception {
		String answer = null;
		try {
			answer= clothesService.deleteClothes(cloNo);
			System.out.println("而⑦듃濡ㅻ윭�뿉�꽌 諛쏆븘�삩 �쓳�떟 : "+answer);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE);
		}
		// TODO Auto-generated method stub
		return new ResponseEntity<String>(answer, HttpStatus.OK);
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
