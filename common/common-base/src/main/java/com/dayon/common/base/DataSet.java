package com.dayon.common.base;

import java.util.HashSet;

public class DataSet extends HashSet<DataMap> {
	private static final long serialVersionUID = -5710859915839910649L;

	public DataSet[] toArray() {
		return super.toArray(new DataSet[] {});
	}

}
