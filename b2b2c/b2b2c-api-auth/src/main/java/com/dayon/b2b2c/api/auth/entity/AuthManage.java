package com.dayon.b2b2c.api.auth.entity;

import java.lang.Long;
import java.util.Date;
import java.lang.String;

public class AuthManage{
	
	private Long id;
	private String name;
	private Long parentId;
	private String createBy;
	private Date createTime;
	private Long platformId;

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
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId=parentId;
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
	public Long getPlatformId() {
		return platformId;
	}
	public void setPlatformId(Long platformId) {
		this.platformId=platformId;
	}	
	
}
