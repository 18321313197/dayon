package com.dayon.build.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ServiceImplInfo implements JavaFileBuildInfo {
	private Set<Table> tables = new HashSet<>();
	private String packageName;
	private String entityInfoPackageName;
	private String mapperInfoPackageName;
	private String serviceInfoPackageName;

	public ServiceImplInfo(String packageName, String entityInfoPackageName, String mapperInfoPackageName,
			String serviceInfoPackageName) {
		this.packageName = packageName;
		this.entityInfoPackageName = entityInfoPackageName;
	}

	public ServiceImplInfo(String packageName, String entityInfoPackageName, String mapperInfoPackageName,
			String serviceInfoPackageName, Collection<Table> tables) {
		this.tables.addAll(tables);
		this.packageName = packageName;
		this.entityInfoPackageName = entityInfoPackageName;
		this.mapperInfoPackageName = mapperInfoPackageName;
		this.serviceInfoPackageName = serviceInfoPackageName;

	}

	public String getMapperInfoPackageName() {
		return mapperInfoPackageName;
	}

	public String getServiceInfoPackageName() {
		return serviceInfoPackageName;
	}

	@Override
	public String getResourceDirName() {
		return "src/main/java/" + packageName.replace(".", "/");
	}

	@Override
	public String getTemplateResourceName() {
		return "service-impl.ftl";
	}

	@Override
	public Map<String, Object> getFileNameAndData() {
		Map<String, Object> retMap = new HashMap<>();
		for (Table table : tables) {
			Map<String, Object> map = new HashMap<>();
			String entityClassSimpleName = Table.tableMameToEntityName(table.getName());
			JavaTypeInfo entityTypeInfo = new JavaTypeInfo(entityInfoPackageName + "." + entityClassSimpleName);
			JavaTypeInfo serviceImplTypeInfo = new JavaTypeInfo(packageName + "." + entityClassSimpleName + "ServiceImpl");
			JavaTypeInfo mapperTypeInfo = new JavaTypeInfo(mapperInfoPackageName + "." + entityClassSimpleName + "Mapper");
			JavaTypeInfo serviceTypeInfo = new JavaTypeInfo(serviceInfoPackageName + "." + entityClassSimpleName + "Service");
			
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
			map.put("serviceImplTypeInfo", serviceImplTypeInfo);
			map.put("serviceTypeInfo", serviceTypeInfo);
			map.put("mapperTypeInfo", mapperTypeInfo);
			map.put("entityTypeInfo", entityTypeInfo);
			map.put("idTypeInfos", idTypeInfos);
			map.put("imports", imports);
			retMap.put(serviceImplTypeInfo.getFileName(), map);
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
