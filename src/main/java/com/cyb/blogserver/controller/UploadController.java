package com.cyb.blogserver.controller;

import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.utils.MyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(value="/upload")
public class UploadController {

	private static String IMG_PATH;

	static {
		Resource resource = new ClassPathResource("");
		String filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
		IMG_PATH = System.getProperty("user.dir") + "\\src\\main\\webapp\\";
		System.out.println("Common Image Path is: " + IMG_PATH);
	}

	@PostMapping("/teclearning")
	@ResponseBody
	public Tips upload(@RequestParam("file") MultipartFile file) {

		Tips tips = new Tips("文件上传失败", false, true);
		if (StringUtils.isEmpty(IMG_PATH) || null == file || file.isEmpty()) {
			return tips;
		}
		String fileName = file.getOriginalFilename();
		String fileType = MyUtils.getFileTypeByFullName(fileName);
		String savePath = "image/teclearning/" + MyUtils.getPrimaryKey()+fileType;
		try {
			File dest = new File(IMG_PATH + savePath);
			if(dest.exists()){
				dest.delete();
			}
			dest.createNewFile();
			file.transferTo(dest);
			tips = new Tips("文件上传成功", false, true);
			tips.setData(savePath);
			return tips;
		} catch (IOException e) {
			e.printStackTrace();
			return tips;
		}
	}
}
