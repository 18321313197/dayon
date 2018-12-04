package com.dayon.build.base.info;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dayon.build.base.data.JavaFileBuildInfo;
import com.dayon.build.base.data.JavaTypeInfo;
import com.dayon.build.base.data.Table;

public class SpringDubboServiceXmlInfo implements JavaFileBuildInfo {
	private Set<Table> tables = new HashSet<>();
	private String servicePackageName;

	public SpringDubboServiceXmlInfo(String servicePackageName) {
		this.servicePackageName = servicePackageName;
	}

	public SpringDubboServiceXmlInfo(String servicePackageName, Collection<Table> tables) {
		this.tables.addAll(tables);
		this.servicePackageName = servicePackageName;
	}

	@Override
	public String getResourceDirName() {
		return "src/main/resources";
	}

	@Override
	public String getTemplateResourceName() {
		return "spring-dubbo-service.ftl";
	}

	@Override
	public Map<String, Object> getFileNameAndData() {

		Map<String, Object> retMap = new HashMap<>();
		Map<String, Object> data=new HashMap<>();
		List<JavaTypeInfo> serviceTypeInfos=new ArrayList<>();
		
		for (Table table : tables) {
			String entityClassSimpleName = Table.tableMameToEntityName(table.getName());
			JavaTypeInfo serviceTypeInfo = new JavaTypeInfo(servicePackageName + "." + entityClassSimpleName+"Service");
			serviceTypeInfos.add(serviceTypeInfo);
		}
		data.put("serviceTypeInfos", serviceTypeInfos);
		retMap.put("spring-dubbo-service.xml", data);
		return retMap;
	}

	public Set<Table> getTables() {
		return tables;
	}

	
}
