package com.dayon.b2b2c.api.auth.entity;

import java.lang.Long;
import java.util.Date;
import java.io.Serializable;
import java.lang.Boolean;
import java.lang.String;

public class AuthPower implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String servletPath;
	private Boolean isMenu;
	private Long manageId;
	private Long platformId;
	private String createBy;
	private Date createTime;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}	
	public String getServletPath() {
		return servletPath;
	}
	public void setServletPath(String servletPath) {
		this.servletPath=servletPath;
	}	
	public Boolean getIsMenu() {
		return isMenu;
	}
	public void setIsMenu(Boolean isMenu) {
		this.isMenu=isMenu;
	}	
	public Long getManageId() {
		return manageId;
	}
	public void setManageId(Long manageId) {
		this.manageId=manageId;
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
