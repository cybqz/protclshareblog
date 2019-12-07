package com.cyb.blogserver.service.impl;

import com.cyb.blogserver.dao.UserRoleMapper;
import com.cyb.blogserver.domain.UserRole;
import com.cyb.blogserver.service.UserRoleServices;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service(value="userRoleServices")
public class UserRoleServicesImpl implements UserRoleServices {

	@Resource
	private UserRoleMapper userRoleMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(UserRole record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(UserRole record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserRole selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(UserRole record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(UserRole record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UserRole> selectByUserId(String id) {
		return null;
	}
}