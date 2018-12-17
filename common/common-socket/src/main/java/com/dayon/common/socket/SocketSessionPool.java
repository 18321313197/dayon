package com.dayon.common.socket;

import java.net.Socket;

import com.dayon.common.base.dto.model.DataQueue;

public abstract class SocketSessionPool {
	private final ThreadLocal<SocketSession> threadLocal = new ThreadLocal<>();
	private final DataQueue<SocketSession> socketSessioDataQueue = new DataQueue<>();

	public SocketSessionPool(String ip, int port, int count) throws Exception {
		count = count <= 0 ? 1 : count;
		for (int i = 0; i < count; i++) {
			Socket socket=new Socket(ip,port);
			this.socketSessioDataQueue.input(new SocketSession(socket) {
				@Override
				protected Object onRecv(String ip, int port, Object requestObj) {
					return SocketSessionPool.this.onRecv(ip, port, requestObj);
				}

				@Override
				public synchronized void close() {
					SocketSessionPool.this.threadLocal.remove();
					SocketSessionPool.this.socketSessioDataQueue.input(this);
				}
			});
		}
	}

	protected abstract Object onRecv(String ip, int port, Object requestObj);

	public SocketSession getSession() {
		try {
			SocketSession socketSession = this.threadLocal.get();
			if (socketSession == null) {
				socketSession = this.socketSessioDataQueue.output();
				this.threadLocal.set(socketSession);
			}
			return socketSession;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
