package com.dayon.common.model;

import java.util.LinkedList;

public class DataList extends LinkedList<DataMap> {
	private static final long serialVersionUID = -5710859915839910649L;

	@Override
	public String toString() {
		return DataList.toJson(this);
	}

	private static String toJson(DataList dataList) {
		if (dataList == null) {
			return null;
		}
		if (dataList.size() == 0) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder("[");
		for (DataMap data : dataList) {
			sb.append(data).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}

}
