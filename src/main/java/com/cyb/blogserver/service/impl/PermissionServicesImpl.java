package com.cyb.blogserver.service.impl;

import com.cyb.blogserver.dao.PermissionMapper;
import com.cyb.blogserver.domain.Permission;
import com.cyb.blogserver.service.PermissionServices;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service(value="permissionServices")
public class PermissionServicesImpl implements PermissionServices {

	@Resource
	private PermissionMapper permissionMapper;

	@Override
    public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Permission record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Permission record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Permission selectByPrimaryKey(String id) {
		return permissionMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Permission record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Permission record) {
		// TODO Auto-generated method stub
		return 0;
	}
}