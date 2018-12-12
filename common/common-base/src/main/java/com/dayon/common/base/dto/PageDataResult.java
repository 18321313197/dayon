package com.dayon.common.base.dto;

import java.io.Serializable;
import java.util.List;

public class PageDataResult<T> implements Serializable {

	private static final long serialVersionUID = -2087289442900145206L;
	private int retNum = 0;
	private List<T> datas;
	private Paging paging;
	private String retMsg;

	public PageDataResult(int retNum, String retMsg) {
		this.retMsg = retMsg;
		this.retNum = retNum;
	}

	public PageDataResult(String retMsg, List<T> datas, Paging paging) {
		this.retMsg = retMsg;
		this.datas = datas;
		this.paging = paging;
	}

	public PageDataResult(List<T> datas, Paging paging) {
		this.datas = datas;
		this.paging = paging;
	}

	public PageDataResult() {

	}

	public List<T> getDatas() {
		return datas;
	}

	public PageDataResult<T> setDatas(List<T> datas) {
		this.datas = datas;
		return this;
	}

	public Paging getPaging() {
		return paging;
	}

	public PageDataResult<T> setPaging(Paging paging) {
		this.paging = paging;
		return this;
	}

	public int getRetNum() {
		return this.retNum;
	}

	public PageDataResult<T> setRetNum(int retNum) {
		this.retNum = retNum;
		return this;
	}

	public String getRetMsg() {
		return this.retMsg;
	}

	public PageDataResult<T> setRetMsg(String retMsg) {
		this.retMsg = retMsg;
		return this;
	}

}
