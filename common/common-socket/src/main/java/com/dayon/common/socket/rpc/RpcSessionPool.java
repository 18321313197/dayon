package com.dayon.common.socket.rpc;

import com.dayon.common.base.dto.model.DataQueue;

public class RpcSessionPool {

	private final ThreadLocal<RpcSession> threadLocal = new ThreadLocal<>();
	private final DataQueue<RpcSession> rpcSessioDataQueue = new DataQueue<>();

	public RpcSessionPool(String ip, int port, int count) throws Exception {
		count = count <= 0 ? 1 : count;
		for (int i = 0; i < count; i++) {
			this.rpcSessioDataQueue.input(new RpcSession(ip, port) {
				@Override
				public synchronized void close() {
					RpcSessionPool.this.threadLocal.remove();
					RpcSessionPool.this.rpcSessioDataQueue.input(this);
				}
			});
		}
	}

	public RpcSession getSession() {
		try {
			RpcSession rpcSession = this.threadLocal.get();
			if (rpcSession == null) {
				rpcSession = this.rpcSessioDataQueue.output();
				this.threadLocal.set(rpcSession);
			}
			return rpcSession;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
