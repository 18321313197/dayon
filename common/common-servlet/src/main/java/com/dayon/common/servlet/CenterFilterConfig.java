package com.dayon.common.servlet;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.dayon.common.base.dto.model.DataNode;
import com.dayon.common.base.dto.model.XmlTag;
import com.dayon.common.util.DataUtil;
import com.dayon.common.util.FileUtil;

public class CenterFilterConfig {
	private String tail = "";
	private Map<String, String> webPackages = null;
	private String interceptClassName = null;
	private Map<String, String> interceptParams = null;

	public CenterFilterConfig(String centerConfigPath) throws Exception {

		String xmlText = new String(FileUtil.readFile(new File(centerConfigPath)));
		DataNode<XmlTag> root = DataUtil.loadXml(xmlText);
		if (!root.iteratorChilds().hasNext()) {
			return;
		}
		DataNode<XmlTag> n = root.iteratorChilds().next();
		XmlTag x = n.getData();
		if (x.getName().equals("web")) {
			String tail = x.getAttribute("tail");
			if (tail != null) {
				this.tail = tail;
			}
			Iterator<DataNode<XmlTag>> iterator = n.iteratorChilds();

			while (iterator.hasNext()) {
				DataNode<XmlTag> node = iterator.next();
				XmlTag xmlTag = node.getData();
				if (this.webPackages == null && xmlTag.getName().equals("packages")) {
					this.webPackages = new HashMap<String, String>();

					Iterator<DataNode<XmlTag>> iterator1 = node.iteratorChilds();
					while (iterator1.hasNext()) {
						DataNode<XmlTag> nodepackage = iterator1.next();
						XmlTag nodepackageTag = nodepackage.getData();
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
						Iterator<DataNode<XmlTag>> iterator1 = node.iteratorChilds();
						while (iterator1.hasNext()) {
							DataNode<XmlTag> nodeparam = iterator1.next();
							XmlTag nodeparamTag = nodeparam.getData();
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
