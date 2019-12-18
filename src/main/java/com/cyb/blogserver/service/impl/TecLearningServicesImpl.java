package com.cyb.blogserver.service.impl;

import com.cyb.blogserver.common.Pagenation;
import com.cyb.blogserver.dao.TecLearningMapper;
import com.cyb.blogserver.domain.TecLearning;
import com.cyb.blogserver.service.TecLearningServices;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;

/**
 * 技术学习博客接口实现类
 */
@Service(value="tecLearningServices")
public class TecLearningServicesImpl implements TecLearningServices {
	
	@Resource
	private TecLearningMapper tecLearningMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		return tecLearningMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TecLearning tecLearning) { return tecLearningMapper.insert(tecLearning); }

	@Override
	public TecLearning selectByPrimaryKey(String id) {
		return tecLearningMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(TecLearning tecLearning) {
		return tecLearningMapper.updateByPrimaryKey(tecLearning);
	}

	@Override
	public TecLearning selectOneByTecLearning(TecLearning tecLearning){
		return tecLearningMapper.selectOneByTecLearning(tecLearning);
	}

	@Override
	public List<TecLearning> selectByTecLearning(TecLearning tecLearning, Pagenation pagenation) {
		List<TecLearning> resultList = null;
		Map<String, TecLearning> map = new HashMap<String, TecLearning>();
		List<TecLearning> list = tecLearningMapper.selectByTecLearning(tecLearning, pagenation);
		if(null != list && list.size() > 0){
			List<String> keyList = new ArrayList<String>();
			resultList = new ArrayList<TecLearning>();
			for(TecLearning t : list){
				if(map.containsKey(t.getId())){
					TecLearning mt = map.get(t.getId());
					mt.getChapterList().add(t.getChapterList().get(0));
					map.put(t.getId(), mt);
				}else{
					map.put(t.getId(), t);
					keyList.add(t.getId());
				}
			}
			for(String key : keyList){
				resultList.add(map.get(key));
			}
		}
		return resultList;
	}
}
