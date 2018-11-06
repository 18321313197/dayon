package com.dayon.common.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlNode {
	private String nodeName;
	private final Map<String, String> attributes = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		public String toString() {
			StringBuilder sb = new StringBuilder("{");
			if (this.size() > 0) {
				for (String key : this.keySet()) {
					if (key != null) {
						sb.append("\"" + key + "\":")
								.append(this.get(key) == null ? "\"\"" : "\"" + this.get(key) + "\",");
					}
				}
				sb.deleteCharAt(sb.length() - 1);
			}
			sb.append("}");
			return sb.toString();
		};
	};
	private List<XmlNode> childs = new ArrayList<XmlNode>();
	private String nodeText;

	
	public XmlNode setNodeName(String nodeName) {
		this.nodeName = nodeName;
		return this;
	}

	public String getNodeName() {
		return this.nodeName;
	}

	public Map<String, String> getAttributes() {
		return this.attributes;
	}

	public String getAttribute(String key) {
		return this.attributes.get(key);
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
		StringBuilder sb = new StringBuilder("");
		builder(sb, this);
		return sb.toString();
	}

	private static void builder(StringBuilder sb, XmlNode xmlNode) {

		sb.append("<").append(xmlNode.getNodeName());
		for (String item : xmlNode.attributes.keySet()) {
			String value = xmlNode.attributes.get(item);
			sb.append(" ").append(item).append("=\"").append(value).append("\"");
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
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"name\":").append(nodeName == null ? "\"\"" : "\"" + nodeName + "\"");
		sb.append(",\"attributes\":").append(attributes);
		sb.append(",\"nodeText\":").append(nodeText == null ? "\"\"" : "\"" + nodeText + "\"");
		sb.append(",\"context\":").append(childs);
		sb.append("}");
		return sb.toString();
	}
}
