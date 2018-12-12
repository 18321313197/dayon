package com.dayon.b2b2c.api.user.entity;

import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import java.lang.String;

public class UserAdmin implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String username;
	private String password;
	private String realname;
	private Date createTime;
	private String createBy;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username=username;
	}	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password=password;
	}	
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname=realname;
	}	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}	
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy=createBy;
	}	
	
}
