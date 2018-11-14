package com.dayon.common.base.model;

import java.util.Date;
import java.util.HashMap;

import com.dayon.common.base.model.util.JsonUtil;

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

	public DataSet getDataSet(String key) {
		return (DataSet) super.get(key);
	}

	public String toString() {
		return JsonUtil.toJson(this);
	}

}
