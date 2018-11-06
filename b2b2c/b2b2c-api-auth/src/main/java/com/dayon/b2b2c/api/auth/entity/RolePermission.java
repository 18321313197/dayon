package com.dayon.b2b2c.api.auth.entity;

import java.util.Date;

import com.dayon.common.model.Entity;

public class RolePermission extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4409644623965027698L;
	private Long id;
	private String createBy;
	private Date createTime;
	private Long roleId;
	private Long permissionId;
	private Long platform;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	public Long getPlatform() {
		return platform;
	}
	public void setPlatform(Long platform) {
		this.platform = platform;
	}
	
}
