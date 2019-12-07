package com.cyb.blogserver.dao;

import com.cyb.blogserver.domain.Chapter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ChapterMapper {

    int deleteByTecLearningId(@Param("tecLearningId") String tecLearningId);

    int deleteByPrimaryKey(@Param("id") String id);

    int insert(@Param("chapter") Chapter chapter);

    List<Chapter> selectByTecLearningId(@Param("tecLearningId") String tecLearningId);

    Chapter selectByPrimaryKey(@Param("id") String id);

    int updateByPrimaryKey(@Param("chapter") Chapter chapter);
}