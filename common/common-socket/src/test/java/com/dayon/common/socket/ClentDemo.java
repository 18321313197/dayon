package com.dayon.common.socket;

public class ClentDemo {
	
	public static void main(String[] args) throws Exception {
		Integer a = 12345;
		
		for(int i=0;i<10;i++) {
			
			new Thread() {
				public void run() {
					synchronized (a) {
						System.out.println(1);
						try {
							a.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}.start();
			
			
			
		}
		
		
		
		System.out.println("结束");
	}

}
