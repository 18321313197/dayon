package com.dayon.build.base.info;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dayon.build.base.data.JavaFileBuildInfo;
import com.dayon.build.base.data.MavenProjectBuildInfo;
import com.dayon.common.base.model.DataMap;

public class MavenManagerInfo implements MavenProjectBuildInfo {
	private String groupId;
	private String artifactId;
	private String version = "1.0";
	private List<String> modules = new ArrayList<>();
	private Map<String, String> properties = new HashMap<String, String>();
	private String jdkVersion = "1.8";
	private String encoding = "UTF-8";
	private String mavenCompilerPluginVersion = "3.7.0";
	private String mavenResourcesPluginVersion = "2.7";
	private String mavenWarPluginVersion = "2.6";
	private String mavenJarPluginVersion = "3.0.2";
	private String tomcatVersion="8.0.30";

	private String dirName;

	public MavenManagerInfo(String groupId, String artifactId) {
		this.groupId = groupId;
		this.artifactId = artifactId;
	}

	public MavenManagerInfo(String groupId, String artifactId, String dirName) {
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.dirName = dirName;

	}

	public MavenAppInfo createChildMavenAppInfo(String actifactId) {
		MavenAppInfo mai = new MavenAppInfo();
		mai.setParentArtifactId(this.artifactId);
		mai.setParentGroupId(groupId);
		mai.setParentVersion(version);
		mai.setArtifactId(actifactId);
		return mai;
	}

	public MavenAppInfo createChildMavenAppServiceInfo(String actifactId, String dirName, String packageName) {
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

	public MavenAppInfo createChildMavenAppWebInfo(String actifactId, String dirName, String packageName) {
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
		dataMap.put("artifactId", this.getArtifactId());
		dataMap.put("encoding", this.getEncoding());
		dataMap.put("groupId", this.getGroupId());
		dataMap.put("jdkVersion", this.getJdkVersion());
		dataMap.put("tomcatVersion", this.getTomcatVersion());
		dataMap.put("mavenCompilerPluginVersion", this.getMavenCompilerPluginVersion());
		dataMap.put("mavenJarPluginVersion", this.getMavenJarPluginVersion());
		dataMap.put("mavenResourcesPluginVersion", this.getMavenResourcesPluginVersion());
		dataMap.put("mavenWarPluginVersion", this.getMavenWarPluginVersion());
		dataMap.put("modules", this.getModules());
		dataMap.put("properties", this.getProperties());
		dataMap.put("version", this.getVersion());
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

	public String getJdkVersion() {
		return jdkVersion;
	}

	public void setJdkVersion(String jdkVersion) {
		this.jdkVersion = jdkVersion;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getMavenCompilerPluginVersion() {
		return mavenCompilerPluginVersion;
	}

	public void setMavenCompilerPluginVersion(String mavenCompilerPluginVersion) {
		this.mavenCompilerPluginVersion = mavenCompilerPluginVersion;
	}

	public String getMavenResourcesPluginVersion() {
		return mavenResourcesPluginVersion;
	}

	public void setMavenResourcesPluginVersion(String mavenResourcesPluginVersion) {
		this.mavenResourcesPluginVersion = mavenResourcesPluginVersion;
	}

	public String getMavenWarPluginVersion() {
		return mavenWarPluginVersion;
	}

	public void setMavenWarPluginVersion(String mavenWarPluginVersion) {
		this.mavenWarPluginVersion = mavenWarPluginVersion;
	}

	public String getMavenJarPluginVersion() {
		return mavenJarPluginVersion;
	}

	public void setMavenJarPluginVersion(String mavenJarPluginVersion) {
		this.mavenJarPluginVersion = mavenJarPluginVersion;
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

	public String getTomcatVersion() {
		return tomcatVersion;
	}

	public void setTomcatVersion(String tomcatVersion) {
		this.tomcatVersion = tomcatVersion;
	}

}
