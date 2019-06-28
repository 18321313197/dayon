package com.dayon.common.util;

import java.util.function.BiFunction;

public class Pool<T extends Session> {
	private final ThreadLocal<T> threadLocal = new ThreadLocal<>();
	private final DataQueue<T> dataQueue = new DataQueue<>();
	private BiFunction<DataQueue<T>, ThreadLocal<T>, T> biFunction;

	public Pool(BiFunction<DataQueue<T>, ThreadLocal<T>, T> biFunction, int len) {
		this.biFunction = biFunction;
		
		for(int i=0;i<len;i++) {
			T session=biFunction.apply(dataQueue, threadLocal);
			dataQueue.input(session);
		}

	}

	public T getSession() {
		try {
			T session = this.threadLocal.get();
			if (session == null) {
				session = this.dataQueue.output();
				if (!session.isAvailable()) {
					session = biFunction.apply(dataQueue, threadLocal);
				}
				this.threadLocal.set(session);
			}
			return session;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
