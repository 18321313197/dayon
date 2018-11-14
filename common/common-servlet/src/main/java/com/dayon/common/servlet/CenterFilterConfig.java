package com.dayon.common.servlet;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.dayon.common.base.model.Node;
import com.dayon.common.base.model.XmlNode;
import com.dayon.common.base.model.XmlTag;
import com.dayon.common.other.ConfigUtil;
import com.dayon.common.other.IOFileUtil;

public class CenterFilterConfig {
	private String tail = "";
	private Map<String, String> webPackages = null;
	private String interceptClassName = null;
	private Map<String, String> interceptParams = null;

	public CenterFilterConfig(String centerConfigPath) throws Exception {

		String xmlText = new String(IOFileUtil.readFile(new File(centerConfigPath)));
		XmlNode root = ConfigUtil.loadXml(xmlText);
		if (root.getChilds().size() == 0) {
			return;
		}
		Node<XmlTag> n=root.getChilds().get(0);
		XmlTag x = n.getData();
		if (x.getName().equals("web")) {
			String tail = x.getAttribute("tail");
			if (tail != null) {
				this.tail = tail;
			}

			for (Node<XmlTag> node : n.getChilds()) {
				XmlTag xmlTag=node.getData();
				if (this.webPackages == null && node.getName().equals("packages")) {
					this.webPackages = new HashMap<String, String>();
					for (Node<XmlTag> nodepackage : node.getChilds()) {
						XmlTag nodepackageTag=nodepackage.getData();
						if (nodepackageTag.getName().equals("package")) {
							String id = nodepackageTag.getAttribute("id");
							String packageStr = nodepackageTag.getAttribute("value");
							if (id != null && packageStr != null) {
								this.webPackages.put(id, packageStr);
							}
						}
					}
				}

				if (this.interceptClassName == null && xmlTag.getName().equals("intercept")) {
					this.interceptClassName = xmlTag.getAttribute("class");
					if (this.interceptClassName != null) {
						this.interceptParams = new HashMap<String, String>();
						for (Node<XmlTag> nodeparam : node.getChilds()) {
							XmlTag nodeparamTag=nodeparam.getData();
							if (nodeparamTag.getName().equals("init-param")) {
								String key = nodeparamTag.getAttribute("key");
								String value = nodeparamTag.getAttribute("value");
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
