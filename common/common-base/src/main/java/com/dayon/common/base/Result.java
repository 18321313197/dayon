package com.dayon.common.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Result implements Serializable {

	private static final long serialVersionUID = 5496176979618739359L;

	private int retNum = 0;
	private String retMsg;
	private Map<String, Object> attributeMap;

	public Result(int retNum, String retMsg) {
		this.retMsg = retMsg;
		this.retNum = retNum;
	}

	public Result(String retMsg) {
		this.retMsg = retMsg;
	}

	public Result() {
	}

	public int getRetNum() {
		return this.retNum;
	}

	public Result setRetNum(int retNum) {
		this.retNum = retNum;
		return this;
	}

	public String getRetMsg() {
		return this.retMsg;
	}

	public Result setRetMsg(String retMsg) {
		this.retMsg = retMsg;
		return this;
	}

	@SuppressWarnings("unchecked")
	public <T extends Serializable> T getAttribute(String key) {
		if (key == null || this.attributeMap == null) {
			return null;
		}
		return (T) this.attributeMap.get(key);
	}

	public Result setAttribute(String key, Object value) {
		if (key == null || value == null) {
			return this;
		}
		if (this.attributeMap == null) {
			this.attributeMap = new HashMap<>();
		}
		this.attributeMap.put(key, value);
		return this;
	}

	public Result setDefaultAttribute(Object value) {
		if (value == null) {
			return this;
		}
		if (this.attributeMap == null) {
			this.attributeMap = new HashMap<>();
		}
		this.attributeMap.put(null, value);
		return this;
	}

	@SuppressWarnings("unchecked")
	public <T extends Object> T getDefaultAttribute() {
		if (this.attributeMap == null) {
			return null;
		}
		return (T) this.attributeMap.get(null);
	}
}
