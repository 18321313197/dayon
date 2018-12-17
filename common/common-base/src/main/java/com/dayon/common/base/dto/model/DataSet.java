package com.dayon.common.base.dto.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DataSet implements Serializable {
	private static final long serialVersionUID = -5710859915839910649L;

	private Set<DataMap> set;

	public DataSet() {
		set = new HashSet<>();
	}

	public DataSet(Set<DataMap> dataMaps) {
		set = dataMaps;
	}

	public DataSet add(DataMap dataMap) {
		set.add(dataMap);
		return this;
	}

	public DataSet remove(DataMap dataMap) {
		set.remove(dataMap);
		return this;
	}

	public DataSet clear() {
		set.clear();
		return this;
	}

	public int size() {
		return set.size();
	}

	public Iterator<DataMap> df() {
		return set.iterator();
	}

	public DataSet[] toArray() {
		return set.toArray(new DataSet[] {});
	}

}
