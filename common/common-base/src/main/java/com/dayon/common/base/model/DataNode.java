package com.dayon.common.base.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataNode<T> implements Serializable {
	private static final long serialVersionUID = 5866053491330330833L;
	private long id;
	private String name;
	private DataNode<T> parent;
	private T data;
	private List<DataNode<T>> childs = new LinkedList<>();
	private Map<Long, DataNode<T>> idchildMap = new HashMap<>();

	public DataNode() {
		this.id = this.hashCode();
	}

	public DataNode(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return this.data;
	}

	public void setParent(DataNode<T> parent) {
		this.parent = parent;
	}

	public DataNode<T> getParent() {
		return this.parent;
	}

	public List<DataNode<T>> getChilds() {
		return childs;
	}

	public boolean addChild(DataNode<T> child) {
		if (child.getParent() != null) {
			return false;
		}
		if (child.id == this.id) {
			return false;
		}
		if (child.getGenerationsChildById(this.id) != null) {
			return false;
		}
		this.childs.add(child);
		this.idchildMap.put(child.getId(), child);
		return true;
	}

	public DataNode<T> getChildById(long id) {
		return getChildById(this, id);
	}

	public DataNode<T> getGenerationsChildById(long id) {

		return getGenerationsChildById(this, id);
	}

	private static <T> DataNode<T> getGenerationsChildById(DataNode<T> node, long id) {
		DataNode<T> child = getChildById(node, id);
		if (child != null) {
			return child;
		}
		for (DataNode<T> c : node.childs) {
			child = getGenerationsChildById(c, id);
			if (child != null) {
				return child;
			}
		}
		return null;
	}

	private static <T> DataNode<T> getChildById(DataNode<T> node, long id) {
		if (node.childs.size() == 0) {
			return null;
		}
		return node.idchildMap.get(id);
	}

	public static <T> void settingNodeParent(DataNode<T> child, DataNode<T> parent) {
		if (child.id == parent.id) {
			return;
		}
		DataNode<T> ypt = child.getParent();
		ypt.childs.remove(child);
		ypt.idchildMap.remove(child.id);
		parent.childs.add(child);
		parent.idchildMap.put(child.getId(), child);
	}
}
