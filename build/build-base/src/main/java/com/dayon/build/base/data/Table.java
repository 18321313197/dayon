package com.dayon.build.base.data;

import java.util.LinkedList;
import java.util.List;

public class Table {
	private String name;
	private List<Column> columns = new LinkedList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public static String tableMameToEntityName(String tableMame) {
		if (tableMame.startsWith("t_")) {
			tableMame = tableMame.substring(2, tableMame.length());
		}
		StringBuilder sb = new StringBuilder();
		boolean bool = true;
		for (char c : tableMame.toCharArray()) {
			if (c == '-' || c == '_') {
				bool = true;
				continue;
			}
			if (bool) {
				if (c >= 'a' && c <= 'z') {
					c -= 32;
				}
				bool = false;
			}
			sb.append(c);
		}
		return sb.toString();
	}
}
