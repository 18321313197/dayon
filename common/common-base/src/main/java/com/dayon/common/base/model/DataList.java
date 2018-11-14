package com.dayon.common.base.model;

import java.util.LinkedList;
import java.util.Map;

import com.dayon.common.base.model.util.JsonUtil;

public class DataList extends LinkedList<Map<String, Object>> {
	private static final long serialVersionUID = -5710859915839910649L;

	public DataMap getDataMap(int index) {

		return (DataMap) this.get(index);

	}

	@Override
	public String toString() {
		return JsonUtil.toJson(this);
	}

}
