package com.dayon.common.base.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Queue<T> implements Serializable {
	private static final long serialVersionUID = -8532317032113405503L;
	private List<T> list = new LinkedList<>();

	public synchronized void input(T arg) {
		if (this.list.isEmpty()) {
			notify();
		}
		this.list.add(arg);
	}

	public synchronized T output() throws Exception {

		if (this.list.isEmpty()) {
			wait();
		}
		return this.list.remove(0);

	}
}
