package com.dayon.common.socket;

import java.util.ArrayList;
import java.util.List;

public class ClentDemo {
	private static List<Long> list=new ArrayList<>();
	public static void main(String[] args) throws Exception {
		Long a = System.currentTimeMillis();
		list.add(a);
		
		synchronized (a) {
			new Thread() {
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Long b = list.get(0);
					synchronized (b) {
						b.notify();
						System.out.println("解锁完毕");
					}

				};
			}.start();
			a.wait();
		}
		System.out.println("结束");
	}

}
