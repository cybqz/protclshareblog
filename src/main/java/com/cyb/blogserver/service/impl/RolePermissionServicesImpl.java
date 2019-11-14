package com.cyb.blogserver.service.impl;

import com.cyb.blogserver.dao.RolePermissionMapper;
import com.cyb.blogserver.domain.RolePermission;
import com.cyb.blogserver.domain.RolePermissionExample;
import com.cyb.blogserver.service.RolePermissionServices;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service(value="rolePermissionServices")
public class RolePermissionServicesImpl implements RolePermissionServices {

	@Resource
	private RolePermissionMapper rolePermissionMapper;
	
	public long countByExample(RolePermissionExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteByExample(RolePermissionExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(RolePermission record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(RolePermission record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<RolePermission> selectByExample(RolePermissionExample example) {
		return rolePermissionMapper.selectByExample(example);
	}

	public RolePermission selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByExampleSelective(RolePermission record, RolePermissionExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByExample(RolePermission record, RolePermissionExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKeySelective(RolePermission record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(RolePermission record) {
		// TODO Auto-generated method stub
		return 0;
	}
}