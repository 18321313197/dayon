package com.dayon.b2b2c.api.auth.entity;

import java.util.Date;

import com.dayon.common.model.Entity;

public class Manage extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4188605465538629684L;

	private Long id;
	private String name;
	private Long parentId;
	private Date createTime;
	private String createBy;
	private Long platformId;

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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
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

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

}
