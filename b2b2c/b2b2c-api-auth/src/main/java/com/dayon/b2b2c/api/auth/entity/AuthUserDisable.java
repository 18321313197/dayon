package com.dayon.b2b2c.api.auth.entity;

import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import java.lang.String;

public class AuthUserDisable implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long userId;
	private Long platformId;
	private String note;
	private Date startTime;
	private Date stopTime;
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
	public Long getPlatformId() {
		return platformId;
	}
	public void setPlatformId(Long platformId) {
		this.platformId=platformId;
	}	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note=note;
	}	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime=startTime;
	}	
	public Date getStopTime() {
		return stopTime;
	}
	public void setStopTime(Date stopTime) {
		this.stopTime=stopTime;
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
