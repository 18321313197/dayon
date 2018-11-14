package com.dayon.common.other;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.dayon.common.base.model.XmlNode;
import com.dayon.common.base.model.XmlTag;

public class ConfigUtil {

	public static XmlNode loadXml(String xmlText) throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

		XmlNode xmlNode = new XmlNode();
		xmlNode.setData(new XmlTag().setName("root"));
		DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
		Document doc = db.parse(new InputSource(new StringReader(xmlText)));
		ConfigUtil.loadNodeList(xmlNode, doc.getChildNodes());
		return xmlNode;
	}

	private static void loadNodeList(XmlNode node, NodeList nodes) {
		for (int i = 0; i < nodes.getLength(); i++) {
			if (nodes.item(i).getNodeType() == 1) {
				XmlNode xmlNode = new XmlNode();
				node.getChilds().add(xmlNode);
				xmlNode.setData(new XmlTag().setName(nodes.item(i).getNodeName()));
				NamedNodeMap namedNodeMap = nodes.item(i).getAttributes();
				for (int j = 0; j < namedNodeMap.getLength(); j++) {
					xmlNode.getData().setAttribute(namedNodeMap.item(j).getNodeName(),
							namedNodeMap.item(j).getNodeValue());
				}
				NodeList childnodes = nodes.item(i).getChildNodes();
				if (childnodes.getLength() > 0) {
					ConfigUtil.loadNodeList(xmlNode, childnodes);
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

	public static Properties loadClassPathProperties(String path) {
		path = path.trim();
		if (path == null || path.length() == 0) {
			return null;
		}
		InputStream is = ConfigUtil.class.getResourceAsStream(path);
		if (is == null) {
			return null;
		}
		try {
			Properties cofig = new Properties();
			cofig.load(is);
			return cofig;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
