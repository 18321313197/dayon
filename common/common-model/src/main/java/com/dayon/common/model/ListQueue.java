package com.dayon.common.model;

import java.util.LinkedList;
import java.util.List;

public class ListQueue<T> {
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
