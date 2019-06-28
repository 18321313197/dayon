package com.dayon.build.framework.info;

import java.util.HashMap;
import java.util.Map;

import com.build.base.data.JavaFileBuildInfo;

public class WebInfo implements JavaFileBuildInfo {
	private String initListenerInfoName;

	public WebInfo(String initListenerInfoName) {
		this.initListenerInfoName = initListenerInfoName;
	}

	@Override
	public String getResourceDirName() {
		return "src/main/webapp/WEB-INF";
	}

	@Override
	public String getTemplateResourceName() {
		return "web.ftl";
	}

	@Override
	public Map<String, Object> getFileNameAndData() {

		Map<String, Object> retMap = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		data.put("initListenerTypeInfo", initListenerInfoName);
		retMap.put("web.xml", data);
		return retMap;
	}

}
