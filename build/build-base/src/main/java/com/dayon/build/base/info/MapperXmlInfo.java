package com.dayon.build.base.info;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.dayon.build.base.data.Column;
import com.dayon.build.base.data.JavaFileBuildInfo;
import com.dayon.build.base.data.JavaTypeInfo;
import com.dayon.build.base.data.Table;
import com.dayon.common.base.model.DataList;
import com.dayon.common.base.model.DataMap;

public class MapperXmlInfo implements JavaFileBuildInfo {
	private Set<Table> tables = new HashSet<>();
	private String entityPackageName;
	private String mapperPackageName;

	public MapperXmlInfo(String mapperPackageName,String entityPackageName) {
		this.entityPackageName = entityPackageName;
		this.mapperPackageName = mapperPackageName;
	}

	public MapperXmlInfo(String mapperPackageName,String entityPackageName,  Collection<Table> tables) {
		this.tables.addAll(tables);
		this.entityPackageName = entityPackageName;
		this.mapperPackageName = mapperPackageName;
	}

	@Override
	public String getResourceDirName() {
		return "src/main/resources/" + mapperPackageName.replace(".", "/");
	}

	@Override
	public String getTemplateResourceName() {
		return "mapper-xml.ftl";
	}

	@Override
	public Map<String, Object> getFileNameAndData() {

		Map<String, Object> retMap = new HashMap<>();
		for (Table table : tables) {
			Map<String, Object> map = new HashMap<>();
			String entityClassSimpleName = Table.tableMameToEntityName(table.getName());
			JavaTypeInfo entityTypeInfo = new JavaTypeInfo(entityPackageName + "." + entityClassSimpleName);
			JavaTypeInfo mapperTypeInfo = new JavaTypeInfo(mapperPackageName + "." + entityClassSimpleName + "Mapper");
			DataList idDataList = new DataList();
			DataList otherDataList = new DataList();
			DataList dataList=new DataList();
			for (Column column : table.getColumns()) {
				DataMap dataMap = new DataMap();
				dataMap.put("column", column);
				dataMap.put("attrName", Column.columnMameToJavaName(column.getName()));
				dataList.add(dataMap);
				if(column.getIsPrimary()){
					idDataList.add(dataMap);
				}else{
					otherDataList.add(dataMap);
				}
			}
			map.put("columnInfos", dataList);
			map.put("otherColumnInfos", otherDataList);
			map.put("idColumnInfos", idDataList);
			map.put("mapperTypeInfo", mapperTypeInfo);
			map.put("entityTypeInfo", entityTypeInfo);
			map.put("tableName", table.getName());
			retMap.put(mapperTypeInfo.getSimpleName() + ".xml", map);

		}
		return retMap;
	}

	public Set<Table> getTables() {
		return tables;
	}

}
