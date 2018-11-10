package com.dayon.build.base;

import java.util.Map;

public interface JavaFileBuildInfo {

	String getPackageDirName();

	String getTemplateResourceName();

	Map<String, Object> getFileNameAndData();
}
