package com.dayon.common.model;

import java.util.List;

public class XmlNode extends Node<XmlTag> {
	private static final long serialVersionUID = 300707695112068649L;

	public String toXML() {
		StringBuilder sb = new StringBuilder();
		builder(sb, this.getData(), this.getChilds());
		return sb.toString();
	}

	private static void builder(StringBuilder sb, XmlTag xmlTag, List<Node<XmlTag>> cXmlTags) {
		if (xmlTag == null) {
			return;
		}
		sb.append(xmlTag.toXml());
		if (cXmlTags == null || cXmlTags.size() == 0) {
			return;
		}
		if (xmlTag.getText() == null || xmlTag.getText().trim().isEmpty()) {
			sb.delete(sb.length() - 2, sb.length() - 1);
		} else {
			sb.delete(sb.length() - xmlTag.getName().length() - 3, sb.length());
		}
		for (Node<XmlTag> ns : cXmlTags) {
			builder(sb, ns.getData(), ns.getChilds());
		}
		sb.append("</").append(xmlTag.getName()).append(">");

	}

	@Override
	public String toString() {
		return toJson(this);
	}

	private static String toJson(XmlNode xmlNode) {
		if (xmlNode == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("{\"data\":").append(xmlNode.getData());
		sb.append(",\"childs\":").append(xmlNode.getChilds());
		sb.append("}");
		return sb.toString();
	}

}
