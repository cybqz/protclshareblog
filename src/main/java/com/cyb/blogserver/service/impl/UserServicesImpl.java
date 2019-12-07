package com.cyb.blogserver.service.impl;

import java.util.List;
import java.util.Set;
import javax.annotation.Resource;

import com.cyb.authority.utils.EncryptionDecrypt;
import org.springframework.stereotype.Service;
import com.cyb.blogserver.dao.UserMapper;
import com.cyb.blogserver.domain.User;
import com.cyb.blogserver.service.UserServices;

@Service(value="userSerivces")
public class UserServicesImpl implements UserServices {
	
	@Resource
	private UserMapper userMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(User record, String basePath) {
		String password = EncryptionDecrypt.encryptionMD5(record.getPassword());
		record.setPassword(password);
		Integer sex = record.getSex();
		String image = "/headportrait/";
		if(sex == 0) {
			image += "gril.png";
		}else {
			image += "boy.png";
		}
		record.setImage(image);
		return userMapper.insert(record);
	}

	@Override
	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User selectByPrimaryKey(String id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(User record) {
		return userMapper.updateByPrimaryKey(record);
	}

	@Override
    public Set<String> queryRolesByName(String userName) {
		return userMapper.queryRolesByName(userName);
	}
	
	@Override
	public Set<String> queryPermissionByName(String userName) {
		return userMapper.queryPermissionByName(userName);
	}

	@Override
	public List<User> selectByUserName(String username) {
		return null;
	}
}
