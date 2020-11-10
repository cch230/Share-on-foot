package com.my.shareonfoot.board.controller;

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

import com.my.shareonfoot.board.dao.BoardDAO;
import com.my.shareonfoot.board.service.BoardService;
import com.my.shareonfoot.board.vo.BoardVO;


@RestController("boardController")
@RequestMapping("/board")
public class BoardControllerImpl implements BoardController {

	//Logger �겢�옒�뒪 媛앹껜 媛��졇�삤湲�
	private static final Logger logger = LoggerFactory.getLogger(BoardControllerImpl.class);
		
	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	BoardVO boardVO;

	//紐⑤뱺 寃뚯떆湲� 由ъ뒪�듃 議고쉶. �쎒 愿�由ъ슜.
	@Override
	@RequestMapping(value = "/boardlist", method = RequestMethod.GET)
	public ModelAndView boardlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName(request));
		List<BoardVO> boardList = boardService.boardlist();
		mav.addObject("boardList", boardList);
		return mav;
	}
	
	//紐⑤뱺 寃뚯떆湲� 由ъ뒪�듃 議고쉶
	@Override
	@RequestMapping(value = "/share", method = RequestMethod.GET)
	public ResponseEntity<List<BoardVO>> AllBoard(@RequestParam String page, @RequestParam String pageSize) throws Exception {
		List<BoardVO> boardList;
		try{
			boardList = boardService.listAllBoard(page, pageSize);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<BoardVO>>(Collections.<BoardVO>emptyList(), HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<List<BoardVO>>(boardList, HttpStatus.OK);
	}

	
	//�궡 寃뚯떆湲� �쟾遺� 議고쉶
	@Override
	@RequestMapping(value = "/my", method = RequestMethod.GET)
	public ResponseEntity<List<BoardVO>> myAllBoard(HttpSession session, @RequestParam String page, @RequestParam String pageSize) throws Exception{
		List<BoardVO> myBoardList;
		try{			
			myBoardList = boardService.myAllBoard(session, page, pageSize);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<BoardVO>>(Collections.<BoardVO>emptyList(), HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<List<BoardVO>>(myBoardList, HttpStatus.OK);
	}
	//�듅�젙 �쑀�� 寃뚯떆湲� �쟾遺� 議고쉶
	@Override
	@RequestMapping(value = "/{userID}", method = RequestMethod.GET)
	public ResponseEntity<List<BoardVO>> usersAllBoard(@PathVariable("userID") String userID, @RequestParam String page, @RequestParam String pageSize) throws Exception {
		List<BoardVO> myBoardList;
		try{			
			myBoardList = boardService.usersAllBoard(userID, page, pageSize);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<BoardVO>>(Collections.<BoardVO>emptyList(), HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<List<BoardVO>>(myBoardList, HttpStatus.OK);
	}
	
	
	//�듅�젙 寃뚯떆湲� 議고쉶
	@Override
	@RequestMapping(value = "/{boardNo}/info", method = RequestMethod.GET)
	public ResponseEntity<BoardVO> infoBoard(@PathVariable("boardNo") String boardNo) throws Exception {
		BoardVO boardInfo;
		try {
			boardInfo = boardService.infoBoard(boardNo);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<BoardVO>(new BoardVO(), HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<BoardVO>(boardInfo, HttpStatus.OK);
	}
	//�듅�젙 議곌굔�쓽 寃뚯떆湲� 由ъ뒪�듃 議고쉶
	@Override
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<List<BoardVO>> searchBoard(BoardVO boardFilter, @RequestParam String page, @RequestParam String pageSize) throws Exception {
		List<BoardVO> searched_boardList;
		try{
			searched_boardList = boardService.searchBoard(boardFilter, page, pageSize);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<BoardVO>>(Collections.<BoardVO>emptyList(), HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<List<BoardVO>>(searched_boardList, HttpStatus.OK);
	}
	
	//寃뚯떆湲� 異붽�
	@Override
	@RequestMapping(value = "/add", method = RequestMethod.POST, headers="Content-Type=multipart/form-data")
	public ResponseEntity<String> addBoard(MultipartHttpServletRequest multipartRequest,
			@RequestPart(value = "file", required = false) MultipartFile multipartFile) throws Exception{
		
		logger.debug("#create: multipartFile({})", multipartFile);
		//System.out.println(multipartFile.getName()); //�쐢�룄�슦 �뀒�뒪�듃�떆 x
		
		String answer = null;
		try {
			answer = boardService.addBoard(multipartRequest);
			//�쐢�룄�슦 �떆�뿕�슜 : winAddBoard
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(answer, HttpStatus.OK);
	}

	//寃뚯떆湲� �닔�젙
	@Override
	@RequestMapping(value = "/modify", method = RequestMethod.PUT)
	public ResponseEntity<String> modifyBoard(@RequestBody BoardVO boardInfo) throws Exception {
		System.out.println("諛쏆븘�삩 �젙蹂� : "+boardInfo.getContents()+boardInfo.getFileName());
				
		String answer = null;
		try {
			answer = boardService.modifyBoard(boardInfo);
		} catch (Exception e) {
			return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<String>(answer, HttpStatus.OK);
	}

	//寃뚯떆湲� �궘�젣
	@Override
	@RequestMapping(value = "/delete/{boardNo}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBoard(@PathVariable("boardNo") String boardNo) throws Exception {
		String answer;
		try {
			answer= boardService.deleteBoard(boardNo);
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
	
	public BoardVO genPageFilter(String page, String pageSize) {
		//�럹�씠吏� �븘�꽣 �깮�꽦
		BoardVO boardFilter = new BoardVO();
		if (page != null && pageSize != null) {
			int pageInt = Integer.parseInt(page);
			int pageSizeInt = Integer.parseInt(pageSize);
			boardFilter.setPageStart(pageInt * pageSizeInt);
			boardFilter.setPageSize(pageSizeInt);
		}
		return boardFilter;
	}
	
}
