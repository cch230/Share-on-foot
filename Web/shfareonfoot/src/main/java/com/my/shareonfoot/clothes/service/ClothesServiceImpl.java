package com.my.shareonfoot.clothes.service;

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

import com.my.shareonfoot.clothes.dao.ClothesDAO;
import com.my.shareonfoot.clothes.vo.ClothesVO;
import com.my.shareonfoot.file.FileUploadController;
import com.my.shareonfoot.user.vo.LoginVO;


@Service("clothesService")
@Transactional(propagation=Propagation.REQUIRED) //�꽌鍮꾩뒪 �겢�옒�뒪�쓽 紐⑤뱺 硫붿꽌�뱶�뿉 �듃�옖�옲�뀡�쓣 �쟻�슜
public class ClothesServiceImpl implements ClothesService {

	@Autowired
	private ClothesDAO clothesDAO;
	@Autowired
	private FileUploadController uploadCon;
	
	@Override
	public List<ClothesVO> listAllClothes() throws DataAccessException {
		List<ClothesVO> clothesList = clothesDAO.selectAllClothes();
		return clothesList;
	}
	
	//�궡 �샆 �쟾遺� 議고쉶
	@Override
	public List<ClothesVO> myAllClothes(String userID, ClothesVO clothesVO) throws DataAccessException {
//		LoginVO loginVO;
//		try {
//			loginVO = (LoginVO) session.getAttribute("login");
//			System.out.println("�꽭�뀡�뿉 ���옣�맂 userID : "+loginVO.getUserID());
//		}catch(Exception e) {
//			System.out.println("�꽭�뀡�쓣 李얠쓣 �닔 �뾾�쓬.");
//			return null;
//		}
		clothesVO.setUserID(userID);

		return clothesDAO.selectClothes(clothesVO);
	}

	//�샆 �젙蹂� 蹂닿린
	@Override
	public ClothesVO infoClothes(String no) throws DataAccessException {
		return clothesDAO.selectThisClothes(no);
	}

	//�샆 李얘린
	@Override
	public List<ClothesVO> searchClothes(String userID, ClothesVO clothesVO) throws DataAccessException {
//		LoginVO loginVO;
//		try {
//			loginVO = (LoginVO) session.getAttribute("login");
//			System.out.println("�꽭�뀡�뿉 ���옣�맂 userID : "+loginVO.getUserID());
//		}catch(Exception e) {
//			System.out.println("�꽭�뀡�쓣 李얠쓣 �닔 �뾾�쓬.");
//			return null;
//		}
		if(!userID.equals("-1")) {
			clothesVO.setUserID(userID);
		}

		return clothesDAO.selectClothes(clothesVO);
	}

	@Override
	public String winAddClothes(MultipartHttpServletRequest multipartRequest) throws DataAccessException {
		
		//�뾽濡쒕뱶 而⑦듃濡ㅻ윭�쓽 upload �븿�닔濡� �궗吏� �뙆�씪+�뙆�씪誘명꽣 �젙蹂닿� �떞湲� request瑜� �븳爰쇰쾲�뿉 �꽆湲곌퀬, �뾽濡쒕뱶 �썑�뿉 �빐�돩留듭쑝濡� �냽�꽦 �젙蹂대�� 諛쏆븘�삩�떎.
		Map<String, Object> clothesMap = new HashMap<String, Object>();
		try {
			clothesMap=uploadCon.upload("windows", multipartRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�뾽濡쒕뱶 肄� �떎�뙣");
		}
		System.out.println("�빐�돩留� �깮�꽦 �꽦怨�. �빐�돩留듭쓣 異쒕젰�빀�땲�떎.");
		try {
		for(Map.Entry<String, Object> elem : clothesMap.entrySet()){
			 
            String key = elem.getKey();
            Object value = elem.getValue();
 
            System.out.println(key+" : "+value);
 
        }
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		//諛쏆븘�삩 �빐�돩留듭쓣 �씠�슜�빐 dao�뿉 �뜲�씠�꽣踰좎씠�뒪 異붽�瑜� �슂泥��븳�떎.
		return clothesDAO.addClothes(clothesMap);
		
	}
	
	@Override
	public String addClothes(MultipartHttpServletRequest multipartRequest) throws DataAccessException {
		
		//�뾽濡쒕뱶 而⑦듃濡ㅻ윭�쓽 upload �븿�닔濡� �궗吏� �뙆�씪+�뙆�씪誘명꽣 �젙蹂닿� �떞湲� request瑜� �븳爰쇰쾲�뿉 �꽆湲곌퀬, �뾽濡쒕뱶 �썑�뿉 �빐�돩留듭쑝濡� �냽�꽦 �젙蹂대�� 諛쏆븘�삩�떎.
		Map<String, Object> clothesMap = new HashMap<String, Object>();
		try {
			clothesMap=uploadCon.upload("clothes", multipartRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�뾽濡쒕뱶 肄� �떎�뙣");
		}
		System.out.println("�빐�돩留� �깮�꽦 �꽦怨�");
		for(Map.Entry<String, Object> elem : clothesMap.entrySet()){
			 
            String key = elem.getKey();
            Object value = elem.getValue();
 
            System.out.println(key+" : "+value);
 
        }
		
		//諛쏆븘�삩 �빐�돩留듭쓣 �씠�슜�빐 dao�뿉 �뜲�씠�꽣踰좎씠�뒪 異붽�瑜� �슂泥��븳�떎.
		return clothesDAO.addClothes(clothesMap);
		
	}
	
	

	@Override
	public String modifyClothes(ClothesVO clothesInfo) throws DataAccessException {
		//利먭꺼李얘린 DAO留� �셿�꽦.
		return clothesDAO.updateClothes(clothesInfo);
	}

	@Override
	public String deleteClothes(String no) throws DataAccessException {
		/*
		 �뿬�윭 �뻾 �궘�젣 :
		 $param = "1,2,3,4,5";
		 delete from table where id in ($param);
		 
		 荑쇰━臾몄뿉�꽌 in �쑝濡� 泥섎━
		 */
		return clothesDAO.deleteClothes(no);
	}



}
