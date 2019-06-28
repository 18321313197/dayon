package com.dayon.common.base;

import java.util.LinkedList;

public class DataList extends LinkedList<DataMap> {
	private static final long serialVersionUID = -5710859915839910649L;

	public DataSet[] toArray() {
		return super.toArray(new DataSet[] {});
	}

}
