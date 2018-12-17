package com.dayon.common.server.manager;

public class Main {
	public static void main(String[] args) throws Exception {
		new WorkServer("127.0.0.1:2181", "usercenter").start();
		new WorkServer("127.0.0.1:2181", "usercenter").start();
		new WorkServer("127.0.0.1:2181", "authcenter").start();
		new WorkServer("127.0.0.1:2181", "authcenter").start();
		new WorkServer("127.0.0.1:2181", "usercenter").start();
		new WorkServer("127.0.0.1:2181", "manageweb").start();
		Thread.sleep(Long.MAX_VALUE);
	}

}
