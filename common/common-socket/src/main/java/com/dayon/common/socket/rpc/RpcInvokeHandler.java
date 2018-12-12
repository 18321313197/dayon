package com.dayon.common.socket.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RpcInvokeHandler implements InvocationHandler {
	private RpcSessionPool rpcSessionPool;

	public RpcInvokeHandler(String ip, int port) throws Exception {
		this.rpcSessionPool = new RpcSessionPool(ip, port, 1);
	}

	public RpcInvokeHandler(String ip, int port, int count) throws Exception {
		this.rpcSessionPool = new RpcSessionPool(ip, port, count);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
		RpcSession rpcSession = rpcSessionPool.getSession();
		Object obj = rpcSession.getResult(method, params);
		rpcSession.close();
		return obj;
	}

}
