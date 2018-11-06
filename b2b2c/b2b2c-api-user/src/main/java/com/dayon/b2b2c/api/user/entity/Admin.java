package com.dayon.b2b2c.api.user.entity;

import java.util.Date;

import com.dayon.common.model.Entity;

public class Admin extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4188605465538629684L;

	private Long id;
	
	private Date createTime;
	private String createBy;
	private String username;
	private String password;
	private String realname;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}

	
}
