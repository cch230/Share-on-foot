package com.my.shareonfoot.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.my.shareonfoot.user.vo.LoginVO;

@Controller
public class FileUploadController {
	
	private static String CURR_IMAGE_REPO_PATH;

	// server.xml : <Context docBase="/home/ubuntu/repo/clothes_image/"
	// path="/Closet/repo/clothes_image/" reloadable="true"/>

	// �뾽濡쒕뱶 �뼇�떇 蹂닿린 (�쎒)(�쐢�룄�슦/�샆)
	@RequestMapping(value = "/upload/winform")
	public String winform() {
		return "windowsUploadForm";
	}
	// �뾽濡쒕뱶 �뼇�떇 蹂닿린 (�쎒)(由щ늼�뒪/�샆)
	@RequestMapping(value = "/upload/awsform")
	public String awsform() {
		return "awsUploadForm";
	}


	
	//service �겢�옒�뒪 �궡遺��뿉�꽌 �벝 �븿�닔.
	public Map<String, Object> upload(String obj, MultipartHttpServletRequest multipartRequest) throws Exception {

		String userID;

		//�빐�돩留듭쑝濡� ���옣�븯湲�
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<?> enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			System.out.println(name + ", " + value);
			map.put(name, value);
		}
		userID = map.get("userID").toString();
		
		String new_name = userID+"_"+System.currentTimeMillis() + ".jpg"; //�궗�슜�옄紐낃낵 �쁽�옱 �떆媛꾩쑝濡� �뙆�씪�씠由� 留뚮뱾湲�
		System.out.println(new_name);
		String filePath=null;
		
		switch(obj) {
		case "clothes":
			CURR_IMAGE_REPO_PATH = "/home/ubuntu/repo/clothes_image/";
			filePath = "/download/clothes?imageFileName="+new_name;
			break;
		case "codi":
			CURR_IMAGE_REPO_PATH = "/home/ubuntu/repo/codi_image/";
			filePath = "/download/codi?imageFileName="+new_name;
			break;
		case "board":
			CURR_IMAGE_REPO_PATH = "/home/ubuntu/repo/board_image/";
			filePath = "/download/board?imageFileName="+new_name;
			break;
		case "profile":
			CURR_IMAGE_REPO_PATH = "/home/ubuntu/repo/profile_image/";
			filePath = "/download/profile?imageFileName="+new_name;
			break;
		case "windows":
			CURR_IMAGE_REPO_PATH = "C:\\repo\\clothes_image\\"; // �쐢�룄�슦 �뀒�뒪�듃�슜
			filePath = "/download/windows?imageFileName="+new_name;
			break;
		}
		
		List<?> fileList = fileProcess(new_name, multipartRequest); //�뙆�씪 ���옣, �썝蹂명뙆�씪 �씠由� 由ъ뒪�듃 諛쏆븘�샂. String.

		map.put("userID",userID);
		map.put("fileName", new_name);
		map.put("filePath", filePath);
		
		return map;  //�샆 �젙蹂닿� �떞湲� �빐�돩留듭쓣 諛섑솚�븿
	}

	private List<String> fileProcess(String new_name, MultipartHttpServletRequest multipartRequest) throws Exception {
		System.out.println("�뙆�씪 �봽濡쒖꽭�뒪 �뱾�뼱�샂");
		List<String> fileList = new ArrayList<String>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			String originalFileName = mFile.getOriginalFilename();
			fileList.add(originalFileName);
			File file = new File(CURR_IMAGE_REPO_PATH + new_name);
			System.out.println("�뙆�씪 �깮�꽦�맖");
			if (mFile.getSize() != 0) { // File Null Check
				if (!file.exists()) { // 寃쎈줈�뿉 �뙆�씪�씠 �뾾�쑝硫�
					if (file.getParentFile().mkdirs()) { // 洹� 寃쎈줈�뿉 �빐�떦�븯�뒗 �뵒�젆�꽣由щ�� 留뚮뱺 �썑
						file.createNewFile(); // �뙆�씪�쓣 �깮�꽦
					}
				}
				mFile.transferTo(new File(CURR_IMAGE_REPO_PATH + new_name)); // �엫�떆濡� ���옣�맂 multipartFile�쓣 �떎�젣 �뙆�씪濡� �쟾�넚
				System.out.println("�뙆�씪 �쟾�넚�맖");
			}
		}
		return fileList;
	}
	
	private String fileProcess(MultipartFile mFile) throws Exception {
		String originalFileName = mFile.getOriginalFilename();
		String fileName = mFile.getName(); //�닔�젙
	
		File file = new File(CURR_IMAGE_REPO_PATH + fileName);
		if (mFile.getSize() != 0) { // File Null Check
			if (!file.exists()) { // 寃쎈줈�뿉 �뙆�씪�씠 �뾾�쑝硫�
				if (file.getParentFile().mkdirs()) { // 洹� 寃쎈줈�뿉 �빐�떦�븯�뒗 �뵒�젆�꽣由щ�� 留뚮뱺 �썑
					file.createNewFile(); // �뙆�씪�쓣 �깮�꽦
				}
			}
			mFile.transferTo(new File(CURR_IMAGE_REPO_PATH + originalFileName)); // �엫�떆濡� ���옣�맂 multipartFile�쓣 �떎�젣 �뙆�씪濡� �쟾�넚
		}
		return originalFileName;
	}
	
	
	
	
	@RequestMapping(value = "/upload/{object}", method = RequestMethod.POST)
	public ModelAndView upload(@PathVariable("object") String obj, MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response) throws Exception {
		String userID="";
		
		if (obj.equals("clothes"))
			CURR_IMAGE_REPO_PATH = "/home/ubuntu/repo/clothes_image/";
		else if (obj.equals("codi"))
			CURR_IMAGE_REPO_PATH = "/home/ubuntu/repo/codi_image/";
		else if(obj.equals("windows"))
			CURR_IMAGE_REPO_PATH = "C:\\repo\\clothes_image\\"; // �쐢�룄�슦 �뀒�뒪�듃�슜 (�샆)

		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<?> enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			System.out.println(name + ", " + value);
			map.put(name, value);
		}


		//�꽭�뀡�쑝濡쒕��꽣 �쑀���븘�씠�뵒 諛쏆븘�삤湲�
		HttpSession httpSession = multipartRequest.getSession(false);
		if(httpSession ==null) {
			System.out.println("�꽭�뀡 �젙蹂� �뾾�쓬");
			userID = "a";
		}
		else {
			LoginVO loginVO = (LoginVO) httpSession.getAttribute("login");
			userID = loginVO.getUserID();
			System.out.println("userID : "+userID);
		}
		map.put("userID",userID);
		
		String new_name = userID+"_"+System.currentTimeMillis() + ".jpg"; //�궗�슜�옄紐낃낵 �쁽�옱 �떆媛꾩쑝濡� �뙆�씪�씠由� 留뚮뱾湲�
		List<?> fileList = fileProcess(new_name, multipartRequest); //�뙆�씪 ���옣, �썝蹂명뙆�씪 �씠由� 由ъ뒪�듃 諛쏆븘�샂. String.
		
		
		map.put("userID",userID);
		map.put("fileList", fileList);
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.setViewName("windowsUploadResult");
		if(obj.equals("clothes")||obj.equals("clothes"))
			mav.setViewName("awsUploadResult");
		return mav;
	}
	
	
	
}
