package com.dayon.common.base.model;

import com.dayon.common.base.model.util.JsonUtil;

public class XmlNode extends Node<XmlTag> {
	private static final long serialVersionUID = 300707695112068649L;
	
	public String toXML() {
		return JsonUtil.toJson(this);
	}

}
