package com.dayon.common.socket;

public class ClentDemo {
	
	public static void main(String[] args) throws Exception {
		
		new SocketSessionPool("106.75.223.137",61010,1) {
			
			@Override
			protected Object onRecv(String ip, int port, Object requestObj) {
				System.out.println(requestObj);
				return null;
			}
		};
	}

}
