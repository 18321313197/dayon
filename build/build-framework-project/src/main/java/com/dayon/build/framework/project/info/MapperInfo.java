package com.dayon.build.framework.project.info;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dayon.build.framework.project.data.Column;
import com.dayon.build.framework.project.data.JavaFileBuildInfo;
import com.dayon.build.framework.project.data.JavaTypeInfo;
import com.dayon.build.framework.project.data.Table;
import com.dayon.build.framework.project.util.MySqlDBPool;

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
	public String getResourceDirName() {
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
			String entityClassSimpleName = Table.tableMameToEntityName(table.getName());
			JavaTypeInfo entityTypeInfo = new JavaTypeInfo(entityInfoPackageName + "." + entityClassSimpleName);
			JavaTypeInfo mapperTypeInfo = new JavaTypeInfo(packageName + "." + entityClassSimpleName + "Mapper");
			List<JavaTypeInfo> idTypeInfos = new ArrayList<>();
			Set<String> imports=new HashSet<>();
			for (Column column : table.getColumns()) {
				if (column.getIsPrimary()) {
					JavaTypeInfo idTypeInfo = new JavaTypeInfo(MySqlDBPool.getJavaType(column.getType()).getName());
					idTypeInfos.add(idTypeInfo);
					idTypeInfo.setJavaName(Column.columnMameToJavaName(column.getName()));
					if(!idTypeInfo.getName().startsWith("java.lang.")){
						imports.add(idTypeInfo.getName());
					}
				}

			}

			map.put("mapperTypeInfo", mapperTypeInfo);
			map.put("entityTypeInfo", entityTypeInfo);
			map.put("idTypeInfos", idTypeInfos);
			map.put("imports", imports);
			retMap.put(mapperTypeInfo.getFileName(), map);
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
