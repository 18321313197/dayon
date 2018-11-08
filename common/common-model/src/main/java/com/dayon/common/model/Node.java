package com.dayon.common.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node<T> implements Serializable {
	private static final long serialVersionUID = 5866053491330330833L;
	private long id;
	private String name;
	private Node<T> parent;
	private T data;
	private List<Node<T>> childs = new LinkedList<>();
	private Map<Long, Node<T>> idchildMap = new HashMap<>();

	public Node() {
		this.id = this.hashCode();
	}

	public Node(long id) {
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

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public Node<T> getParent() {
		return this.parent;
	}

	public List<Node<T>> getChilds() {
		return childs;
	}

	public boolean addChild(Node<T> child) {
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

	public Node<T> getChildById(long id) {
		return getChildById(this, id);
	}

	public Node<T> getGenerationsChildById(long id) {

		return getGenerationsChildById(this, id);
	}

	private static <T> Node<T> getGenerationsChildById(Node<T> node, long id) {
		Node<T> child = getChildById(node, id);
		if (child != null) {
			return child;
		}
		for (Node<T> c : node.childs) {
			child = getGenerationsChildById(c, id);
			if (child != null) {
				return child;
			}
		}
		return null;
	}

	private static <T> Node<T> getChildById(Node<T> node, long id) {
		if (node.childs.size() == 0) {
			return null;
		}
		return node.idchildMap.get(id);
	}

	public static <T> void settingNodeParent(Node<T> child, Node<T> parent) {
		if (child.id == parent.id) {
			return;
		}
		Node<T> ypt = child.getParent();
		ypt.childs.remove(child);
		ypt.idchildMap.remove(child.id);
		parent.childs.add(child);
		parent.idchildMap.put(child.getId(), child);
	}

}
