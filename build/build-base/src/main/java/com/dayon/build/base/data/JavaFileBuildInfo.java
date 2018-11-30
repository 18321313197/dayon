package com.dayon.build.base.data;

import java.util.Map;

public interface JavaFileBuildInfo {

	String getResourceDirName();

	String getTemplateResourceName();

	Map<String, Object> getFileNameAndData();
}
