package com.cyb.blogserver.service;

import com.cyb.blogserver.domain.Chapter;
import java.util.List;

public interface ChapterServices {

    int deleteByTecLearningId(String tecLearningId);

    int deleteByPrimaryKey(String id);

    int insert(Chapter chapter);

    List<Chapter> selectByTecLearningId(String tecLearningId);

    Chapter selectByPrimaryKey(String id);

    int updateByPrimaryKey(Chapter chapter);
}