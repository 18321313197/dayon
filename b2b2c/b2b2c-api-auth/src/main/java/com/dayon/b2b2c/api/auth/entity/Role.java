package com.dayon.b2b2c.api.auth.entity;

import java.util.Date;

import com.dayon.common.model.Entity;

public class Role extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5390115945270917182L;
	
	private Long id;
	private String createBy;
	private Date createTime;
	private String name;
	private Long platformId;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getPlatformId() {
		return platformId;
	}
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
	
}
