package com.dayon.build.framework.project.info;

import java.util.HashMap;
import java.util.Map;

import com.dayon.build.framework.project.data.JavaFileBuildInfo;
import com.dayon.build.framework.project.data.JavaTypeInfo;

public class AppInfo implements JavaFileBuildInfo {
	private String packageName;

	public AppInfo(String packageName) {
		this.packageName = packageName;
	}

	
	@Override
	public String getResourceDirName() {
		return "src/main/java/" + packageName.replace(".", "/");
	}
	@Override
	public String getTemplateResourceName() {
		return "app.ftl";
	}

	@Override
	public Map<String, Object> getFileNameAndData() {
		Map<String, Object> retMap = new HashMap<>();
		Map<String, Object> data=new HashMap<>();
		data.put("appTypeInfo", new JavaTypeInfo(packageName+".App"));
		retMap.put("App.java", data);
		return retMap;
	}

	
	public String getPackageName() {
		return packageName;
	}

}
