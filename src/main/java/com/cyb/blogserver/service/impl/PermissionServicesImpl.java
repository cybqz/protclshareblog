package com.cyb.blogserver.service.impl;

import com.cyb.blogserver.dao.PermissionMapper;
import com.cyb.blogserver.domain.Permission;
import com.cyb.blogserver.domain.PermissionExample;
import com.cyb.blogserver.service.PermissionServices;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service(value="permissionServices")
public class PermissionServicesImpl implements PermissionServices {

	@Resource
	private PermissionMapper permissionMapper;
	
	public long countByExample(PermissionExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteByExample(PermissionExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Permission record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(Permission record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Permission> selectByExample(PermissionExample example) {
		// TODO Auto-generated method stub
		return null;
	}

	public Permission selectByPrimaryKey(String id) {
		return permissionMapper.selectByPrimaryKey(id);
	}

	public int updateByExampleSelective(Permission record, PermissionExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByExample(Permission record, PermissionExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKeySelective(Permission record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Permission record) {
		// TODO Auto-generated method stub
		return 0;
	}
}