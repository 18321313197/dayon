package com.dayon.build.framework.info;

import java.util.HashMap;
import java.util.Map;

import com.build.base.data.JavaFileBuildInfo;

public class SpringContextWebInfo implements JavaFileBuildInfo {
	private String componentScanBasePackage;

	public SpringContextWebInfo(String componentScanBasePackage) {
		this.componentScanBasePackage = componentScanBasePackage;
	}

	@Override
	public String getResourceDirName() {
		return "src/main/resources";
	}

	@Override
	public String getTemplateResourceName() {
		return "spring-context-web.ftl";
	}

	@Override
	public Map<String, Object> getFileNameAndData() {

		Map<String, Object> retMap = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		data.put("componentScanBasePackage", componentScanBasePackage);
		retMap.put("spring-context.xml", data);
		return retMap;
	}

}
