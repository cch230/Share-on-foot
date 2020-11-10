package com.my.shareonfoot.board.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.my.shareonfoot.board.dao.BoardDAO;
import com.my.shareonfoot.board.vo.BoardVO;
import com.my.shareonfoot.file.FileUploadController;
import com.my.shareonfoot.user.vo.LoginVO;


@Service("boardService")
@Transactional(propagation=Propagation.REQUIRED) //�꽌鍮꾩뒪 �겢�옒�뒪�쓽 紐⑤뱺 硫붿꽌�뱶�뿉 �듃�옖�옲�뀡�쓣 �쟻�슜
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private FileUploadController uploadCon;
	
	
	
	
	//紐⑤뱺 寃뚯떆湲� 由ъ뒪�듃 議고쉶. �쎒 愿�由ъ슜.
	@Override
	public List<BoardVO> boardlist() throws DataAccessException {
		return boardDAO.boardlist();
	}
	
	
	//紐⑤뱺 寃뚯떆湲� 由ъ뒪�듃 議고쉶.
	@Override
	public List<BoardVO> listAllBoard(String page, String pageSize) throws DataAccessException {
		
		//�럹�씠吏� �븘�꽣 �깮�꽦
		BoardVO boardFilter = new BoardVO();
		if (page != null && pageSize != null) {
			int pageInt = Integer.parseInt(page);
			int pageSizeInt = Integer.parseInt(pageSize);
			boardFilter.setPageStart(pageInt * pageSizeInt);
			boardFilter.setPageSize(pageSizeInt);
		}
		List<BoardVO> boardList = boardDAO.selectAllBoard(boardFilter);
		return boardList;
	}

	
	//�궡 寃뚯떆湲� �쟾遺� 議고쉶
	@Override
	public List<BoardVO> myAllBoard(HttpSession session, String page, String pageSize) throws DataAccessException {
		//�꽭�뀡�쑝濡쒕��꽣 �궡 �븘�씠�뵒 媛��졇�삤湲�
		LoginVO loginVO;
		try {
			loginVO = (LoginVO) session.getAttribute("login");
			System.out.println("�꽭�뀡�뿉 ���옣�맂 userID : "+loginVO.getUserID());
		}catch(Exception e) {
			System.out.println("�꽭�뀡�쓣 李얠쓣 �닔 �뾾�쓬.");
			return null;
		}
		//�궡 �븘�씠�뵒濡� �븘�꽣 �깮�꽦
		BoardVO boardFilter = new BoardVO();
		boardFilter.setUserID(loginVO.getUserID());
		//�럹�씠吏� �븘�꽣 �쟻�슜
		if (page != null && pageSize != null) {
			int pageInt = Integer.parseInt(page);
			int pageSizeInt = Integer.parseInt(pageSize);
			boardFilter.setPageStart(pageInt * pageSizeInt);
			boardFilter.setPageSize(pageSizeInt);
		}
		//�빐�떦 �븘�꽣濡� 寃��깋
		return boardDAO.selectBoard(boardFilter);	
	}
	
	//�듅�젙 �쑀�� 寃뚯떆湲� �쟾遺� 議고쉶
	@Override
	public List<BoardVO> usersAllBoard(String userID, String page, String pageSize) throws DataAccessException {
		//�빐�떦 �쑀�� �븘�씠�뵒濡� �븘�꽣 �깮�꽦
		BoardVO boardFilter = new BoardVO();
		boardFilter.setUserID(userID);
		//�럹�씠吏� �븘�꽣 �쟻�슜
		if (page != null && pageSize != null) {
			int pageInt = Integer.parseInt(page);
			int pageSizeInt = Integer.parseInt(pageSize);
			boardFilter.setPageStart(pageInt * pageSizeInt);
			boardFilter.setPageSize(pageSizeInt);
		}		
		//�빐�떦 �븘�꽣濡� 寃��깋
		return boardDAO.selectBoard(boardFilter);	
	}
	
	//�듅�젙 寃뚯떆湲� 議고쉶
	@Override
	public BoardVO infoBoard(String boardNo) throws DataAccessException {
		return boardDAO.selectThisBoard(boardNo);
	}
	//�듅�젙 議곌굔�쓽 寃뚯떆湲� 由ъ뒪�듃 議고쉶
	@Override
	public List<BoardVO> searchBoard(BoardVO boardFilter, String page, String pageSize) throws DataAccessException {
		//�럹�씠吏� �븘�꽣 �쟻�슜
		if (page != null && pageSize != null) {
			int pageInt = Integer.parseInt(page);
			int pageSizeInt = Integer.parseInt(pageSize);
			boardFilter.setPageStart(pageInt * pageSizeInt);
			boardFilter.setPageSize(pageSizeInt);
		}	
		return boardDAO.selectBoard(boardFilter);
	}


	//寃뚯떆湲� 異붽�
	@Override
	public String addBoard(MultipartHttpServletRequest multipartRequest) throws DataAccessException {
		//�뾽濡쒕뱶 而⑦듃濡ㅻ윭�쓽 upload �븿�닔濡� �궗吏� �뙆�씪+�뙆�씪誘명꽣 �젙蹂닿� �떞湲� request瑜� �븳爰쇰쾲�뿉 �꽆湲곌퀬, �뾽濡쒕뱶 �썑�뿉 �빐�돩留듭쑝濡� �냽�꽦 �젙蹂대�� 諛쏆븘�삩�떎.
		Map<String, Object> boardMap = new HashMap<String, Object>();
		try {
			boardMap=uploadCon.upload("board", multipartRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�뾽濡쒕뱶 肄� �떎�뙣");
		}
		System.out.println("�빐�돩留� �깮�꽦 �꽦怨�");
		for(Map.Entry<String, Object> elem : boardMap.entrySet()){
			 
            String key = elem.getKey();
            Object value = elem.getValue();
 
            System.out.println(key+" : "+value);
        }
		
		//諛쏆븘�삩 �빐�돩留듭쓣 �씠�슜�빐 dao�뿉 �뜲�씠�꽣踰좎씠�뒪 異붽�瑜� �슂泥��븳�떎.
		return boardDAO.addBoard(boardMap);
	}
	//寃뚯떆湲� 異붽� (�쐢�룄�슦 �뀒�뒪�듃�슜)
	@Override
	public String winAddBoard(MultipartHttpServletRequest multipartRequest) throws DataAccessException {
		//�뾽濡쒕뱶 而⑦듃濡ㅻ윭�쓽 upload �븿�닔濡� �궗吏� �뙆�씪+�뙆�씪誘명꽣 �젙蹂닿� �떞湲� request瑜� �븳爰쇰쾲�뿉 �꽆湲곌퀬, �뾽濡쒕뱶 �썑�뿉 �빐�돩留듭쑝濡� �냽�꽦 �젙蹂대�� 諛쏆븘�삩�떎.
		Map<String, Object> boardMap = new HashMap<String, Object>();
		try {
			boardMap=uploadCon.upload("windows", multipartRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�뾽濡쒕뱶 肄� �떎�뙣");
		}
		System.out.println("�빐�돩留� �깮�꽦 �꽦怨�");
		for(Map.Entry<String, Object> elem : boardMap.entrySet()){
			 
            String key = elem.getKey();
            Object value = elem.getValue();
 
            System.out.println(key+" : "+value);
        }
		
		//諛쏆븘�삩 �빐�돩留듭쓣 �씠�슜�빐 dao�뿉 �뜲�씠�꽣踰좎씠�뒪 異붽�瑜� �슂泥��븳�떎.
		return boardDAO.addBoard(boardMap);
	}

	@Override
	public String modifyBoard(BoardVO boardInfo) throws DataAccessException {
		return boardDAO.updateBoard(boardInfo);
	}

	@Override
	public String deleteBoard(String boardNo) throws DataAccessException {
		/*
		 �뿬�윭 �뻾 �궘�젣 :
		 $param = "1,2,3,4,5";
		 delete from table where id in ($param);
		 
		 荑쇰━臾몄뿉�꽌 in �쑝濡� 泥섎━
		 */
		//�궗吏� �궘�젣 泥섎━�룄 �빐�빞 �븿.
		return boardDAO.deleteBoard(boardNo);
	}

	



}
