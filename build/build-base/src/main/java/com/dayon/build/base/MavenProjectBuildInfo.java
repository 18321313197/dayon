package com.dayon.build.base;

import java.util.List;

public interface MavenProjectBuildInfo {
	public String getProjectDirName();

	public String getPomTemplateResourceName();

	public Object getPomData();

	public List<String> getProjectDirectoryPaths();
	
	public List<JavaFileBuildInfo> getJavaFileBuildInfos();
}
