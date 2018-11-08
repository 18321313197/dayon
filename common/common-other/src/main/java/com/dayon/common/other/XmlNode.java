package com.dayon.common.other;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.dayon.common.model.DataMap;

public class XmlNode {
	private String nodeName;
	private final DataMap attributes = new DataMap() ;
	private List<XmlNode> childs = new LinkedList<XmlNode>();
	private String nodeText;

	
	public XmlNode setNodeName(String nodeName) {
		this.nodeName = nodeName;
		return this;
	}

	public String getNodeName() {
		return this.nodeName;
	}

	private DataMap getAttributes() {
		return this.attributes;
	}

	public String getAttribute(String key) {
		return this.attributes.getString(key);
	}

	public XmlNode setAttribute(String key, String value) {
		this.attributes.put(key, value);
		return this;
	}

	public XmlNode deleteAttribute(String key) {
		this.attributes.remove(key);
		return this;
	}

	public void clearAttribute() {
		this.attributes.clear();
	}

	public List<XmlNode> getChilds() {
		return childs;
	}

	public XmlNode addChild(XmlNode child) {
		this.childs.add(child);
		return this;
	}

	public XmlNode deleteChild(int index) {
		this.childs.remove(index);
		return this;
	}

	public XmlNode clearChilds() {
		this.childs.clear();
		return this;
	}

	public String getNodeText() {
		return nodeText;
	}

	public XmlNode setNodeText(String nodeText) {
		this.nodeText = nodeText;
		return this;
	}

	public String toXML() {
		StringBuilder sb = new StringBuilder();
		builder(sb, this);
		return sb.toString();
	}

	private static void builder(StringBuilder sb, XmlNode xmlNode) {
		if (xmlNode == null) {
			return;
		}
		sb.append("<").append(xmlNode.getNodeName());
		Set<Entry<String, Object>> eset= xmlNode.attributes.entrySet();
		for (Entry<String, Object> e : eset) {
			sb.append(" ").append(e.getKey()).append("=\"").append(e.getValue()).append("\"");
		}
		
		if (xmlNode.childs.isEmpty() && xmlNode.getNodeText() == null) {
			sb.append(" />");
			return;
		}
		sb.append(">");
		if (xmlNode.childs.isEmpty()) {
			sb.append(xmlNode.getNodeText());
		} else {
			for (XmlNode node : xmlNode.childs) {
				builder(sb, node);
			}
		}
		sb.append("</").append(xmlNode.getNodeName()).append(">");
	}

	@Override
	public String toString() {
		return toJson(this);
	}
	
	
	private static String toJson(XmlNode xmlNode) {
		if (xmlNode == null) {
			return null;
		}
		String nodeName=xmlNode.getNodeName();
		String nodeText=xmlNode.getNodeText();
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"name\":").append(nodeName == null ? "\"\"" : "\"" + nodeName + "\"");
		sb.append(",\"attributes\":").append(xmlNode.getAttributes());
		sb.append(",\"nodeText\":").append(nodeText == null ? "\"\"" : "\"" + nodeText + "\"");
		sb.append(",\"context\":").append(xmlNode.getChilds());
		sb.append("}");
		return sb.toString();
	}
}
