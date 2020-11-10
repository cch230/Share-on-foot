package com.my.shareonfoot.social.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.my.shareonfoot.social.dao.CrudDAO;
import com.my.shareonfoot.social.dao.SocialDAO;
import com.my.shareonfoot.social.vo.CommentFeedVO;
import com.my.shareonfoot.social.vo.CommentVO;
import com.my.shareonfoot.social.vo.DetailFeedVO;
import com.my.shareonfoot.social.vo.DetailFeedVO_Extended;
import com.my.shareonfoot.social.vo.ExpandedFeedVO;
import com.my.shareonfoot.social.vo.FeedVO;
import com.my.shareonfoot.social.vo.FollowVO;
import com.my.shareonfoot.social.vo.HeartVO;
import com.my.shareonfoot.social.vo.UserspaceVO;
import com.my.shareonfoot.user.vo.UserVO;
import com.my.shareonfoot.util.Util;

@RestController("socialController")
@RequestMapping("/social")
public class SocialControllerImpl implements SocialController {

	// Logger �겢�옒�뒪 媛앹껜 媛��졇�삤湲�
	private static final Logger logger = LoggerFactory.getLogger(SocialControllerImpl.class);

	@Autowired
	private SocialDAO socialDAO;
	@Autowired
	private CrudDAO crudDAO;

