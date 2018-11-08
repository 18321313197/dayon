package com.dayon.common.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class DataMap extends HashMap<String, Object> {

	private static final long serialVersionUID = 1616199500541855749L;

	public Date getDate(String key) {
		return (Date) super.get(key);
	}

	public Boolean getBoolean(String key) {
		return (Boolean) super.get(key);
	}

	public Double getDouble(String key) {
		return (Double) super.get(key);
	}

	public Integer getInteger(String key) {
		return (Integer) super.get(key);
	}

	public Long getLong(String key) {
		return (Long) super.get(key);
	}

	public String getString(String key) {
		return (String) super.get(key);
	}

	public DataMap getDataMap(String key) {
		return (DataMap) super.get(key);
	}

	public DataList getDataList(String key) {
		return (DataList) super.get(key);
	}

	public String toString() {
		return toJson(this);
	}

	private static String toJson(DataMap dataMap) {
		if (dataMap == null) {
			return null;
		}
		if (dataMap.size() == 0) {
			return "{}";
		}
		DateFormat dateFormat = null;
		StringBuilder sb = new StringBuilder("{");
		Set<Entry<String, Object>> eset = dataMap.entrySet();
		for (Entry<String, Object> e : eset) {
			Object obj = e.getValue();
			sb.append("\"").append(e.getKey()).append("\":");
			if (obj == null) {
				sb.append("null");
			} else if (obj instanceof Boolean || obj instanceof Byte || obj instanceof Short || obj instanceof Integer
					|| obj instanceof Long || obj instanceof Float || obj instanceof Double || obj instanceof DataMap
					|| obj instanceof DataList) {
				sb.append(obj);
			} else if (obj instanceof Date) {
				if (dateFormat == null) {
					dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				}
				sb.append("\"").append(dateFormat.format(obj)).append("\"");
			} else {
				sb.append("\"").append(obj).append("\"");
			}
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("}");
		return sb.toString();
	}
}
