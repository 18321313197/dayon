package com.dayon.build.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EntityInfo implements JavaFileBuildInfo {
	private Set<Table> tables = new HashSet<>();
	private String packageName;

	public EntityInfo(String packageName) {
		this.packageName = packageName;
	}

	public EntityInfo(String packageName, Collection<Table> tables) {
		this.tables.addAll(tables);
		this.packageName = packageName;
	}

	@Override
	public String getPackageDirName() {
		return "src/main/java/" + packageName.replace(".", "/");
	}



	@Override
	public String getTemplateResourceName() {
		return "entity.ftl";
	}

	@Override
	public Map<String, Object> getFileNameAndData() {
		Map<String, Object> retMap = new HashMap<>();
		for (Table table : tables) {
			Map<String, Object> map = new HashMap<>();
			String className = Table.tableMameToEntityName(table.getName());
			retMap.put(className + ".java", map);
			
			map.put("package", packageName);
			map.put("class", className);

			List<Map<String, String>> fields = new ArrayList<>();
			List<Map<String, String>> methods = new ArrayList<>();
			Set<String> imports = new HashSet<>();

			map.put("fields", fields);
			map.put("imports", imports);
			map.put("methods", methods);

			for (Column column : table.getColumns()) {
				Map<String, String> field = new HashMap<>();
				Map<String, String> getMethod = new HashMap<>();
				Map<String, String> setMethod = new HashMap<>();
				methods.add(getMethod);
				methods.add(setMethod);
				fields.add(field);
				String fieldName = Column.columnMameToJavaName(column.getName());
				Class<?> fileType = MySqlDBPool.getJavaType(column.getType());
				if (!fileType.getName().startsWith("java.lang.")) {
					imports.add(fileType.getName());
				}
				field.put("name", fieldName);
				field.put("comment", column.getComment());
				field.put("type", fileType.getSimpleName());

				getMethod.put("core", "return " + fieldName + ";");
				getMethod.put("ret", fileType.getSimpleName());
				getMethod.put("paramStr", "");

				setMethod.put("core", "this." + fieldName + " = " + fieldName + ";");
				setMethod.put("ret", "void");
				setMethod.put("paramStr", fileType.getSimpleName() + " " + fieldName);

				
				String str=fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
				setMethod.put("name", "set" + str);
				getMethod.put("name", "get" + str);
			}
		}
		return retMap;
	}

	public Set<Table> getTables() {
		return tables;
	}

	public String getPackageName() {
		return packageName;
	}

}
