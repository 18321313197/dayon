package com.dayon.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParamUtil {
	private ParamUtil() {

	}

	public static Long parseLong(String string) {
		if (string == null || string.isEmpty()) {
			return null;
		}
		try {
			return Long.parseLong(string);
		} catch (Exception e) {
			return null;
		}
	}

	public static Integer parseInt(String string) {
		if (string == null || string.isEmpty()) {
			return null;
		}
		try {
			return Integer.parseInt(string);
		} catch (Exception e) {
			return null;
		}
	}

	public static Double parseDouble(String string) {
		if (string == null || string.isEmpty()) {
			return null;
		}
		try {
			return Double.parseDouble(string);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date parseDate(SimpleDateFormat format, String string) {
		if (string == null || string.isEmpty() || format == null) {
			return null;
		}
		try {
			return format.parse(string);
		} catch (Exception e) {
			return null;
		}
	}

	public static Boolean parseBoolean(String string) {
		if (string == null || string.isEmpty()) {
			return null;
		}
		try {
			return Boolean.parseBoolean(string);
		} catch (Exception e) {
			return null;
		}
	}

	public static List<Date> parseDates(SimpleDateFormat format, String... strings) {
		List<Date> dates = new ArrayList<>();
		if (strings != null) {
			for (String string : strings) {
				Date date = parseDate(format, string);
				if (date != null) {
					dates.add(date);
				}
			}
		}
		return dates;
	}

	public static List<Long> parseLongs(String... strings) {
		List<Long> longs = new ArrayList<>();
		if (strings != null) {
			for (String string : strings) {
				Long l = parseLong(string);
				if (l != null) {
					longs.add(l);
				}
			}
		}
		return longs;
	}

	public static List<Integer> parseInts(String... strings) {
		List<Integer> ints = new ArrayList<>();
		if (strings != null) {
			for (String string : strings) {
				Integer i = parseInt(string);
				if (i != null) {
					ints.add(i);
				}
			}
		}
		return ints;
	}
}
