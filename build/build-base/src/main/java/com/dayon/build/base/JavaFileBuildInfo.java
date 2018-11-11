package com.dayon.build.base;

import java.util.Map;

public interface JavaFileBuildInfo {

	String getResourceDirName();

	String getTemplateResourceName();

	Map<String, Object> getFileNameAndData();
}
