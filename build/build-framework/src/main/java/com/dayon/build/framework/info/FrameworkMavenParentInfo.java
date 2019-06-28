package com.dayon.build.framework.info;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.build.base.data.JavaFileBuildInfo;
import com.build.base.data.MavenProjectBuildInfo;
import com.dayon.common.base.DataMap;

public class FrameworkMavenParentInfo implements MavenProjectBuildInfo {
	private String groupId;
	private String artifactId;
	private String version = "1.0.0";
	private String dirName;
	private List<String> modules = new ArrayList<>();
	private Map<String, String> properties = new HashMap<String, String>();
	private Set<String> childApiAppArtifactIds = new HashSet<>();

	public Set<String> getChildApiAppArtifactIds() {
		return childApiAppArtifactIds;
	}

	public FrameworkMavenParentInfo(String groupId, String artifactId) {
		this.groupId = groupId;
		this.artifactId = artifactId;
	}

	public FrameworkMavenParentInfo(String groupId, String artifactId, String dirName) {
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.dirName = dirName;
	}


	
	public FrameworkMavenApiAppInfo createChildMavenApiAppInfo(String actifactId, String dirName, String packageName) {
		FrameworkMavenApiAppInfo mai = new FrameworkMavenApiAppInfo();
		mai.setParentArtifactId(this.artifactId);
		mai.setParentGroupId(groupId);
		mai.setParentVersion(version);
		mai.setArtifactId(actifactId);
		mai.setDirName(dirName);
		mai.setPackageName(packageName);
		return mai;
	}
	
	public FrameworkMavenServiceAppInfo createChildMavenServiceAppInfo(String actifactId, String dirName, String packageName) {
		FrameworkMavenServiceAppInfo mai = new FrameworkMavenServiceAppInfo();
		mai.setParentArtifactId(this.artifactId);
		mai.setParentGroupId(groupId);
		mai.setParentVersion(version);
		mai.setArtifactId(actifactId);
		mai.setDirName(dirName);
		mai.setPackageName(packageName);
		return mai;
	}

	public FrameworkMavenWebAppInfo createChildMavenWebAppInfo(String actifactId, String dirName, String packageName) {
		FrameworkMavenWebAppInfo mai = new FrameworkMavenWebAppInfo();
		mai.setParentArtifactId(this.artifactId);
		mai.setParentGroupId(groupId);
		mai.setParentVersion(version);
		mai.setArtifactId(actifactId);
		mai.setDirName(dirName);
		mai.setPackageName(packageName);
		return mai;
	}

	@Override
	public List<JavaFileBuildInfo> getJavaFileBuildInfos() {
		return new ArrayList<>();
	}

	@Override
	public Object getPomData() {
		DataMap dataMap = new DataMap();
		dataMap.put("groupId", this.groupId);
		dataMap.put("artifactId", this.artifactId);
		dataMap.put("version", this.version);
		dataMap.put("modules", this.modules);
		dataMap.put("properties", this.properties);
		dataMap.put("childApiAppArtifactIds", this.childApiAppArtifactIds);
		return dataMap;
	}

	@Override
	public String getProjectDirName() {
		return dirName == null ? artifactId : dirName;
	}

	@Override
	public String getPomTemplateResourceName() {
		return "framework-maven-parent-pom.ftl";
	}

	@Override
	public List<String> getProjectDirectoryPaths() {
		return new ArrayList<>();
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<String> getModules() {
		return modules;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	@Override
	public Map<String, File> getResourcesFileMap() {
		return new HashMap<>();
	}

}
