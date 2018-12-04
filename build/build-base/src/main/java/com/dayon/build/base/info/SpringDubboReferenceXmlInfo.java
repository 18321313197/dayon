package com.dayon.build.base.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dayon.build.base.data.JavaFileBuildInfo;
import com.dayon.build.base.data.JavaTypeInfo;
import com.dayon.build.base.data.Table;

public class SpringDubboReferenceXmlInfo implements JavaFileBuildInfo {
	private List<ServiceInfo> serviceInfos=new ArrayList<>();

	

	@Override
	public String getResourceDirName() {
		return "src/main/resources";
	}

	@Override
	public String getTemplateResourceName() {
		return "spring-dubbo-reference.ftl";
	}

	@Override
	public Map<String, Object> getFileNameAndData() {

		Map<String, Object> retMap = new HashMap<>();
		Map<String, Object> data=new HashMap<>();
		List<JavaTypeInfo> serviceTypeInfos=new ArrayList<>();
		
		for (ServiceInfo serviceInfo : serviceInfos) {
			String servicePackageName=serviceInfo.getPackageName();
			for (Table table : serviceInfo.getTables()) {
				String entityClassSimpleName = Table.tableMameToEntityName(table.getName());
				JavaTypeInfo serviceTypeInfo = new JavaTypeInfo(servicePackageName + "." + entityClassSimpleName+"Service");
				serviceTypeInfos.add(serviceTypeInfo);
			}
			
		}
		data.put("serviceTypeInfos", serviceTypeInfos);
		retMap.put("spring-dubbo-reference.xml", data);
		return retMap;
	}

	public List<ServiceInfo> getServiceInfos() {
		return serviceInfos;
	}

	
}
