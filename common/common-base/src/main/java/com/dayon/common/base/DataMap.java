package com.dayon.common.base;

import java.util.HashMap;

public class DataMap extends HashMap<String, Object> {

	private static final long serialVersionUID = 1616199500541855749L;

	@SuppressWarnings("unchecked")
	public <T extends Object> T get(String key) {
		return (T) super.get(key);
	}

	@SuppressWarnings("unchecked")
	public <T extends Object> T remove(String key) {
		return (T) super.remove(key);
	}

}
