package com.cyb.blogserver.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.cyb.blogserver.dao.ParamesMapper;
import com.cyb.blogserver.service.ParamesServices;

@Service(value="paramesServices")
public class ParamesServicesImpl implements ParamesServices {
	
	@Resource
	private ParamesMapper paramesMapper;
}
