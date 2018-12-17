package com.dayon.common.util;

import java.util.Random;

public class RandomUtil {
	private RandomUtil() {

	}

	private static char[] chars = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890".toCharArray();

	public static long randomLong(boolean isPositive) {
		if (isPositive) {
			return (long) (Math.random() * Long.MAX_VALUE + 1);
		} else {
			return (long) (Math.random() * Long.MIN_VALUE - 1);
		}
	}

	public static String randomString(int length) {
		if (length <= 0) {
			return "";
		}
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			// 产生0-61的数字
			int number = random.nextInt(chars.length);
			// 将产生的数字通过length次承载到sb中
			sb.append(chars[number]);
		}
		return sb.toString();
	}
}
