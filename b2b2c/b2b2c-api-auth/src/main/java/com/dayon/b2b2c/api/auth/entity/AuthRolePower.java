package com.dayon.b2b2c.api.auth.entity;

import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import java.lang.String;

public class AuthRolePower implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long roleId;
	private Long powerId;
	private Long platformId;
	private String createBy;
	private Date createTime;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId=roleId;
	}	
	public Long getPowerId() {
		return powerId;
	}
	public void setPowerId(Long powerId) {
		this.powerId=powerId;
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
