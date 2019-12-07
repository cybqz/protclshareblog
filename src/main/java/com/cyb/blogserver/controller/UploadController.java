package com.cyb.blogserver.controller;

import com.cyb.blogserver.common.Tips;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/upload")
public class UploadController {

	@PostMapping("/upload")
	@ResponseBody
	public Tips upload(@RequestParam("file") MultipartFile file) {

		Tips tips = new Tips("文件上传失败", false, true);
		if (file.isEmpty()) {
			return tips;
		}
		String fileName = file.getOriginalFilename();
		String filePath = "/Users/itinypocket/workspace/temp/";
		File dest = new File(filePath + fileName);
		try {
			file.transferTo(dest);
			return tips = new Tips("文件上传成功", false, true);
		} catch (IOException e) {
			e.printStackTrace();
			return tips;
		}
	}
}
