package com.cyb.blogserver.controller;

import com.cyb.blogserver.common.Pagenation;
import com.cyb.blogserver.common.Tips;
import com.cyb.blogserver.domain.Chapter;
import com.cyb.blogserver.domain.TecLearning;
import com.cyb.blogserver.service.ChapterServices;
import com.cyb.blogserver.service.TecLearningServices;
import com.cyb.blogserver.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="/tecLearning")
public class TecLearningController {

	@Autowired
	private TecLearningServices tecLearningServices;

	@Autowired
	private ChapterServices chapterServices;


	/**
	 * 添加技术学习博客
	 * @param tecLearning
	 * @return
	 */
	@RequestMapping(value="/add")
	@ResponseBody
	public Tips add (TecLearning tecLearning) {
		Tips tips = new Tips("添加技术学习博客成功！", true, true);
		String teclearningID = MyUtils.getPrimaryKey();
		Date now = new Date();
		tecLearning.setId(teclearningID);
        tecLearning.setCreateDate(now);
		TecLearning tecLearningParam = new TecLearning();
		tecLearningParam.setTitle(tecLearning.getTitle());
		TecLearning tecLearningTemp = tecLearningServices.selectOneByTecLearning(tecLearningParam);
		if(tecLearningTemp == null){
			int count = tecLearningServices.insert(tecLearning);
			if(count > 0){
				List<Chapter> chapterList = tecLearning.getChapterList();
				if(null != chapterList && chapterList.size() > 0){

					for(Chapter chapter : chapterList){
						chapter.setTeclearningId(teclearningID);
						chapter.setId(MyUtils.getPrimaryKey());
						chapter.setCreateDate(now);
						chapterServices.insert(chapter);
					}
				}
			}else{
				tips = new Tips("添加技术学习博客失败！", true, false);
			}
		}else{
			tips = new Tips("技术学习博客名称重复！", true, false);
		}
		return tips;
	}

	/**
	 * 查询技术学习博客
	 * @param pageIndex
	 * @param pageSize
	 */
	@RequestMapping(value="/selectByTecLearning")
	@ResponseBody
	public Tips selectByTecLearning(Integer pageIndex, Integer pageSize){

		TecLearning tecLearning = null;
		Pagenation pagenation = new Pagenation(pageSize, pageIndex);
        Tips tips = new Tips("查询技术学习博客成功！", true, true);
        tips.setData(tecLearningServices.selectByTecLearning(tecLearning, pagenation));
        return tips;
	}
}
