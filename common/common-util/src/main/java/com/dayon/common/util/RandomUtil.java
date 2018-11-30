package com.dayon.common.util;

public class RandomUtil {
	private RandomUtil(){
		
	}
	public static long randomLong(boolean isPositive) {
		if (isPositive) {
			return (long) (Math.random() * Long.MAX_VALUE + 1);
		} else {
			return (long) (Math.random() * Long.MIN_VALUE - 1);
		}
	}

}
