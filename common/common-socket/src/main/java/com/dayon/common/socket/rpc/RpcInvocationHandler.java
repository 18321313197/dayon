package com.dayon.common.socket.rpc;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

import com.dayon.common.base.model.Queue;

public class RpcInvocationHandler implements InvocationHandler {
	private Queue<RpcSocketSession> queue = new Queue<>();

	public RpcInvocationHandler(String host, int port, int sessionSize) throws IOException {
		if (sessionSize <= 0) {
			sessionSize = 1;
		}
		for (int i = 0; i < sessionSize; i++) {
			RpcSocketSession socketSession = new RpcSocketSession(new Socket(host, port));
			socketSession.start();
			queue.input(socketSession);
		}
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		RpcSocketSession ss = queue.output();
		Object obj = ss.getResult(method, args);
		queue.input(ss);
		return obj;
	}

}
