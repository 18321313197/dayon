package com.dayon.build.base;

import com.dayon.common.model.DataList;
import com.dayon.common.model.DataMap;

public class Main {
	
	public static void outEntity(String className,DataList dl){
		StringBuilder sb=new StringBuilder();
		sb.append("public class ").append(className).append(" extends Empty {\r\n");
		for (DataMap dm : dl) {
			sb.append("\tprivate ").append(dm.get("DATA_TYPE")).append(" ").append(dm.get("COLUMN_NAME")).append(";\r\n");
		}
		sb.append("}");
		System.out.println(sb);
	}
	
	public static void main(String[] args) {
		
		DataBasePool dbp=new DataBasePool("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/b2b2c?characterEncoding=utf-8", "root", "12345qwe");
		DataList dataList=dbp.getDataBaseInfo();
		
		DataMap dm=new DataMap();
		for (DataMap dataMap : dataList) {
			DataList dl=dm.getDataList(dataMap.getString("TABLE_NAME"));
			if(dl==null){
				dl=new DataList();
				dm.put(dataMap.getString("TABLE_NAME"), dl);
			}
			dataMap.remove("TABLE_NAME");
			dl.add(dataMap);
		}
		for(String key:dm.keySet()){
			outEntity(key,dm.getDataList(key));
		}
		
		/* try {
	            // 创建插值的map
	            Map<String,Object> map = new HashMap<String,Object>();
	            map.put("user", "rr");
	            map.put("url", "http://www.baidu.com/");
	            map.put("name", "百度");

	            // 创建一个模板对象
	            Template t = new Template(null, new StringReader(
	                    "用户名：${user};URL：    ${url};姓名： 　${name}"), null);
	            

	            // 执行插值，并输出到指定的输出流中
	            Writer writer = new FileWriter("c:/test2.ftl");
	            t.process(map, writer);
	            // t.process(map, new OutputStreamWriter(System.out));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }*/
	}
}
