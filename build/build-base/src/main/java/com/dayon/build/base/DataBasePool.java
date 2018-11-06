package com.dayon.build.base;

import java.util.Properties;

import com.dayon.common.jdbc.SessionPool;
import com.dayon.common.model.DataList;

public  class DataBasePool {
	
	private SessionPool sp=null;
	private int databaseType=0;//1=mysql,2=sqlserver,3=oracle
	private String databaseName=null;
	
	public DataBasePool(String driver,String url,String user,String password){
		try {
		//1=mysql,2=sqlserver,3=oracle
		switch (driver) {
		case "com.mysql.jdbc.Driver":
			this.databaseType=1;
			break;
		default:
			break;
		}
		 databaseName=url.substring(url.lastIndexOf("/")+1, url.lastIndexOf("?"));
		
		Properties p=new Properties();
		p.setProperty("driver", driver);
		p.setProperty("url", url);
		p.setProperty("user", user);
		p.setProperty("password", password);
		this.sp=new SessionPool(p,1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public DataList getDataBaseInfo(){
		if(databaseType==1){
			try {
				DataList dataList=sp.getSession().find("SELECT table_name,column_name,data_type,column_comment FROM information_schema.COLUMNS WHERE table_schema = ?", databaseName);
				return dataList;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				sp.getSession().close();
			}
		}
		return null;
		
	}
}
