package com.dayon.common.socket.rpc;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.Socket;

import com.dayon.common.socket.SocketSession;

public  class RpcSocketSession extends SocketSession {
	
	private RpcMsg rpcMsg =new RpcMsg();
	public RpcSocketSession(Socket s) throws IOException {
		super(s);
	}
	@Override
	protected Object onRecv(String ip, int port, Object requestObj) {
		rpcMsg.setResult(requestObj);
		synchronized (rpcMsg) {
			rpcMsg.notify();
		}
		return null;
	}
	public Object getResult(Method method,Object[] params) throws Exception{
			rpcMsg.setMethodStr(method.toString());
			rpcMsg.setParams(params);
			super.send(rpcMsg);
			synchronized (rpcMsg) {
				rpcMsg.wait();
			}
			return rpcMsg.getResult();
	}
}
