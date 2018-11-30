package com.dayon.build.base.info;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dayon.build.base.data.Column;
import com.dayon.build.base.data.JavaFileBuildInfo;
import com.dayon.build.base.data.JavaTypeInfo;
import com.dayon.build.base.data.Table;
import com.dayon.build.base.util.MySqlDBPool;

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
	public String getResourceDirName() {
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
			String entityClassSimpleName = Table.tableMameToEntityName(table.getName());
			
			JavaTypeInfo javaTypeInfo=new JavaTypeInfo(packageName+"."+entityClassSimpleName); 
			
			retMap.put(javaTypeInfo.getFileName(), map);
			
			map.put("entityTypeInfo", javaTypeInfo);

			List<JavaTypeInfo> attrInfos = new ArrayList<JavaTypeInfo>();
			Set<String> imports = new HashSet<>();
			
			map.put("attrInfos", attrInfos);
			map.put("imports", imports);

			for (Column column : table.getColumns()) {
				String attrName = Column.columnMameToJavaName(column.getName());
				Class<?> attrType = MySqlDBPool.getJavaType(column.getType());
				javaTypeInfo=new JavaTypeInfo(attrType.getName(),attrName);
				attrInfos.add(javaTypeInfo);
	
				if (!javaTypeInfo.getPackageName().startsWith("java.lang.")) {
					imports.add(javaTypeInfo.getName());
				}
				
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
