package com.my.shareonfoot.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FileDownloadController {
	private static String CURR_IMAGE_REPO_PATH;

	// private static String CURR_IMAGE_REPO_PATH = "C:\\repo\\clothes_image"; //�쐢�룄�슦
	// �뀒�뒪�듃�슜
	// private static String CURR_IMAGE_REPO_PATH =
	// "/home/ubuntu/repo/clothes_image/";

	@RequestMapping("/download/{object}")
	protected void download(@PathVariable("object") String obj, @RequestParam("imageFileName") String imageFileName,
			HttpServletResponse response) throws Exception {
		
		
		switch(obj) {
		case "clothes":
			CURR_IMAGE_REPO_PATH = "/home/ubuntu/repo/clothes_image/";
			break;
		case "codi":
			CURR_IMAGE_REPO_PATH = "/home/ubuntu/repo/codi_image/";
			break;
		case "board":
			CURR_IMAGE_REPO_PATH = "/home/ubuntu/repo/board_image/";
			break;
		case "profile":
			CURR_IMAGE_REPO_PATH = "/home/ubuntu/repo/profile_image/";
			break;
		case "windows":
			CURR_IMAGE_REPO_PATH = "C:\\repo\\clothes_image\\"; // �쐢�룄�슦 �뀒�뒪�듃�슜
			break;
		}
		
		

		OutputStream out = response.getOutputStream();
		String downFile = CURR_IMAGE_REPO_PATH + imageFileName; // "\\" +
		File file = new File(downFile); //�뙆�씪 �깮�꽦

		
		//�뙆�씪 �쟾�넚�븯湲�
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + imageFileName);
		FileInputStream in = new FileInputStream(file);
		byte[] buffer = new byte[1024 * 8];
		while (true) {
			int count = in.read(buffer); // 踰꾪띁瑜� �씠�슜�빐 �븳 踰덉뿉 8KB�뵫 釉뚮씪�슦��濡� �쟾�넚
			if (count == -1)
				break;
			out.write(buffer, 0, count);
		}
		in.close();
		out.close();
	}

}
