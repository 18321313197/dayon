package com.dayon.build.framework.project.info;

import java.util.HashMap;
import java.util.Map;

import com.dayon.build.framework.project.data.JavaFileBuildInfo;
import com.dayon.build.framework.project.data.JavaTypeInfo;

public class IndexControllerInfo implements JavaFileBuildInfo {

	private String packageName;

	public IndexControllerInfo(String packageName) {
		this.packageName = packageName;
	}

	@Override
	public String getResourceDirName() {
		return "src/main/java/" + packageName.replace(".", "/");
	}

	@Override
	public String getTemplateResourceName() {
		return "index-controller.ftl";
	}

	@Override
	public Map<String, Object> getFileNameAndData() {
		Map<String, Object> retMap = new HashMap<>();
		JavaTypeInfo javaTypeInfo=new JavaTypeInfo(packageName+".IndexController");
		Map<String,Object> data=new HashMap<String,Object>();
		data.put("indexControllerTypeInfo", javaTypeInfo);
		retMap.put(javaTypeInfo.getFileName(), data);
		return retMap;
	}

	public String getPackageName() {
		return packageName;
	}



}
