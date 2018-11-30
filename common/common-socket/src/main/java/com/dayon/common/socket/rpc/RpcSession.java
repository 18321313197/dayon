package com.dayon.common.socket.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class RpcSession {
	private String ip;
	private int port;
	private Socket socket;
	private ObjectOutputStream os;
	private ObjectInputStream is;

	public RpcSession(String ip, int port) throws Exception {
		this.socket = new Socket(ip, port);
		this.ip = ip;
		this.port = port;
		this.os = new ObjectOutputStream(socket.getOutputStream());
		this.is = new ObjectInputStream(socket.getInputStream());
	}

	public synchronized Object getResult(Method method, Object... params) throws Exception {
		RpcParam rpcParam = new RpcParam();
		rpcParam.setMethodStr(method.toString());
		rpcParam.setParams(params);
		this.os.writeObject(rpcParam);
		Object result = this.is.readObject();
		return result;
	}

	public synchronized void close() {
		try {

			this.socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return this.ip + ":" + this.port;
	}

}
