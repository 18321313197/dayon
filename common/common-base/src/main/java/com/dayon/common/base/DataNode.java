package com.dayon.common.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class DataNode<T> implements Serializable {
	private static final long serialVersionUID = 5866053491330330833L;
	private long id;
	private DataNode<T> parent;
	private T data;
	private List<DataNode<T>> childs = new ArrayList<>();
	private Map<Long, Integer> idChildIndexMap = new HashMap<>();

	private Predicate<DataNode<T>> removePredicate = new Predicate<DataNode<T>>() {
		@Override
		public boolean test(DataNode<T> item) {
			if (item.id == id) {
				return true;
			}
			return false;
		}
	};

	public DataNode() {
		this.id = (long) (Math.random() * Long.MAX_VALUE + 1);
	}

	public DataNode(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return this.data;
	}

	public boolean setParent(DataNode<T> parent) {
		if (parent.id == this.id) {
			return false;
		}
		if (this.parent != null) {
			this.parent.childs.removeIf(removePredicate);
			this.parent.idChildIndexMap.remove(this.id);
		}
		parent.childs.removeIf(removePredicate);
		parent.idChildIndexMap.put(this.id, parent.childs.size());
		parent.childs.add(this);
		this.parent = parent;
		return true;
	}

	public DataNode<T> getParent() {
		return this.parent;
	}

	public Iterator<DataNode<T>> iteratorChilds() {
		return childs.iterator();
	}

	public boolean addChild(DataNode<T> child) {
		return child.setParent(this);
	}

	public DataNode<T> getChildById(long id) {
		if (this.childs.size() == 0) {
			return null;
		}
		Integer index = this.idChildIndexMap.get(id);
		return this.childs.get(index);
	}

	public DataNode<T> getGenerationsChildById(long id) {
		DataNode<T> child = getChildById(id);
		if (child != null) {
			return child;
		}
		for (DataNode<T> c : this.childs) {
			child = c.getGenerationsChildById(id);
			if (child != null) {
				return child;
			}
		}
		return null;
	}
}
