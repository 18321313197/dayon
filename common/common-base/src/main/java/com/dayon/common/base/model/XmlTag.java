package com.dayon.common.base.model;

import java.io.Serializable;
import java.util.Set;

import com.dayon.common.base.model.util.JsonUtil;
import com.dayon.common.base.model.util.XmlUtil;

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

	public Set<String> getAttributeKeys() {
		if (attributes == null) {
			return null;
		}
		return this.attributes.keySet();
	}

	public String getAttribute(String key) {
		if (attributes == null) {
			return null;
		}
		return this.attributes.getString(key);
	}

	public XmlTag setAttribute(String key, String value) {
		if (key == null || value == null) {
			return null;
		}
		if (attributes == null) {
			attributes = new DataMap();
		}
		this.attributes.put(key, value);
		return this;
	}

	public XmlTag deleteAttribute(String key) {
		if (attributes == null) {
			return this;
		}
		this.attributes.remove(key);
		return this;
	}

	public void clearAttribute() {
		if (attributes == null) {
			return;
		}
		this.attributes = null;
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
		return JsonUtil.toJson(this);
	}

	public String toXml() {
		return XmlUtil.toXml(this);
	}

}
