package com.dayon.b2b2c.api.auth.entity;

import java.lang.Long;
import java.util.Date;
import java.lang.String;

public class AuthUserRole{
	
	private Long id;
	private Long userId;
	private Long roleId;
	private Long platformId;
	private String createBy;
	private Date createTime;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId=userId;
	}	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId=roleId;
	}	
	public Long getPlatformId() {
		return platformId;
	}
	public void setPlatformId(Long platformId) {
		this.platformId=platformId;
	}	
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy=createBy;
	}	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}	
	
}
