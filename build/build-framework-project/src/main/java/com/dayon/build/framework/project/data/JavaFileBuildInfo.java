package com.dayon.build.framework.project.data;

import java.util.Map;

public interface JavaFileBuildInfo {

	String getResourceDirName();

	String getTemplateResourceName();

	Map<String, Object> getFileNameAndData();
}
