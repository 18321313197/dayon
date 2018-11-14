package com.dayon.common.base.dto;

import java.io.Serializable;
import java.util.List;

public class PageFindResource<T> implements Serializable{
	
	private static final long serialVersionUID = -2087289442900145206L;
	private List<T> datas;
	private Paging paging;
	public PageFindResource(){
		
	}
	public PageFindResource(List<T> datas,Paging paging){
		
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
	
}
