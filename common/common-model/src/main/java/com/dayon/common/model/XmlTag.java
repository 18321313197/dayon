package com.dayon.common.model;

import java.io.Serializable;
import java.util.Map.Entry;
import java.util.Set;

public class XmlTag implements Serializable {
	private static final long serialVersionUID = 300707695112068649L;
	private String name;
	private DataMap attributes;
	private String text;

	public XmlTag setName(String name) {
		this.name = name;
		return this;
	}

	public String getName() {
		return this.name;
	}

	private DataMap getAttributes() {
		return this.attributes;
	}

	public String getAttribute(String key) {
		return this.attributes.getString(key);
	}

	public XmlTag setAttribute(String key, String value) {
		this.attributes.put(key, value);
		return this;
	}

	public XmlTag deleteAttribute(String key) {
		this.attributes.remove(key);
		return this;
	}

	public void clearAttribute() {
		this.attributes.clear();
	}

	public String getText() {
		return this.text;
	}

	public XmlTag setText(String text) {
		this.text = text;
		return this;
	}

	@Override
	public String toString() {
		return toJson(this);
	}

	public String toXml() {
		return toXml(this);
	}

	public static String toXml(XmlTag xmlTag) {
		if (xmlTag == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<").append(xmlTag.getName());
		Set<Entry<String, Object>> eset = xmlTag.attributes.entrySet();
		for (Entry<String, Object> e : eset) {
			sb.append(" ").append(e.getKey()).append("=\"").append(e.getValue()).append("\"");
		}

		if (xmlTag.getText() == null || xmlTag.getText().isEmpty()) {
			sb.append(" />");
			return sb.toString();
		} else {
			sb.append(xmlTag.getText()).append("</").append(xmlTag.getName()).append(">");
		}
		return sb.toString();
	}

	private static String toJson(XmlTag xmlTag) {
		if (xmlTag == null) {
			return null;
		}
		String name = xmlTag.getName();
		String text = xmlTag.getText();
		StringBuilder sb = new StringBuilder("{\"name\":");
		if (name == null) {
			sb.append("\"\",\"attributes\":");
		} else {
			sb.append("\"").append(name).append("\",\"attributes\":");
		}
		sb.append(xmlTag.getAttributes()).append(",\"text\":");
		if (text == null) {
			sb.append("\"\"");
		} else {
			sb.append("\"").append(text).append("\"");
		}
		sb.append("}");
		return sb.toString();
	}

}
