package com.cyb.blogserver.domain;

import java.util.List;

public class UserRolePermissionVO extends User{
    
	private List<RolePermissionVO> userRoles;

	public List<RolePermissionVO> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<RolePermissionVO> userRoles) {
		this.userRoles = userRoles;
	}
	
	public static UserRolePermissionVO toUserRolePermissionVO(User user) {
		UserRolePermissionVO vo = new UserRolePermissionVO();
		vo.setId(user.getId());
		vo.setSex(user.getSex());
		vo.setName(user.getName());
		vo.setEmail(user.getEmail());
		vo.setPhone(user.getPhone());
		vo.setImage(user.getImage());
		vo.setAddress(user.getAddress());
		vo.setPassword(user.getPassword());
		vo.setUserName(user.getUserName());
		return vo;
	}
}