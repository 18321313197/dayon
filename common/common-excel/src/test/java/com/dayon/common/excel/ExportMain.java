package com.dayon.common.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dayon.common.excel.ExportUtil;
import com.dayon.common.excel.WorkBookRule;
import com.dayon.common.excel.WorkSheetRule;

public class ExportMain {
	public static void main(String[] args) {
		List<Entity> entitys=new ArrayList<Entity>();
		for(int i=0;i<25;i++){
			Entity entity=new Entity(i+100l,"name"+i,Double.parseDouble(i+""),new Date());
			entitys.add(entity);
		}
	
		WorkBookRule pwbr=new WorkBookRule();
		WorkSheetRule pwsr=new  WorkSheetRule("sheet1");
		WorkSheetRule pwsr2=new  WorkSheetRule("sheet2");
		
		pwsr.setTitles("编号","名称","金钱","时间");
		pwsr.setDatas(entitys,"id","name","money","date");
		pwbr.addPoiWorkSheetRule(pwsr); 
		
		pwsr2.setTitles("编号","名称","金钱","时间");
		pwsr2.setDatas(entitys,"id","name","money","date");
		pwbr.addPoiWorkSheetRule(pwsr2); 
		String excelName = "c:/table-new.xls";  
		  try {  
		   File excelFile = new File(excelName);  
		   OutputStream os=new FileOutputStream(excelFile);
		   ExportUtil.exportExcel(pwbr, os);
		   os.close();
		   System.out.println("Excel创建成功");  
		  } catch (Exception e) {  
		   System.out.println(e);  
		  }  
	}
}
class Entity{
	private Long id;
	private String name;
	private Double money;
	private Date date;
	
	
	
	public Entity(Long id, String name, Double money, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.money = money;
		this.date = date;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
