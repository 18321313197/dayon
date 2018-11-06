package com.dayon.common.model;

import java.util.HashMap;
import java.util.Map;

public class Result {
	private int retNum;
	private String retMsg;
	private Map<String, Object> attributeMap;

	public int getRetNum() {
		return this.retNum;
	}

	public void setRetNum(int retNum) {
		this.retNum = retNum;
	}

	public String getRetMsg() {
		return this.retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public Object getAttribute(String key) {
		if (this.attributeMap == null) {
			return null;
		}
		return this.attributeMap.get(key);
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String key, Class<T> clazz) {
		if (this.attributeMap == null) {
			return null;
		}
		return (T) this.attributeMap.get(key);
	}

	public void setAttribute(String key, Object value) {
		if (value == null) {
			return;
		}
		if (this.attributeMap == null) {
			this.attributeMap = new HashMap<>();
		}
		this.attributeMap.put(key, value);
	}

}
