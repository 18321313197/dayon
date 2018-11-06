package com.dayon.common.servlet;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.dayon.common.other.ConfigUtil;
import com.dayon.common.other.IOUtil;
import com.dayon.common.other.XmlNode;

public class CenterFilterConfig {
	private String tail = "";
	private Map<String, String> webPackages = null;
	private String interceptClassName = null;
	private Map<String, String> interceptParams = null;

	public CenterFilterConfig(String centerConfigPath) throws Exception {

		String xmlText = new String(IOUtil.readFile(new File(centerConfigPath)));
		XmlNode root = ConfigUtil.loadXml(xmlText);
		if (root.getChilds().size() == 0) {
			return;
		}
		XmlNode xmlnode = root.getChilds().get(0);
		if (xmlnode.getNodeName().equals("web")) {
			String tail = xmlnode.getAttribute("tail");
			if (tail != null) {
				this.tail = tail;
			}

			for (XmlNode node : xmlnode.getChilds()) {
				if (this.webPackages == null && node.getNodeName().equals("packages")) {
					this.webPackages = new HashMap<String, String>();
					for (XmlNode nodepackage : node.getChilds()) {
						if (nodepackage.getNodeName().equals("package")) {
							String id = nodepackage.getAttribute("id");
							String packageStr = nodepackage.getAttribute("value");
							if (id != null && packageStr != null) {
								this.webPackages.put(id, packageStr);
							}
						}
					}
				}

				if (this.interceptClassName == null && node.getNodeName().equals("intercept")) {
					this.interceptClassName = node.getAttribute("class");
					if (this.interceptClassName != null) {
						this.interceptParams = new HashMap<String, String>();
						for (XmlNode nodeparam : node.getChilds()) {
							if (nodeparam.getNodeName().equals("init-param")) {
								String key = nodeparam.getAttribute("key");
								String value = nodeparam.getAttribute("value");
								if (key != null && value != null) {
									this.interceptParams.put(key, value);
								}
							}
						}
					}
				}

			}

		}
	}

	public Map<String, String> getWebPackages() {
		return webPackages;
	}

	public void setWebPackages(Map<String, String> webPackages) {
		this.webPackages = webPackages;
	}

	public String getInterceptClassName() {
		return interceptClassName;
	}

	public void setInterceptClassName(String interceptClassName) {
		this.interceptClassName = interceptClassName;
	}

	public Map<String, String> getInterceptParams() {
		return interceptParams;
	}

	public void setInterceptParams(Map<String, String> interceptParams) {
		this.interceptParams = interceptParams;
	}

	public String getTail() {
		return this.tail;
	}

	public void setWebTail(String tail) {
		this.tail = tail;
	}

	@Override
	public String toString() {
		return "tail: " + tail + "\r\nwebpackages: " + webPackages + "\r\ninterceptClassName: " + interceptClassName
				+ "\r\ninterceptParams: " + interceptParams;
	}

}
