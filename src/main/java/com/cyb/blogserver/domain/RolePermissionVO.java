package com.cyb.blogserver.domain;

import java.util.List;

public class RolePermissionVO extends UserRole{
    
	private List<Permission> permissions;

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public static RolePermissionVO toRolePermissionVO(UserRole userRole) {
		RolePermissionVO vo = new RolePermissionVO();
		vo.setId(userRole.getId());
		vo.setUserId(userRole.getUserId());
		vo.setRoleId(userRole.getRoleId());
		vo.setRemarks(userRole.getRemarks());
		return vo;
	}
}