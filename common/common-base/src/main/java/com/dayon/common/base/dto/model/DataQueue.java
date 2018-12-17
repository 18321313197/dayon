package com.dayon.common.base.dto.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class DataQueue<T> implements Serializable {
	private static final long serialVersionUID = -8532317032113405503L;
	private List<T> list = new LinkedList<>();

	public synchronized void input(T arg) {
		if (list.isEmpty()) {
			notify();
		}
		list.add(arg);
	}

	public synchronized T output() throws Exception {

		if (list.isEmpty()) {
			wait();
		}
		return list.remove(0);

	}
}
