package com.my.shareonfoot.user.controller;

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

import com.my.shareonfoot.board.vo.BoardVO;
import com.my.shareonfoot.user.dao.UserDAO;
import com.my.shareonfoot.user.service.UserService;
import com.my.shareonfoot.user.vo.LoginVO;
import com.my.shareonfoot.user.vo.UserVO;

@RestController("userController")
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

	//Logger �겢�옒�뒪 媛앹껜 媛��졇�삤湲�
	private static final Logger logger = LoggerFactory.getLogger(UserControllerImpl.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	UserVO userVO;
	
	//�쉶�썝 由ъ뒪�듃 蹂닿린(�쎒)
	@Override
	@RequestMapping(value = "/userlist", method = RequestMethod.GET)
	public ModelAndView userlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(System.currentTimeMillis());
		
		ModelAndView mav = new ModelAndView(getViewName(request));
		List<UserVO> userList = userService.listAllUsers();
		mav.addObject("userList", userList);
		return mav;
	}
	
	//�궡 �젙蹂� �솗�씤
	@Override
	@RequestMapping(value = "/myInfo/{userID}", method = RequestMethod.GET)
	public ResponseEntity<UserVO> myInfo(@PathVariable("userID") String userID) throws Exception {

		UserVO userInfo = null;
		try {
			userInfo = userService.infoUser(userID);
		} catch (Exception e) {
			return new ResponseEntity<UserVO>(userInfo, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<UserVO>(userInfo, HttpStatus.OK);
		//�옄�룞�쑝濡� JSON�쑝濡� 蹂��솚�빐�꽌 蹂대궡以�.
	}

	//�듅�젙 議곌굔�쓽 �궗�슜�옄 由ъ뒪�듃 議고쉶
	@Override
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<List<UserVO>> searchUser(UserVO userFilter, @RequestParam String page, @RequestParam String pageSize) throws Exception{
		List<UserVO> searched_userList;
		try{
			searched_userList = userService.searchUser(userFilter, page, pageSize);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<UserVO>>(Collections.<UserVO>emptyList(), HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<List<UserVO>>(searched_userList, HttpStatus.OK);
	}
	
	//�쉶�썝 媛��엯
	@Override
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ResponseEntity<String> join(@RequestBody UserVO userInfo) throws Exception {
		//@RequestBody : �쟾�넚�맂 �뙆�씪誘명꽣瑜� UserVO �빐�떦 �냽�꽦�뿉 �옄�룞�쑝濡� �꽕�젙 (JSON�쓣 VO濡� �옄�룞 蹂��솚)

		//ID, �씠硫붿씪 以묐났 �뿬遺� 泥댄겕
		String checkResult = userDAO.checkExistUser(userInfo);
		if(!"ok".equals(checkResult)) {
			//以묐났�떆 id, email �닚�쑝濡� �쓳�떟 蹂대깂
			return new ResponseEntity<String>(checkResult, HttpStatus.OK);
		}
		
		String answer = null;
		try {
			answer = userService.join(userInfo);

			logger.info("info �젅踰� - ID : " + userInfo.getUserID()); //濡쒓렇 硫붿떆吏� �젅踰⑥쓣 info濡� �꽕�젙
			
		} catch (Exception e) {
			return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(answer, HttpStatus.OK);
	}

	//濡쒓렇�씤
	@Override
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody LoginVO loginVO, HttpSession session) throws Exception {
		// @RequestBody : �쟾�넚�맂 �뙆�씪誘명꽣瑜� VO �빐�떦 �냽�꽦�뿉 �옄�룞�쑝濡� �꽕�젙 (JSON�쓣 VO濡� �옄�룞 蹂��솚)
		
		try {
			LoginVO loVO = (LoginVO) session.getAttribute("login");
			System.out.println("�꽭�뀡�뿉 ���옣�맂 userID : "+loVO.getUserID());
		}catch(Exception e) {
			System.out.println("�꽭�뀡 �젙蹂� �뾾�쓬");
		}
		
		logger.info("ID : " + loginVO.getUserID());
		logger.info("Password : " + loginVO.getPwd());
		String answer = null;
		try {
			answer = userService.login(loginVO);
		} catch (Exception e) {
			session.invalidate();
			return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE);
		}
		
		if (answer.equals("true")) //濡쒓렇�씤 �꽦怨듭떆
			session.setAttribute("login", loginVO); //�꽭�뀡�뿉 濡쒓렇�씤 �젙蹂� (�깉濡�) 諛붿씤�뵫
		else //�떎�뙣�떆
			session.invalidate(); //�꽭�뀡 �궇由�
		
		return new ResponseEntity<String>(answer, HttpStatus.OK);
	}

	//�쉶�썝�젙蹂� �닔�젙
	@Override
	@RequestMapping(value = "/modify", method = RequestMethod.PUT)
	public ResponseEntity<String> modifyUser(@RequestBody UserVO userInfo) throws Exception {
		String answer = null;
		try {
			answer = userService.modifyUser(userInfo);
		} catch (Exception e) {
			return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(answer, HttpStatus.OK);
	}

	//�봽濡쒗븘 �궗吏� 蹂�寃�
	@Override
	@RequestMapping(value = "/modify/profile_image", method = RequestMethod.POST, headers="Content-Type=multipart/form-data")
	public ResponseEntity<String> modifyProfileImage(MultipartHttpServletRequest multipartRequest,
			@RequestPart(value = "file", required = false) MultipartFile multipartFile) throws Exception {
		
		String answer = null;
		try {
			answer = userService.modifyProfileImage(multipartRequest); //win
			
		} catch (Exception e) {
			return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(answer, HttpStatus.OK);
	}	
	
	
	//�쉶�썝 �궘�젣
	@Override
	@RequestMapping(value = "/delete/{userID}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteAccount(@PathVariable("userID") String userID) throws Exception {
		String answer = null;
		try {
			answer = userService.deleteAccount(userID);
		} catch (Exception e) {
			return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(answer, HttpStatus.OK);
	}
	
	//jsp �뼇�떇 蹂닿린(�쎒)
	@Override
	@RequestMapping(value = "/*Form", method = RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName(request));
		return mav;
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
