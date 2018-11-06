package com.dayon.common.model;

import java.util.HashSet;
import java.util.Set;

public class Node<T> {
	private Node<T> parent;
	private T data;
	private Set<Node<T>> childs = new HashSet<>();

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Set<Node<T>> getChilds() {
		return childs;
	}

}
