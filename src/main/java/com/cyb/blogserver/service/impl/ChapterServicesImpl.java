package com.cyb.blogserver.service.impl;

import com.cyb.blogserver.dao.ChapterMapper;
import com.cyb.blogserver.domain.Chapter;
import com.cyb.blogserver.service.ChapterServices;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service(value="chapterServices")
public class ChapterServicesImpl implements ChapterServices {
	
	@Resource
	private ChapterMapper chapterMapper;

	@Override
	public int deleteByTecLearningId(String tecLearningId) {
		return chapterMapper.deleteByTecLearningId(tecLearningId);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return chapterMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Chapter chapter) {
		return chapterMapper.insert(chapter);
	}

	@Override
	public List<Chapter> selectByTecLearningId(String tecLearningId) {
		return chapterMapper.selectByTecLearningId(tecLearningId);
	}

	@Override
	public Chapter selectByPrimaryKey(String id) {
		return chapterMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(Chapter chapter) {
		return chapterMapper.updateByPrimaryKey(chapter);
	}
}
