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
	public Tips teclearning(@RequestParam("file") MultipartFile file) {

		Tips tips = new Tips("文件上传失败", false, true);
		if (isEmpty(file)) {
			return tips;
		}
		String savePath = "image/teclearning/" + getSavePaht(file);
		boolean save = saveFile(savePath, file);
		if(save){
			tips = new Tips("文件上传成功", false, true);
			tips.setData(savePath);
		}
		return tips;
	}

	@PostMapping("/chapter")
	@ResponseBody
	public Tips chapter(@RequestParam("file") MultipartFile file) {

		Tips tips = new Tips("文件上传失败", false, true);
		if (isEmpty(file)) {
			return tips;
		}
		String savePath = "image/chapter/" + getSavePaht(file);
		boolean save = saveFile(savePath, file);
		if(save){
			tips = new Tips("文件上传成功", false, true);
			tips.setData(savePath);
		}
		return tips;
	}

	/**
	 * 获取文件保存路径
	 * @param file
	 * @return
	 */
	private String getSavePaht(MultipartFile file){
		String fileName = file.getOriginalFilename();
		String fileType = MyUtils.getFileTypeByFullName(fileName);
		String savePath =  MyUtils.getPrimaryKey()+fileType;
		return savePath;
	}

	/**
	 * 非空判断
	 * @param file
	 * @return
	 */
	private boolean isEmpty(MultipartFile file){
		if (StringUtils.isEmpty(IMG_PATH) || null == file || file.isEmpty()) {
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 保存文件
	 * @param savePath
	 * @param file
	 * @return
	 */
	private boolean saveFile(String savePath, MultipartFile file){
		try{
			File dest = new File(IMG_PATH + savePath);
			if(dest.exists()){
				dest.delete();
			}
			dest.createNewFile();
			file.transferTo(dest);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
