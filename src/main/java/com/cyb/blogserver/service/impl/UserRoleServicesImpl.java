package com.cyb.blogserver.service.impl;

import com.cyb.blogserver.dao.UserRoleMapper;
import com.cyb.blogserver.domain.UserRole;
import com.cyb.blogserver.domain.UserRoleExample;
import com.cyb.blogserver.service.UserRoleServices;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service(value="userRoleServices")
public class UserRoleServicesImpl implements UserRoleServices {

	@Resource
	private UserRoleMapper userRoleMapper;
	
	public long countByExample(UserRoleExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteByExample(UserRoleExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(UserRole record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(UserRole record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<UserRole> selectByExample(UserRoleExample example) {
		return userRoleMapper.selectByExample(example);
	}

	public UserRole selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByExampleSelective(UserRole record, UserRoleExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByExample(UserRole record, UserRoleExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKeySelective(UserRole record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(UserRole record) {
		// TODO Auto-generated method stub
		return 0;
	}
}