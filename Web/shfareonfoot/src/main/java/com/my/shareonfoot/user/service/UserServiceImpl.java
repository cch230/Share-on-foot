package com.my.shareonfoot.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.my.shareonfoot.closet.dao.ClosetDAO;
import com.my.shareonfoot.closet.vo.ClosetVO;
import com.my.shareonfoot.file.FileUploadController;
import com.my.shareonfoot.user.dao.UserDAO;
import com.my.shareonfoot.user.vo.LoginVO;
import com.my.shareonfoot.user.vo.UserVO;

@Service("userService")
@Transactional(propagation=Propagation.REQUIRED) //�꽌鍮꾩뒪 �겢�옒�뒪�쓽 紐⑤뱺 硫붿꽌�뱶�뿉 �듃�옖�옲�뀡�쓣 �쟻�슜
public class UserServiceImpl implements UserService {
	
	int defaultimageNum = 12; //�봽濡쒗븘 �뵒�뤃�듃�씠誘몄� 媛쒖닔
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private FileUploadController uploadCon;
	@Autowired
	private ClosetDAO closetDAO;

	@Override
	public List<UserVO> listAllUsers() throws DataAccessException {
		List<UserVO> userList = userDAO.selectAllUsers();
		return userList;
	}

	@Override
	public UserVO infoUser(String userID) throws DataAccessException {
		return userDAO.selectThisUser(userID);
	}
	
	@Override
	public List<UserVO> searchUser(UserVO userFilter, String page, String pageSize) throws DataAccessException{
		//�럹�씠吏� �븘�꽣 �쟻�슜
		if(!page.isEmpty()&&!pageSize.isEmpty()) {
			int pageInt = Integer.parseInt(page);
			int pageSizeInt = Integer.parseInt(pageSize);
			userFilter.setPageStart(pageInt*pageSizeInt);
			userFilter.setPageSize(pageSizeInt);
		}
		return userDAO.selectUser(userFilter);
	}

	
	@Override
	public String join(UserVO userInfo) throws DataAccessException {
		
		if(userInfo.getNickname()==null) //�땳�꽕�엫 �뵒�뤃�듃 �꽕�젙 = �븘�씠�뵒
			userInfo.setNickname(userInfo.getUserID());
		if(userInfo.getEmailChecked()==null) //�씠硫붿씪 泥댄겕�뿬遺� �뵒�뤃�듃 �꽕�젙
			userInfo.setEmailChecked("no");
		if(userInfo.getPfImageName()==null) { //�봽�궗 �뵒�뤃�듃 �꽕�젙
			Random random = new Random();
			String pfImageName = "default"+(random.nextInt(defaultimageNum)+1)+".png";
			String pfImagePath = "/download/profile?imageFileName="+pfImageName;
			userInfo.setPfImageName(pfImageName);
			userInfo.setPfImagePath(pfImagePath);
			System.out.println("�뵒�뤃�듃 �봽�궗紐� : "+pfImageName);
		}
		
		//�쉶�썝 異붽�
		String result = userDAO.addUser(userInfo);
		if("fail".equals(result))
			return result;
		
		//�쉶�썝媛��엯怨� �룞�떆�뿉 default �샆�옣 �깮�꽦
		ClosetVO closetVO = new ClosetVO(userInfo.getUserID(), "default", "no"); 
		result = closetDAO.insertCloset(closetVO);
		if("fail".equals(result))
			return "�샆�옣 �깮�꽦 �떎�뙣";
		
		return "ok";
	}
	
	
	
	@Override
	public String login(LoginVO loginVO) throws DataAccessException {
		String userID = loginVO.getUserID();
		String pwd = loginVO.getPwd();
		return userDAO.verifyIdPwd(userID, pwd);
	}

	@Override
	public String modifyUser(UserVO userInfo) throws DataAccessException {
		return userDAO.updateUser(userInfo);
	}

	

	
	
	@Override
	public String modifyProfileImage(MultipartHttpServletRequest multipartRequest) throws DataAccessException {
		
		//�뾽濡쒕뱶 而⑦듃濡ㅻ윭�쓽 upload �븿�닔濡� �궗吏� �뙆�씪+�뙆�씪誘명꽣 �젙蹂닿� �떞湲� request瑜� �븳爰쇰쾲�뿉 �꽆湲곌퀬, �뾽濡쒕뱶 �썑�뿉 �빐�돩留듭쑝濡� �냽�꽦 �젙蹂대�� 諛쏆븘�삩�떎.
		Map<String, Object> infoMap = new HashMap<String, Object>();
		try {
			infoMap=uploadCon.upload("profile", multipartRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�뾽濡쒕뱶 肄� �떎�뙣");
		}
		System.out.println("�빐�돩留� �깮�꽦 �꽦怨�");
		
		UserVO profileImageInfo = new UserVO();
		profileImageInfo.setUserID(infoMap.get("userID").toString());
		profileImageInfo.setPfImageName(infoMap.get("fileName").toString());
		profileImageInfo.setPfImagePath(infoMap.get("filePath").toString());
		
		//諛쏆븘�삩 �빐�돩留듭쓣 �씠�슜�빐 dao�뿉 �뜲�씠�꽣踰좎씠�뒪 異붽�瑜� �슂泥��븳�떎. (�쉶�썝 異붽�)
		return userDAO.updateUser(profileImageInfo);

	}

	@Override
	public String winmodifyProfileImage(MultipartHttpServletRequest multipartRequest) throws DataAccessException {
		
		//�뾽濡쒕뱶 而⑦듃濡ㅻ윭�쓽 upload �븿�닔濡� �궗吏� �뙆�씪+�뙆�씪誘명꽣 �젙蹂닿� �떞湲� request瑜� �븳爰쇰쾲�뿉 �꽆湲곌퀬, �뾽濡쒕뱶 �썑�뿉 �빐�돩留듭쑝濡� �냽�꽦 �젙蹂대�� 諛쏆븘�삩�떎.
		Map<String, Object> infoMap = new HashMap<String, Object>();
		try {
			infoMap=uploadCon.upload("windows", multipartRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�뾽濡쒕뱶 肄� �떎�뙣");
		}
		System.out.println("�빐�돩留� �깮�꽦 �꽦怨�");
		
		UserVO profileImageInfo = new UserVO();
		profileImageInfo.setUserID(infoMap.get("userID").toString());
		profileImageInfo.setPfImageName(infoMap.get("fileName").toString());
		profileImageInfo.setPfImagePath(infoMap.get("filePath").toString());
		
		//諛쏆븘�삩 �빐�돩留듭쓣 �씠�슜�빐 dao�뿉 �뜲�씠�꽣踰좎씠�뒪 異붽�瑜� �슂泥��븳�떎. (�쉶�썝 異붽�)
		return userDAO.updateUser(profileImageInfo);		
	}
	
	
	
	@Override
	public String deleteAccount(String userID) throws DataAccessException {
		return userDAO.deleteUser(userID);
	}

}
