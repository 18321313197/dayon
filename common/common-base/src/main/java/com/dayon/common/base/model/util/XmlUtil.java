package com.dayon.common.base.model.util;

import java.util.List;
import java.util.Set;

import com.dayon.common.base.model.Node;
import com.dayon.common.base.model.XmlNode;
import com.dayon.common.base.model.XmlTag;

public class XmlUtil {
	private XmlUtil() {

	}

	public static String toXml(XmlTag xmlTag) {
		if (xmlTag == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("<").append(xmlTag.getName());
		Set<String> keys = xmlTag.getAttributeKeys();
		if (keys != null) {
			for (String key : keys) {
				sb.append(" ").append(key).append("=\"").append(xmlTag.getAttribute(key)).append("\"");
			}
		}
		if (xmlTag.getText() == null || xmlTag.getText().isEmpty()) {
			sb.append(" />");
			return sb.toString();
		} else {
			sb.append(xmlTag.getText()).append("</").append(xmlTag.getName()).append(">");
		}
		return sb.toString();
	}
	
	public static String toXml(XmlNode xmlNode){
		StringBuilder sb = new StringBuilder();
		builder(sb, xmlNode.getData(), xmlNode.getChilds());
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
}
