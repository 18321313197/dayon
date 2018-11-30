package com.dayon.common.base.model;

import java.util.HashMap;

public class DataMap extends HashMap<String, Object> {

	private static final long serialVersionUID = 1616199500541855749L;

	@SuppressWarnings("unchecked")
	public <T> T get(String key,Class<T> type) {
		return (T)super.get(key);
	}
}
