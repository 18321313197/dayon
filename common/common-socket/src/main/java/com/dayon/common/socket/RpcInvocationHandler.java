package com.dayon.common.socket;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

import com.dayon.common.model.ListQueue;

public class RpcInvocationHandler implements InvocationHandler {
	private ListQueue<RpcSocketSession> listQueue = new ListQueue<>();

	public RpcInvocationHandler(String host, int port, int sessionSize) throws IOException {
		if (sessionSize <= 0) {
			sessionSize = 1;
		}
		for (int i = 0; i < sessionSize; i++) {
			RpcSocketSession socketSession = new RpcSocketSession(new Socket(host, port));
			socketSession.start();
			listQueue.input(socketSession);
		}
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		RpcSocketSession ss = listQueue.output();
		Object obj = ss.getResult(method, args);
		listQueue.input(ss);
		return obj;
	}

}
