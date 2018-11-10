package com.dayon.build.base;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapperInfo implements JavaFileBuildInfo {
	private Set<Table> tables = new HashSet<>();
	private String packageName;
	private String entityInfoPackageName;

	public MapperInfo(String packageName, String entityInfoPackageName) {
		this.packageName = packageName;
		this.entityInfoPackageName = entityInfoPackageName;
	}

	public MapperInfo(String packageName, String entityInfoPackageName, Collection<Table> tables) {
		this.tables.addAll(tables);
		this.packageName = packageName;
		this.entityInfoPackageName = entityInfoPackageName;

	}

	@Override
	public String getPackageDirName() {
		return "src/main/java/" + packageName.replace(".", "/");
	}

	@Override
	public String getTemplateResourceName() {
		return "mapper.ftl";
	}

	@Override
	public Map<String, Object> getFileNameAndData() {
		Map<String, Object> retMap = new HashMap<>();
		for (Table table : tables) {
			Map<String, Object> map = new HashMap<>();

			String entitySimpleType = Table.tableMameToEntityName(table.getName());
			String entityType = entityInfoPackageName + "." + entitySimpleType;
			String entityName = entitySimpleType.substring(0, 1).toLowerCase() + entitySimpleType.substring(1);
			String className = entitySimpleType + "Mapper";
			map.put("package", packageName);
			map.put("entitySimpleType", entitySimpleType);
			map.put("entityType", entityType);
			map.put("entityName", entityName);

			retMap.put(className + ".java", map);
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

	public void setEntityInfoPackageName(String entityInfoPackageName) {
		this.entityInfoPackageName = entityInfoPackageName;
	}

}