	/* �뵾�뱶 */
	// 紐⑤뱺 �뵾�뱶 由ъ뒪�듃 議고쉶. �쎒 愿�由ъ슜.
	@Override
	@RequestMapping(value = "/feedlist", method = RequestMethod.GET)
	public ModelAndView feedlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName(request));
		List<FeedVO> feedList = socialDAO.showAllFeed(new FollowVO());
		mav.addObject("feedList", feedList);
		return mav;
	}
	// �뵾�뱶 媛��졇�삤湲�
	/*
	@Override
	@RequestMapping(value = "/feed/newest", method = RequestMethod.GET)
	public ResponseEntity<List<FeedVO>> showAllFeed(
			@RequestParam(value = "myID", required = false) String myID,
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "pageSize", required = false) String pageSize) throws Exception {
		List<FeedVO> feedList;
		try {
			FollowVO followFilter = new FollowVO();
			followFilter.setFollowerID(myID);
			followFilter = Util.setPageFilter(followFilter, page, pageSize);
			feedList = socialDAO.showAllFeed(followFilter);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<FeedVO>>(Collections.<FeedVO>emptyList(),
					HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<List<FeedVO>>(feedList, HttpStatus.OK);
	}
	*/
	
	// �뙏濡쒖슦 �뵾�뱶 媛��졇�삤湲�
	/*
	@Override
	@RequestMapping(value = "/feed/following/{userID}", method = RequestMethod.GET)
	public ResponseEntity<List<FeedVO>> showFollowFeed(@PathVariable("userID") String userID,
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "pageSize", required = false) String pageSize) throws Exception {
		List<FeedVO> feedList;
		try {
			FollowVO followFilter = new FollowVO();
			followFilter.setFollowerID(userID);
			followFilter = Util.setPageFilter(followFilter, page, pageSize);
			feedList = socialDAO.showFollowFeed(followFilter);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<FeedVO>>(Collections.<FeedVO>emptyList(),
					HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<List<FeedVO>>(feedList, HttpStatus.OK);
	}
	*/
	
	
	// �뵾�뱶 議곌굔 寃��깋
	@Override
	@RequestMapping(value = "/feed/search", method = RequestMethod.PUT)
	public ResponseEntity<List<DetailFeedVO>> searchFeed(
			@RequestBody DetailFeedVO_Extended feedFilter,
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "pageSize", required = false) String pageSize) throws Exception {
		List<DetailFeedVO> feedList;
		try {
			if(page!=null) {
				feedFilter = Util.setPageFilter(feedFilter, page, pageSize);
			}
			feedList = socialDAO.searchFeed(feedFilter);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<DetailFeedVO>>(Collections.<DetailFeedVO>emptyList(),
					HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<List<DetailFeedVO>>(feedList, HttpStatus.OK);
	}
	
	
	/*
	// �빐�떦 �궗�슜�옄媛� 醫뗭븘�슂�븳 �뵾�뱶
	@Override
	@RequestMapping(value = "/space/{userID}/heart", method = RequestMethod.GET)
	public ResponseEntity<List<FeedVO>> showHeartFeed(@PathVariable("userID") String userID,
			@RequestParam(value = "myID", required = false) String myID,
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "pageSize", required = false) String pageSize) throws Exception {
		List<FeedVO> feedList;
		try {
			FollowVO followFilter = new FollowVO();
			followFilter.setFollowerID(myID);
			followFilter.setFollowedID(userID);
			followFilter = Util.setPageFilter(followFilter, page, pageSize);
			feedList = socialDAO.showHeartFeed(followFilter);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<FeedVO>>(Collections.<FeedVO>emptyList(),
					HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<List<FeedVO>>(feedList, HttpStatus.OK);
	}
	
	
	// �빐�떦 寃뚯떆臾� �꽭遺� �궡�슜 媛��졇�삤湲�
	@Override
	@RequestMapping(value = "/feed/detail/{boardNo}", method = RequestMethod.GET)
	public ResponseEntity<List<DetailFeedVO>> showDetailFeed(@PathVariable("boardNo") String boardNo,
			@RequestParam("myID") String myID) throws Exception {
		List<DetailFeedVO> feedAndChildList;
		try {
			HeartVO feedFilter = new HeartVO();
			feedFilter.setBoardNo(boardNo);
			feedFilter.setHearterID(myID);
			feedAndChildList = socialDAO.showDetailFeed(feedFilter);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<DetailFeedVO>>(Collections.<DetailFeedVO>emptyList(),
					HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<List<DetailFeedVO>>(feedAndChildList, HttpStatus.OK);
	}
	*/

	// �빐�떦 寃뚯떆臾� 肄붾찘�듃 媛��졇�삤湲�
	@Override
	@RequestMapping(value = "/comment/{boardNo}", method = RequestMethod.GET)
	public ResponseEntity<List<CommentFeedVO>> showCommentInBoard(@PathVariable("boardNo") String boardNo,
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "pageSize", required = false) String pageSize) throws Exception {
		List<CommentFeedVO> commentList;
		try {
			BoardVO boardFilter = genPageFilter(page, pageSize);
			boardFilter.setBoardNo(Integer.parseInt(boardNo));
			commentList = socialDAO.showCommentInBoard(boardFilter);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<CommentFeedVO>>(Collections.<CommentFeedVO>emptyList(),
					HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<List<CommentFeedVO>>(commentList, HttpStatus.OK);
	}

	
	/* �뙏濡쒖슦 */
	// 紐⑤뱺 �뙏濡쒖슦 由ъ뒪�듃 議고쉶. �쎒 愿�由ъ슜.
	@Override
	@RequestMapping(value = "/followlist", method = RequestMethod.GET)
	public ModelAndView followlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName(request));
		List<FollowVO> followList = crudDAO.selectFollow(new FollowVO());
		mav.addObject("followList", followList);
		return mav;
	}
	//�뙏濡쒖슦 寃��깋
	@Override
	@RequestMapping(value = "/follow/search", method = RequestMethod.GET)
	public ResponseEntity<List<FollowVO>> searchFollow(@RequestBody FollowVO followFilter) throws Exception {
		List<FollowVO> followList;
		try {
			followList = crudDAO.selectFollow(followFilter);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<FollowVO>>(Collections.<FollowVO>emptyList(),
					HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<List<FollowVO>>(followList, HttpStatus.OK);
	}
	
	//�뙏濡쒖슦 �긽�깭 蹂�寃�
	@Override
	@RequestMapping(value = "/follow/execute", method = RequestMethod.POST)
	public ResponseEntity<String> revertFollow(@RequestBody FollowVO followInfo) throws Exception {
		//@RequestBody : �쟾�넚�맂 �뙆�씪誘명꽣瑜� FollowVO �빐�떦 �냽�꽦�뿉 �옄�룞�쑝濡� �꽕�젙 (JSON�쓣 VO濡� �옄�룞 蹂��솚)

		String answer = null;
		
		//�뙏濡쒖슦 �뿬遺� 泥댄겕
		List<FollowVO> existFollow = crudDAO.selectFollow(followInfo);
		
		if(existFollow.size() == 0) { //�뙏濡쒖슦 �릺�뼱 �엳吏� �븡�쑝硫�
			try {
				answer = crudDAO.addFollow(followInfo); //�뙏濡쒖슦 異붽�
			} catch (Exception e) {
				return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE);
			}
			if(!"fail".equals(answer)) { //�꽦怨듭떆
				
				return new ResponseEntity<String>(answer+"_following", HttpStatus.OK); //�뙏濡쒖슦以� �긽�깭 硫붿떆吏� 蹂대깂
			}
		}
		else { //�뙏濡쒖슦�맂 �긽�깭硫�
			try {
				answer = crudDAO.deleteFollow(followInfo); //�뙏濡쒖슦 �궘�젣
			} catch (Exception e) {
				return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE);
			}
			if(!"fail".equals(answer)) { //�꽦怨듭떆
				
				return new ResponseEntity<String>(answer+"_not_following", HttpStatus.OK); //�뙏濡쒖슦以� �긽�깭 硫붿떆吏� 蹂대깂
			}
		}
		return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	/*�븯�듃*/
	// 紐⑤뱺 �븯�듃 由ъ뒪�듃 議고쉶. �쎒 愿�由ъ슜.
	@Override
	@RequestMapping(value = "/heartlist", method = RequestMethod.GET)
	public ModelAndView heartlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView(getViewName(request));
		List<HeartVO> heartList = crudDAO.selectHeart(new HeartVO());
		mav.addObject("heartList", heartList);
		return mav;
	}
	//�븯�듃 寃��깋
	@Override
	@RequestMapping(value = "/heart/search", method = RequestMethod.GET)
	public ResponseEntity<List<HeartVO>> searchHeart(@RequestBody HeartVO heartFilter) throws Exception {
		List<HeartVO> heartList;
		try {
			heartList = crudDAO.selectHeart(heartFilter);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<HeartVO>>(Collections.<HeartVO>emptyList(),
					HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<List<HeartVO>>(heartList, HttpStatus.OK);
	}
	
	//�븯�듃 �긽�깭 蹂�寃�
	@Override
	@RequestMapping(value = "/heart/execute", method = RequestMethod.POST)
	public ResponseEntity<String> revertHeart(@RequestBody HeartVO heartInfo) throws Exception {
		//@RequestBody : �쟾�넚�맂 �뙆�씪誘명꽣瑜� HeartVO �빐�떦 �냽�꽦�뿉 �옄�룞�쑝濡� �꽕�젙 (JSON�쓣 VO濡� �옄�룞 蹂��솚)

		String answer = null;
		
		//�뙏濡쒖슦 �뿬遺� 泥댄겕
		List<HeartVO> existHeart = crudDAO.selectHeart(heartInfo);
		
		if(existHeart.size() == 0) { //�뙏濡쒖슦 �릺�뼱 �엳吏� �븡�쑝硫�
			try {
				answer = crudDAO.addHeart(heartInfo); //�뙏濡쒖슦 異붽�
			} catch (Exception e) {
				return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE);
			}
			if(!"fail".equals(answer)) { //�꽦怨듭떆
				
				return new ResponseEntity<String>(answer+"_hearting", HttpStatus.OK); //�뙏濡쒖슦以� �긽�깭 硫붿떆吏� 蹂대깂
			}
		}
		else { //�뙏濡쒖슦�맂 �긽�깭硫�
			try {
				answer = crudDAO.deleteHeart(heartInfo); //�뙏濡쒖슦 �궘�젣
			} catch (Exception e) {
				return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE);
			}
			if(!"fail".equals(answer)) { //�꽦怨듭떆
				
				return new ResponseEntity<String>(answer+"_not_hearting", HttpStatus.OK); //�뙏濡쒖슦�븯怨좎엳吏��븡�쓬 �긽�깭 硫붿떆吏� 蹂대깂
			}
		}
		return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	
	
	
	//�뙎湲� 異붽�
	@Override
	@RequestMapping(value = "/comment/add", method = RequestMethod.POST)
	public ResponseEntity<String> addComment(@RequestBody CommentVO commentInfo) throws Exception {
		//@RequestBody : �쟾�넚�맂 �뙆�씪誘명꽣瑜� CommentVO �빐�떦 �냽�꽦�뿉 �옄�룞�쑝濡� �꽕�젙 (JSON�쓣 VO濡� �옄�룞 蹂��솚)

		String answer = null;
		
		try {
			answer = crudDAO.addComment(commentInfo);
		} catch (Exception e) {
			return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE); 
		}
		if(!"fail".equals(answer)) { 
			
			return new ResponseEntity<String>(answer, HttpStatus.OK); //�꽦怨듭떆 �쁽�옱 �뙎湲� 媛쒖닔 蹂대깂
		}
		
		return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE); 
	}	
		
	
	//�뙎湲� �궘�젣
	@Override
	@RequestMapping(value = "/comment/delete/{commentNo}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteComment(@PathVariable("commentNo") String commentNo,
			@RequestParam(value = "boardNo", required = false) String boardNo) throws Exception {
		//@RequestBody : �쟾�넚�맂 �뙆�씪誘명꽣瑜� CommentVO �빐�떦 �냽�꽦�뿉 �옄�룞�쑝濡� �꽕�젙 (JSON�쓣 VO濡� �옄�룞 蹂��솚)

		CommentVO commentFilter = new CommentVO();
		commentFilter.setCommentNo(commentNo);
		commentFilter.setBoardNo(boardNo);
		
		String answer = null;
		
		try {
			answer = crudDAO.deleteComment(commentFilter);
		} catch (Exception e) {
			return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE); 
		}
		if(!"fail".equals(answer)) { 
			
			return new ResponseEntity<String>(answer, HttpStatus.OK); //�꽦怨듭떆 �쁽�옱 �뙎湲� 媛쒖닔 蹂대깂
		}
		
		return new ResponseEntity<String>(answer, HttpStatus.SERVICE_UNAVAILABLE); 
	}		
			
	
	
	
	
	
	
	/*�쑀���뒪�럹�씠�뒪*/
	@Override
	@RequestMapping(value = "/space/{userID}", method = RequestMethod.GET)
	public ResponseEntity<UserspaceVO> showUserspace(@PathVariable("userID") String userID, @RequestParam("myID") String myID) throws Exception {
		UserspaceVO userspaceInfo;
		try {
			FollowVO interUserFilter = new FollowVO();
			interUserFilter.setFollowerID(myID);
			interUserFilter.setFollowedID(userID);
			userspaceInfo = socialDAO.showUserSpace(interUserFilter);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UserspaceVO>(new UserspaceVO(),
					HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<UserspaceVO>(userspaceInfo, HttpStatus.OK);
	}
	
	/*肄붾뵒 異붿쿇*/
	@Override
	@RequestMapping(value = "/recommend/full/{myID}", method = RequestMethod.GET)
	public ResponseEntity<List<DetailFeedVO>> recommendFull(@PathVariable("myID") String myID,
			@RequestParam(value = "tag", required = false) String tag) throws Exception {
		List<DetailFeedVO> feedList;
		
		try {
			HashMap<String, Object> recoFilter = new HashMap<String, Object>();
			recoFilter.put("myID",myID);
			recoFilter.put("numLimit",(int) (Math.random() * 3) +2); //2~4媛� 戮묒븘以�
			recoFilter.put("tag",tag);
			feedList = socialDAO.recommendFull(recoFilter);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<DetailFeedVO>>(Collections.<DetailFeedVO>emptyList(),
					HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity<List<DetailFeedVO>>(feedList, HttpStatus.OK);
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
		// �럹�씠吏� �븘�꽣 �깮�꽦
		BoardVO boardFilter = new BoardVO();
		if (page != null && pageSize != null) {
			int pageInt = Integer.parseInt(page);
			int pageSizeInt = Integer.parseInt(pageSize);
			boardFilter.setPageStart(pageInt * pageSizeInt);
			boardFilter.setPageSize(pageSizeInt);
		}
		return boardFilter;
	}
	
	public BoardVO setPageFilter(BoardVO boardFilter, String page, String pageSize) {
		// �럹�씠吏� �븘�꽣 �깮�꽦
		if (page != null && pageSize != null) {
			int pageInt = Integer.parseInt(page);
			int pageSizeInt = Integer.parseInt(pageSize);
			boardFilter.setPageStart(pageInt * pageSizeInt);
			boardFilter.setPageSize(pageSizeInt);
		}
		return boardFilter;
	}

}
