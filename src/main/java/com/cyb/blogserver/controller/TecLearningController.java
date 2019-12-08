package com.cyb.blogserver.controller;

import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.domain.Chapter;
import com.cyb.blogserver.domain.TecLearning;
import com.cyb.blogserver.service.ChapterServices;
import com.cyb.blogserver.service.LoginServices;
import com.cyb.blogserver.service.TecLearningServices;
import com.cyb.blogserver.utils.MyStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/tecLearning")
public class TecLearningController {

	@Autowired
	private TecLearningServices tecLearningServices;

	@Autowired
	private ChapterServices chapterServices;

	@RequestMapping(value="/add")
	@ResponseBody
	public Tips add (TecLearning tecLearning, String chapterList) {
		Tips tips = new Tips("添加技术学习博客失败", true, false);
		if(null != tecLearning.getTitle()){
			tips.setMsg(tecLearning.getTitle());
		}
//		String teclearningID = MyStringUtils.getPrimaryKey();
//		tecLearning.setId(teclearningID);
//		int count = tecLearningServices.insert(tecLearning);
//		if(count > 0){
//			//List<Chapter> chapterList = tecLearning.getChapterList();
//			if(null != chapterList && chapterList.size() > 0){
//
//				for(Chapter chapter : chapterList){
//					chapter.setTeclearningId(teclearningID);
//					chapter.setId(MyStringUtils.getPrimaryKey());
//					chapterServices.insert(chapter);
//				}
//			}
//		}
		return tips;
	}
}
