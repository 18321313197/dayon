package com.dayon.common.model;

import java.util.LinkedList;

public class DataList extends LinkedList<DataMap> {
	private static final long serialVersionUID = -5710859915839910649L;

	@Override
	public String toString() {
		if (this.size() == 0) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder("[");
		for (DataMap data : this) {
			sb.append(data).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		return super.toString();
	}

}
