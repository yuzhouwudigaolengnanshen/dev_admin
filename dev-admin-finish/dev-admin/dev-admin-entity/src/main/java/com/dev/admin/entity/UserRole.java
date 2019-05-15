package com.dev.admin.entity;

import java.io.Serializable;

/**
 * 用户角色
 */
public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private int userId;

	private int roleId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}