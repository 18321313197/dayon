package com.dayon.build.framework.project.data;

public class JavaTypeInfo {

	private String name;
	private String javaName;

	public JavaTypeInfo(String name) {
		this.name = name;
	}

	public JavaTypeInfo(String name, String javaName) {
		this.name = name;
		this.javaName = javaName;
	}

	public String getPackageName() {
		return name.substring(0, name.lastIndexOf('.'));
	}

	public String getSimpleName() {
		return name.substring(name.lastIndexOf('.') + 1, name.length());
	}

	public String getName() {
		return name;
	}

	public String getFileName() {
		return getSimpleName() + ".java";
	}
	

	public String getJavaName() {
		String jsname=getSimpleName();
		return javaName != null ? javaName : jsname.substring(0, 1).toLowerCase() + jsname.substring(1);
	}

	public void setJavaName(String javaName) {
		this.javaName = javaName;
	}
	public String getJavaGetMethodName(){
		String jname=getJavaName();
		return "get"+jname.substring(0, 1).toUpperCase()+jname.substring(1);
	}
	public String getJavaSetMethodName(){
		String jname=getJavaName();
		return "set"+jname.substring(0, 1).toUpperCase()+jname.substring(1);
	}

}
