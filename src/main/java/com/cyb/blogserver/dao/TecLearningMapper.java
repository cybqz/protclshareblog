package com.cyb.blogserver.dao;

import com.cyb.blogserver.common.Pagenation;
import com.cyb.blogserver.domain.TecLearning;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TecLearningMapper {

    int deleteByPrimaryKey(@Param("id") String id);

    int insert(TecLearning tecLearning);

    TecLearning selectByPrimaryKey(@Param("id") String id);

    int updateByPrimaryKey(@Param("tecLearning") TecLearning tecLearning);

    List<TecLearning> selectByTecLearning(@Param("tecLearning") TecLearning tecLearning, @Param("pagenation") Pagenation pagenation);
}