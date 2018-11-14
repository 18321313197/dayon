package com.dayon.common.base.model.util;

import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import com.dayon.common.base.model.DataList;
import com.dayon.common.base.model.DataMap;
import com.dayon.common.base.model.DataSet;
import com.dayon.common.base.model.Node;
import com.dayon.common.base.model.XmlTag;

import java.util.Set;

public class JsonUtil {
	private JsonUtil() {
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
		if (keys != null && keys.size()>0) {
			sb.append("{");
			for (String key : keys) {
				sb.append("\"").append(key).append("\":\"").append(xmlTag.getAttribute(key)).append("\",");
			}
			sb.setCharAt(sb.length()-1, '}');
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

	public static String toJson(Node<?> node) {
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
}
