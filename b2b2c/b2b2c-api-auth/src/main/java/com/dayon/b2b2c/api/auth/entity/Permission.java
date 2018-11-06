package com.dayon.b2b2c.api.auth.entity;

import java.util.Date;

import com.dayon.common.model.Entity;

public class Permission extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4840405976548959747L;
	
	private Long id;
	private String name;
	private String servletPath;
	private Boolean isMenu;
	private Long manageId;
	private Date createTime;
	private String createBy;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getServletPath() {
		return servletPath;
	}
	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}
	public Boolean getIsMenu() {
		return isMenu;
	}
	public void setIsMenu(Boolean isMenu) {
		this.isMenu = isMenu;
	}
	public Long getManageId() {
		return manageId;
	}
	public void setManageId(Long manageId) {
		this.manageId = manageId;
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
	
	
}
