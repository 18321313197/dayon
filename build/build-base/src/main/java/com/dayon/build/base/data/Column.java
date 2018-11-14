package com.dayon.build.base.data;

public class Column {
	private String name;
	private String comment;
	private String type;
	private Boolean isPrimary;
	private Boolean isUnique;
	private Boolean isNullable;

	public Boolean getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public Boolean getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(Boolean isNullable) {
		this.isNullable = isNullable;
	}

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

	public Boolean getIsUnique() {
		return isUnique;
	}

	public void setIsUnique(Boolean isUnique) {
		this.isUnique = isUnique;
	}

}
