package com.dayon.build.base.info;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface MavenProjectBuildInfo {
	public String getProjectDirName();

	public String getPomTemplateResourceName();

	public Object getPomData();

	public List<String> getProjectDirectoryPaths();
	
	public List<JavaFileBuildInfo> getJavaFileBuildInfos();
	
	public Map<String,File> getResourcesFileMap();
}
