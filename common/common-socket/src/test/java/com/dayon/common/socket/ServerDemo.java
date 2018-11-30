package com.dayon.common.socket;

import java.net.ServerSocket;

public class ServerDemo {
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(8989);
		try {
			while (true) {
				new SocketSession(serverSocket.accept()) {
					@Override
					protected Object onRecv(String ip, int port, Object requestObj) {
						return System.currentTimeMillis()+"";
					}
				};
			}
		} finally {
			serverSocket.close();
			System.out.println("服务器已停止工作");
		}
	}

}
