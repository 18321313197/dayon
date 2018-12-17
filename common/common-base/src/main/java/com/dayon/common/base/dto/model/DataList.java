package com.dayon.common.base.dto.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DataList implements Serializable {
	private static final long serialVersionUID = -5710859915839910649L;

	private List<DataMap> list;

	public DataList() {
		this.list = new LinkedList<>();
	}

	public DataList(List<DataMap> dataMaps) {
		this.list = dataMaps;
	}

	public DataMap get(int index) {
		return this.get(index);
	}

	public DataList add(DataMap dataMap) {
		list.add(dataMap);
		return this;
	}

	public DataList remove(int index) {
		list.remove(index);
		return this;
	}

	public DataList clear() {
		list.clear();
		return this;
	}

	public int size() {
		return list.size();
	}

	public Iterator<DataMap> iterator() {
		return list.iterator();
	}

	public DataSet[] toArray() {
		return list.toArray(new DataSet[] {});
	}
}
