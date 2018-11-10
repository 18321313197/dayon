package com.dayon.build.base;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.dayon.common.model.DataList;
import com.dayon.common.model.DataMap;

import freemarker.template.Template;

public class Main {

	public static void outEntity(String className, DataList dl) {
		StringBuilder sb = new StringBuilder();
		sb.append("public class ").append(className).append(" extends Empty {\r\n");
		for (DataMap dm : dl) {
			sb.append("\tprivate ").append(dm.get("DATA_TYPE")).append(" ").append(dm.get("COLUMN_NAME"))
					.append(";\r\n");
		}
		sb.append("}");
		System.out.println(sb);
	}

	public static void main(String[] args) {

		String[] strs = "t_auth-manage".split("-|_");
		char[] chars = "t_auth-manage".toCharArray();

		StringBuilder sb = new StringBuilder();
		boolean bool = true;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == '-' || chars[i] == '_') {
				bool = true;
				continue;
			}
			if (bool){
				if (chars[i] >= 'a' && chars[i] <= 'z') {
					chars[i] -= 32;
				}
				bool=false;
			}
			
			sb.append(chars[i]);
		}
		System.out.println(sb);
	}
}
