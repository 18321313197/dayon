package com.dayon.common.util;

import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.dayon.common.base.model.DataList;
import com.dayon.common.base.model.DataMap;
import com.dayon.common.base.model.DataNode;
import com.dayon.common.base.model.DataSet;
import com.dayon.common.base.model.XmlNode;
import com.dayon.common.base.model.XmlTag;

public class DataUtil {
	private DataUtil() {
	}

	public static String toJson(XmlTag xmlTag) {
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
		Set<String> keys = xmlTag.getAttributeKeys();
		if (keys != null && keys.size() > 0) {
			sb.append("{");
			for (String key : keys) {
				sb.append("\"").append(key).append("\":\"").append(xmlTag.getAttribute(key)).append("\",");
			}
			sb.setCharAt(sb.length() - 1, '}');
		}
		sb.append("},\"text\":");
		if (text == null) {
			sb.append("\"\"");
		} else {
			sb.append("\"").append(text).append("\"");
		}
		sb.append("}");
		return sb.toString();
	}

	public static String toJson(DataNode<?> node) {
		if (node == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("{\"data\":").append(node.getData());
		sb.append(",\"childs\":").append(node.getChilds());
		sb.append("}");
		return sb.toString();
	}

	public static String toJson(Collection<Map<String, Object>> collectionMap) {
		if (collectionMap == null) {
			return null;
		}
		if (collectionMap.size() == 0) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder("[");
		for (Map<String, Object> map : collectionMap) {
			sb.append(toJson(map)).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}

	public static String toJson(Map<String, Object> map) {
		if (map == null) {
			return null;
		}
		if (map.size() == 0) {
			return "{}";
		}
		StringBuilder sb = new StringBuilder("{");
		Set<Entry<String, Object>> eset = map.entrySet();
		for (Entry<String, Object> e : eset) {
			Object obj = e.getValue();
			sb.append("\"").append(e.getKey()).append("\":");
			if (obj == null) {
				sb.append("null");
			} else if (obj instanceof Boolean || obj instanceof Byte || obj instanceof Short || obj instanceof Integer
					|| obj instanceof Long || obj instanceof Float || obj instanceof Double || obj instanceof DataMap
					|| obj instanceof DataList || obj instanceof DataSet) {
				sb.append(obj);
			} else if (obj instanceof Date) {
				DateFormat dateFormat = DateFormat.getDateTimeInstance();
				sb.append("\"").append(dateFormat.format(obj)).append("\"");
			} else {
				sb.append("\"").append(obj).append("\"");
			}
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("}");
		return sb.toString();
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

	public static String toXml(XmlNode xmlNode) {
		StringBuilder sb = new StringBuilder();
		builder(sb, xmlNode.getData(), xmlNode.getChilds());
		return sb.toString();

	}

	private static void builder(StringBuilder sb, XmlTag xmlTag, List<DataNode<XmlTag>> cXmlTags) {
		if (xmlTag == null) {
			return;
		}
		sb.append(toXml(xmlTag));
		if (cXmlTags == null || cXmlTags.size() == 0) {
			return;
		}
		if (xmlTag.getText() == null || xmlTag.getText().trim().isEmpty()) {
			sb.delete(sb.length() - 2, sb.length() - 1);
		} else {
			sb.delete(sb.length() - xmlTag.getName().length() - 3, sb.length());
		}
		for (DataNode<XmlTag> ns : cXmlTags) {
			builder(sb, ns.getData(), ns.getChilds());
		}
		sb.append("</").append(xmlTag.getName()).append(">");
	}
}
