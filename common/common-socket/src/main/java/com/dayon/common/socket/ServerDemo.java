package com.dayon.common.socket;

import java.net.ServerSocket;

public class ServerDemo {
	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(8989);
		try {
			while (true) {
				new SocketSession(ss.accept()) {
					@Override
					protected Object onRecv(String ip, int port, Object requestObj) {
						return System.currentTimeMillis()+"";
					}
				}.start();
			}
		} finally {
			ss.close();
			System.out.println("server socket accept thread storp");
		}
	}

}
