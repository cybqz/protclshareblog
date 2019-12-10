package com.cyb.blogserver.service;

import com.cyb.blogserver.common.Pagenation;
import com.cyb.blogserver.domain.TecLearning;

import java.util.List;

public interface TecLearningServices {

    int deleteByPrimaryKey(String id);

    int insert(TecLearning tecLearning);

    TecLearning selectByPrimaryKey(String id);

    int updateByPrimaryKey(TecLearning tecLearning);

    TecLearning selectOneByTecLearning(TecLearning tecLearning);

    List<TecLearning> selectByTecLearning(TecLearning tecLearning, Pagenation pagenation);
}