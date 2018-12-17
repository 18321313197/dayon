package com.dayon.common.util;

import java.io.InputStream;
import java.io.StringReader;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.dayon.common.base.dto.model.DataList;
import com.dayon.common.base.dto.model.DataMap;
import com.dayon.common.base.dto.model.DataNode;
import com.dayon.common.base.dto.model.DataSet;
import com.dayon.common.base.dto.model.XmlTag;

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
		sb.append(",\"childs\":").append(node.iteratorChilds());
		sb.append("}");
		return sb.toString();
	}

	public static String toJson(Collection<DataMap> collectionMap) {
		if (collectionMap == null) {
			return null;
		}
		if (collectionMap.size() == 0) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder("[");
		for (DataMap map : collectionMap) {
			sb.append(toJson(map)).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}

	public static DataMap toDataMap(String json) {

		return null;
	}

	public static DataMap toDataList(String json) {

		return null;
	}

	public static String toJson(DataMap map) {
		if (map == null) {
			return null;
		}
		if (map.getSize() == 0) {
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
		if (xmlTag.getText() == null) {
			sb.append(" />");
			return sb.toString();
		} else {
			sb.append(" >").append(xmlTag.getText()).append("</").append(xmlTag.getName()).append(">");
		}
		return sb.toString();
	}

	public static String toXml(DataNode<XmlTag> xmlNode) {
		StringBuilder sb = new StringBuilder();
		builder(sb, xmlNode.getData(), xmlNode.iteratorChilds());
		return sb.toString();

	}

	private static void builder(StringBuilder sb, XmlTag xmlTag, Iterator<DataNode<XmlTag>> cXmlTags) {
		if (xmlTag == null) {
			return;
		}
		sb.append(toXml(xmlTag));
		if (cXmlTags == null || !cXmlTags.hasNext()) {
			return;
		}
		if (xmlTag.getText() == null) {
			sb.delete(sb.length() - 2, sb.length() - 1);
		} else {
			sb.delete(sb.length() - xmlTag.getName().length() - 3, sb.length());
		}

		while (cXmlTags.hasNext()) {
			DataNode<XmlTag> ns = cXmlTags.next();
			builder(sb, ns.getData(), ns.iteratorChilds());
		}
		sb.append("</").append(xmlTag.getName()).append(">");
	}

	public static DataNode<XmlTag> loadXml(String xmlText) throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DataNode<XmlTag> xmlNode = new DataNode<>();
		xmlNode.setData(new XmlTag().setName("root"));
		DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
		StringReader reader = new StringReader(xmlText);
		Document doc = db.parse(new InputSource(reader));
		loadNodeList(xmlNode, doc.getChildNodes());
		reader.close();
		return xmlNode;
	}

	public static DataNode<XmlTag> loadXml(InputStream is) throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DataNode<XmlTag> xmlNode = new DataNode<>();
		xmlNode.setData(new XmlTag().setName("root"));
		DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
		Document doc = db.parse(is);
		loadNodeList(xmlNode, doc.getChildNodes());
		return xmlNode;
	}

	private static void loadNodeList(DataNode<XmlTag> node, NodeList nodes) {
		for (int i = 0; i < nodes.getLength(); i++) {
			if (nodes.item(i).getNodeType() == 1) {
				DataNode<XmlTag> xmlNode = new DataNode<>();
				node.addChild(xmlNode);
				xmlNode.setData(new XmlTag().setName(nodes.item(i).getNodeName()));
				NamedNodeMap namedNodeMap = nodes.item(i).getAttributes();
				for (int j = 0; j < namedNodeMap.getLength(); j++) {
					xmlNode.getData().setAttribute(namedNodeMap.item(j).getNodeName(),
							namedNodeMap.item(j).getNodeValue());
				}
				NodeList childnodes = nodes.item(i).getChildNodes();
				if (childnodes.getLength() > 0) {
					loadNodeList(xmlNode, childnodes);
				}
			} else if (nodes.item(i).getNodeType() == 3) {
				if (node.getData().getText() == null) {
					node.getData().setText(nodes.item(i).getTextContent());
				} else {
					node.getData().setText(node.getData().getText() + " " + nodes.item(i).getTextContent());
				}
			}
		}
	}
}
