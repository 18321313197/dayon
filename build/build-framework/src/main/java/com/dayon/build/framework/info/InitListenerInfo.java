package com.dayon.build.framework.info;

import java.util.HashMap;
import java.util.Map;

import com.build.base.data.JavaFileBuildInfo;
import com.build.base.data.JavaTypeInfo;

public class InitListenerInfo implements JavaFileBuildInfo {
	private String packageName;

	public InitListenerInfo(String packageName) {
		this.packageName = packageName;
	}

	@Override
	public String getResourceDirName() {
		return "src/main/java/" + packageName.replace(".", "/");
	}
	@Override
	public String getTemplateResourceName() {
		return "InitListener.ftl";
	}

	@Override
	public Map<String, Object> getFileNameAndData() {
		Map<String, Object> retMap = new HashMap<>();
		Map<String, Object> data=new HashMap<>();
		data.put("initListenerTypeInfo", new JavaTypeInfo(packageName+".listener"));
		retMap.put("InitListener.java", data);
		return retMap;
	}

	
	public String getPackageName() {
		return packageName;
	}

}
