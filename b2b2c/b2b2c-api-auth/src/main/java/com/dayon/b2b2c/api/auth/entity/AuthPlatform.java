package com.dayon.b2b2c.api.auth.entity;

import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import java.lang.String;

public class AuthPlatform implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String domainName;
	private String note;
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
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName=domainName;
	}	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note=note;
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
