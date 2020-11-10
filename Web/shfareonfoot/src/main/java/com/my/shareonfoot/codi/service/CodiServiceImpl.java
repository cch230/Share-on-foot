package com.my.shareonfoot.codi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.my.shareonfoot.codi.dao.CodiDAO;
import com.my.shareonfoot.codi.vo.CodiVO;
import com.my.shareonfoot.file.FileUploadController;
import com.my.shareonfoot.user.vo.LoginVO;


@Service("codiService")
@Transactional(propagation=Propagation.REQUIRED) //�꽌鍮꾩뒪 �겢�옒�뒪�쓽 紐⑤뱺 硫붿꽌�뱶�뿉 �듃�옖�옲�뀡�쓣 �쟻�슜
public class CodiServiceImpl implements CodiService {

	@Autowired
	private CodiDAO codiDAO;
	@Autowired
	private FileUploadController uploadCon;
	
	@Override
	public List<CodiVO> listAllCodi() throws DataAccessException {
		List<CodiVO> codiList = codiDAO.selectAllCodi();
		return codiList;
	}
	
	//�궡 肄붾뵒 �쟾遺� 議고쉶
	@Override
	public List<CodiVO> myAllCodi(String userID, CodiVO codiFilter) throws DataAccessException {
//		LoginVO loginVO;
//		try {
//			loginVO = (LoginVO) session.getAttribute("login");
//			System.out.println("�꽭�뀡�뿉 ���옣�맂 userID : "+loginVO.getUserID());
//		}catch(Exception e) {
//			System.out.println("�꽭�뀡�쓣 李얠쓣 �닔 �뾾�쓬.");
//			return null;
//		}
		//�럹�씠吏� �젙蹂대쭔 �떞湲� VO�뿉 �꽭�뀡�쑝濡쒕��꽣 諛쏆븘�삩 �쑀���븘�씠�뵒 �젙蹂� 臾띠쓬
		codiFilter.setUserID(userID);

		return codiDAO.selectCodi(codiFilter);
	}

	//肄붾뵒 �젙蹂� 蹂닿린
	@Override
	public CodiVO infoCodi(String codiNo) throws DataAccessException {
		return codiDAO.selectThisCodi(codiNo);
	}

	//肄붾뵒 李얘린
	@Override
	public List<CodiVO> searchCodi(String userID, CodiVO codiFilter) throws DataAccessException {
//		LoginVO loginVO;
//		try {
//			loginVO = (LoginVO) session.getAttribute("login");
//			System.out.println("�꽭�뀡�뿉 ���옣�맂 userID : "+loginVO.getUserID());
//		}catch(Exception e) {
//			System.out.println("�꽭�뀡�쓣 李얠쓣 �닔 �뾾�쓬.");
//			return null;
//		}
		codiFilter.setUserID(userID);

		return codiDAO.selectCodi(codiFilter);
	}

	@Override
	public String winAddCodi(MultipartHttpServletRequest multipartRequest) throws DataAccessException {
		
		//�뾽濡쒕뱶 而⑦듃濡ㅻ윭�쓽 upload �븿�닔濡� �궗吏� �뙆�씪+�뙆�씪誘명꽣 �젙蹂닿� �떞湲� request瑜� �븳爰쇰쾲�뿉 �꽆湲곌퀬, �뾽濡쒕뱶 �썑�뿉 �빐�돩留듭쑝濡� �냽�꽦 �젙蹂대�� 諛쏆븘�삩�떎.
		Map<String, Object> codiInfo = new HashMap<String, Object>();
		try {
			codiInfo=uploadCon.upload("windows", multipartRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�뾽濡쒕뱶 肄� �떎�뙣");
			return "fail";
		}
		System.out.println("�빐�돩留� �깮�꽦 �꽦怨�. �빐�돩留듭쓣 異쒕젰�빀�땲�떎.");
		for(Map.Entry<String, Object> elem : codiInfo.entrySet()){
			 
            String key = elem.getKey();
            Object value = elem.getValue();
 
            System.out.println(key+" : "+value);
 
        }
		
		//諛쏆븘�삩 �빐�돩留듭쓣 �씠�슜�빐 dao�뿉 �뜲�씠�꽣踰좎씠�뒪 異붽�瑜� �슂泥��븳�떎.
		return codiDAO.addCodi(codiInfo);
		
	}
	
	@Override
	public String addCodi(MultipartHttpServletRequest multipartRequest) throws DataAccessException {
		
		//�뾽濡쒕뱶 而⑦듃濡ㅻ윭�쓽 upload �븿�닔濡� �궗吏� �뙆�씪+�뙆�씪誘명꽣 �젙蹂닿� �떞湲� request瑜� �븳爰쇰쾲�뿉 �꽆湲곌퀬, �뾽濡쒕뱶 �썑�뿉 �빐�돩留듭쑝濡� �냽�꽦 �젙蹂대�� 諛쏆븘�삩�떎.
		Map<String, Object> codiInfo = new HashMap<String, Object>();
		try {
			codiInfo=uploadCon.upload("codi", multipartRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�뾽濡쒕뱶 肄� �떎�뙣");
		}
		System.out.println("�빐�돩留� �깮�꽦 �꽦怨�. �빐�돩留듭쓣 異쒕젰�빀�땲�떎.");
		for(Map.Entry<String, Object> elem : codiInfo.entrySet()){
			 
            String key = elem.getKey();
            Object value = elem.getValue();
 
            System.out.println(key+" : "+value);
 
        }
		
		//諛쏆븘�삩 �빐�돩留듭쓣 �씠�슜�빐 dao�뿉 �뜲�씠�꽣踰좎씠�뒪 異붽�瑜� �슂泥��븳�떎.
		return codiDAO.addCodi(codiInfo);
		
	}	
	

	@Override
	public String modifyCodi(CodiVO codiInfo) throws DataAccessException {
		return codiDAO.updateCodi(codiInfo);
	}

	@Override
	public String deleteCodi(String codiNo) throws DataAccessException {
		/*
		 �뿬�윭 �뻾 �궘�젣 :
		 $param = "1,2,3,4,5";
		 delete from table where id in ($param);
		 
		 荑쇰━臾몄뿉�꽌 in �쑝濡� 泥섎━
		 */
		//�궗吏� �궘�젣 泥섎━�룄 �빐�빞 �븿.
		return codiDAO.deleteCodi(codiNo);
	}



}
