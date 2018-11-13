package com.dayon.build.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ServiceInfo implements JavaFileBuildInfo {
	private Set<Table> tables = new HashSet<>();
	private String packageName;
	private String entityInfoPackageName;

	public ServiceInfo(String packageName,String entityInfoPackageName) {
		this.packageName = packageName;
		this.entityInfoPackageName = entityInfoPackageName;
	}

	public ServiceInfo(String packageName, String entityInfoPackageName, Collection<Table> tables) {
		this.tables.addAll(tables);
		this.packageName = packageName;
		this.entityInfoPackageName = entityInfoPackageName;

	}

	

	@Override
	public String getResourceDirName() {
		return "src/main/java/" + packageName.replace(".", "/");
	}

	@Override
	public String getTemplateResourceName() {
		return "service.ftl";
	}

	@Override
	public Map<String, Object> getFileNameAndData() {
		Map<String, Object> retMap = new HashMap<>();
		for (Table table : tables) {
			Map<String, Object> map = new HashMap<>();
			String entityClassSimpleName = Table.tableMameToEntityName(table.getName());
			JavaTypeInfo entityTypeInfo = new JavaTypeInfo(entityInfoPackageName + "." + entityClassSimpleName);
			JavaTypeInfo serviceTypeInfo = new JavaTypeInfo(packageName + "." + entityClassSimpleName + "Service");
			
			List<JavaTypeInfo> idTypeInfos = new ArrayList<>();
			Set<String> imports = new HashSet<>();
			for (Column column : table.getColumns()) {
				if (column.getIsPrimary()) {
					JavaTypeInfo idTypeInfo = new JavaTypeInfo(MySqlDBPool.getJavaType(column.getType()).getName());
					idTypeInfos.add(idTypeInfo);
					idTypeInfo.setJavaName(Column.columnMameToJavaName(column.getName()));
					if (!idTypeInfo.getName().startsWith("java.lang.")) {
						imports.add(idTypeInfo.getName());
					}
				}

			}
			map.put("serviceTypeInfo", serviceTypeInfo);
			map.put("entityTypeInfo", entityTypeInfo);
			map.put("idTypeInfos", idTypeInfos);
			map.put("imports", imports);
			retMap.put(serviceTypeInfo.getFileName(), map);
		}
		return retMap;
	}

	public Set<Table> getTables() {
		return tables;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getEntityInfoPackageName() {
		return entityInfoPackageName;
	}

}
