package com.dayon.build.base.info;

import java.util.Map;

public interface JavaFileBuildInfo {

	String getResourceDirName();

	String getTemplateResourceName();

	Map<String, Object> getFileNameAndData();
}
