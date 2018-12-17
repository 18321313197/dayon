package com.dayon.build.framework.project.info;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dayon.build.framework.project.data.JavaFileBuildInfo;
import com.dayon.build.framework.project.data.MavenProjectBuildInfo;
import com.dayon.common.base.dto.model.DataMap;

public class MavenManagerInfo implements MavenProjectBuildInfo {
	private String parentVersion = "1.0";
	private String groupId;
	private String artifactId;
	private String version = "1.0.0";
	private String frameWorkVersion;
	private String frameWorkGroupId;
	private String frameWorkApiArtifactId;
	private String frameWorkServerArtifactId;
	private String frameWorkWebArtifactId;
	private String dirName;
	private List<String> modules = new ArrayList<>();
	private Map<String, String> properties = new HashMap<String, String>();
	private Set<String> childApiAppArtifactIds = new HashSet<>();

	public Set<String> getChildApiAppArtifactIds() {
		return childApiAppArtifactIds;
	}

	public String getFrameWorkGroupId() {
		return frameWorkGroupId;
	}

	public void setFrameWorkGroupId(String frameWorkGroupId) {
		this.frameWorkGroupId = frameWorkGroupId;
	}

	public String getFrameWorkApiArtifactId() {
		return frameWorkApiArtifactId;
	}

	public void setFrameWorkApiArtifactId(String frameWorkApiArtifactId) {
		this.frameWorkApiArtifactId = frameWorkApiArtifactId;
	}

	public String getFrameWorkServerArtifactId() {
		return frameWorkServerArtifactId;
	}

	public void setFrameWorkServerArtifactId(String frameWorkServerArtifactId) {
		this.frameWorkServerArtifactId = frameWorkServerArtifactId;
	}

	public String getFrameWorkWebArtifactId() {
		return frameWorkWebArtifactId;
	}

	public void setFrameWorkWebArtifactId(String frameWorkWebArtifactId) {
		this.frameWorkWebArtifactId = frameWorkWebArtifactId;
	}

	public String getFrameWorkVersion() {
		return frameWorkVersion;
	}

	public void setFrameWorkVersion(String frameWorkVersion) {
		this.frameWorkVersion = frameWorkVersion;
	}

	public String getParentVersion() {
		return parentVersion;
	}

	public void setParentVersion(String parentVersion) {
		this.parentVersion = parentVersion;
	}

	public MavenManagerInfo(String groupId, String artifactId, String frameWorkGroupId, String frameWorkVersion,
			String frameWorkApiArtifactId, String frameWorkServerArtifactId, String frameWorkWebArtifactId) {
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.frameWorkVersion = frameWorkVersion;
		this.frameWorkGroupId = frameWorkGroupId;
		this.frameWorkApiArtifactId = frameWorkApiArtifactId;
		this.frameWorkServerArtifactId = frameWorkServerArtifactId;
		this.frameWorkWebArtifactId = frameWorkWebArtifactId;
	}

	public MavenManagerInfo(String groupId, String artifactId, String frameWorkGroupId, String frameWorkVersion,
			String frameWorkApiArtifactId, String frameWorkServerArtifactId, String frameWorkWebArtifactId,
			String dirName) {
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.dirName = dirName;
		this.frameWorkVersion = frameWorkVersion;
		this.frameWorkGroupId = frameWorkGroupId;
		this.frameWorkApiArtifactId = frameWorkApiArtifactId;
		this.frameWorkServerArtifactId = frameWorkServerArtifactId;
		this.frameWorkWebArtifactId = frameWorkWebArtifactId;
	}

	public MavenAppInfo createChildMavenAppInfo(String actifactId) {
		MavenAppInfo mai = new MavenAppInfo();
		mai.setParentArtifactId(this.artifactId);
		mai.setParentGroupId(groupId);
		mai.setParentVersion(version);
		mai.setArtifactId(actifactId);
		return mai;
	}

	public MavenAppInfo createChildMavenAppInfo(String actifactId, String dirName, String packageName) {
		MavenAppInfo mai = new MavenAppInfo();
		mai.setParentArtifactId(this.artifactId);
		mai.setParentGroupId(groupId);
		mai.setParentVersion(version);
		mai.setArtifactId(actifactId);
		mai.setDirName(dirName);
		mai.setPackageName(packageName);
		mai.setPackaging("jar");
		return mai;
	}

	public MavenAppInfo createChildMavenWebAppInfo(String actifactId, String dirName, String packageName) {
		MavenAppInfo mai = new MavenAppInfo();
		mai.setParentArtifactId(this.artifactId);
		mai.setParentGroupId(groupId);
		mai.setParentVersion(version);
		mai.setArtifactId(actifactId);
		mai.setDirName(dirName);
		mai.setPackageName(packageName);
		mai.setPackaging("war");
		return mai;
	}

	@Override
	public List<JavaFileBuildInfo> getJavaFileBuildInfos() {
		return new ArrayList<>();
	}

	@Override
	public Object getPomData() {
		DataMap dataMap = new DataMap();
		dataMap.put("parentVersion", this.parentVersion);
		dataMap.put("groupId", this.groupId);
		dataMap.put("artifactId", this.artifactId);
		dataMap.put("version", this.version);
		dataMap.put("modules", this.modules);
		dataMap.put("properties", this.properties);
		dataMap.put("frameWorkGroupId", this.frameWorkGroupId);
		dataMap.put("frameWorkVersion", this.frameWorkVersion);
		dataMap.put("frameWorkApiArtifactId", this.frameWorkApiArtifactId);
		dataMap.put("frameWorkServerArtifactId", this.frameWorkServerArtifactId);
		dataMap.put("frameWorkWebArtifactId", this.frameWorkWebArtifactId);
		dataMap.put("childApiAppArtifactIds", this.childApiAppArtifactIds);
		return dataMap;
	}

	@Override
	public String getProjectDirName() {
		return dirName == null ? artifactId : dirName;
	}

	@Override
	public String getPomTemplateResourceName() {
		return "maven-manager-pom.ftl";
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
