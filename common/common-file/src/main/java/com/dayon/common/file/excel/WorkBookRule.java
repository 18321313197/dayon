package com.dayon.common.file.excel;



import java.util.ArrayList;
import java.util.List;

public class WorkBookRule {
	private List<WorkSheetRule> sheets=new ArrayList<WorkSheetRule>();
	
	public void addPoiWorkSheetRule(WorkSheetRule sheet){
		if(sheet!=null){
			sheets.add(sheet);
		}
	}
	public List<WorkSheetRule> getSheets() {
		return sheets;
	}
	
}
