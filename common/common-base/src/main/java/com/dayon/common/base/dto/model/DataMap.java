package com.dayon.common.base.dto.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DataMap implements Serializable {

	private static final long serialVersionUID = 1616199500541855749L;

	private Map<String, Object> map;

	public DataMap() {
		map = new HashMap<>();
	}

	public DataMap(Map<String, Object> map) {
		this.map = map;
	}

	@SuppressWarnings("unchecked")
	public <T extends Object> T get(String key) {
		return (T) map.get(key);
	}

	public DataMap put(String key, Object value) {
		map.put(key, value);
		return this;
	}

	public Set<String> getkeys() {
		return map.keySet();
	}

	public Collection<Object> getValues() {
		return map.values();
	}

	public Set<Entry<String, Object>> entrySet() {
		return map.entrySet();
	}

	public boolean containsKey(String key) {
		return map.containsKey(key);
	}

	@SuppressWarnings("unchecked")
	public <T extends Object> T remove(String key) {
		return (T) map.remove(key);
	}

	public DataMap clear() {
		map.clear();
		return this;
	}

	public int getSize() {
		return map.size();
	}
}
