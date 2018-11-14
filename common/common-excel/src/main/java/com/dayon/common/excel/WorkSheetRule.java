package com.dayon.common.excel;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class WorkSheetRule {
	private String name;
	private List<?> datas;
	private String[] titles;
	private String[] fieldNames;
	public WorkSheetRule(String name) {
		this.name = name;
	}
	public void setTitles(String...titles){
		this.titles =titles;
	}
	public void setDatas(List<?> datas,String... fieldNames){
		this.datas=datas;
		this.fieldNames=fieldNames;
	}
	public String getName() {
		return name;
	}
	
	public String[] getTitles() {
		return titles;
	}
	public List<List<String>> getRowDatas(){
		List<List<String>> llist=new ArrayList<List<String>>();
		DateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(datas!=null){
			for (Object data : datas) {
				List<String> list=new ArrayList<String>();
				llist.add(list);
				for (String name : fieldNames) {
					try {
						Object obj;
						if(data instanceof Map){
							Map<?,?> mp=(Map<?,?>)data;
							obj=mp.get(name);
						}else{
							Field field=data.getClass().getDeclaredField(name);
							field.setAccessible(true);
							obj=field.get(data);
						}
						if(obj==null){
							list.add("");
							continue;
						}
						if(obj instanceof Date){
							list.add(format.format((Date)obj));
						}else{
							list.add(obj.toString());
						}
					} catch (Exception e) {
						e.printStackTrace();
						list.add("");
					}
				}
				
			}
			
		}	
		return llist;
	}
	
}
