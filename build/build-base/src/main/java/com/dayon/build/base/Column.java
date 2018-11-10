package com.dayon.build.base;

public class Column {
	private String name;
	private String comment;
	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static String columnMameToJavaName(String columnName) {
		if (columnName.startsWith("c_")) {
			columnName = columnName.substring(2, columnName.length());
		}
		StringBuilder sb = new StringBuilder();
		boolean bool = true;
		for (char c : columnName.toCharArray()) {
			if (c == '-' || c == '_') {
				bool = true;
				continue;
			}
			if (bool) {
				if (sb.length() == 0) {
					if (c >= 'A' && c <= 'Z') {
						c += 32;
					}
				} else {
					if (c >= 'a' && c <= 'z') {
						c -= 32;
					}
				}
				bool = false;
			}
			sb.append(c);
		}
		return sb.toString();
	}

}
