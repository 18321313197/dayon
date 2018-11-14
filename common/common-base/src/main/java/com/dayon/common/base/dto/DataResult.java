package com.dayon.common.base.dto;

import java.io.Serializable;

public class DataResult<T> implements Serializable {

	private static final long serialVersionUID = 5496176979618739359L;

	private int retNum = 0;
	private String retMsg;
	private T data;
	
	public DataResult(int retNum, String retMsg, T data) {
		this.retMsg = retMsg;
		this.retNum = retNum;
		this.data=data;
	}
	
	public DataResult(int retNum, String retMsg) {
		this.retMsg = retMsg;
		this.retNum = retNum;
	}

	public DataResult(String retMsg, T data) {
		this.data=data;
		this.retMsg = retMsg;
	}
	
	public DataResult(String retMsg) {
		this.retMsg = retMsg;
	}

	public DataResult() {
	}

	public int getRetNum() {
		return this.retNum;
	}

	public DataResult<T> setRetNum(int retNum) {
		this.retNum = retNum;
		return this;
	}

	public String getRetMsg() {
		return this.retMsg;
	}

	public DataResult<T> setRetMsg(String retMsg) {
		this.retMsg = retMsg;
		return this;
	}

	public T getData() {
		return data;
	}

	public DataResult<T> setData(T data) {
		this.data = data;
		return this;
	}
}
