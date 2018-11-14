package com.dayon.common.base.model;

import java.util.HashSet;
import java.util.Map;

import com.dayon.common.base.model.util.JsonUtil;

public class DataSet extends HashSet<Map<String, Object>> {
	private static final long serialVersionUID = -5710859915839910649L;

	@Override
	public String toString() {
		return JsonUtil.toJson(this);
	}

}
