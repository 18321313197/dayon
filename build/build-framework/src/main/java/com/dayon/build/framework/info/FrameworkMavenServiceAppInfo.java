package com.dayon.build.framework.info;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.build.base.data.Dependencie;
import com.build.base.data.JavaFileBuildInfo;
import com.build.base.data.MavenProjectBuildInfo;
import com.dayon.common.base.DataMap;

public class FrameworkMavenServiceAppInfo implements MavenProjectBuildInfo {
	private String parentGroupId;
	private String parentArtifactId;
	private String parentVersion;
	private String artifactId;
	private String packageName;
	private String dirName;

	private List<Dependencie> dependencies = new ArrayList<>();

	private List<JavaFileBuildInfo> javaFileBuildInfos = new ArrayList<>();

	public FrameworkMavenServiceAppInfo() {

	}

	@Override
	public List<JavaFileBuildInfo> getJavaFileBuildInfos() {

		return javaFileBuildInfos;
	}

	@Override
	public Object getPomData() {
		DataMap dataMap = new DataMap();
		dataMap.put("artifactId", this.getArtifactId());
		dataMap.put("dependencies", this.getDependencies());
		dataMap.put("parentArtifactId", this.getParentArtifactId());
		dataMap.put("parentGroupId", this.getParentGroupId());
		dataMap.put("parentVersion", this.getParentVersion());
		return dataMap;
	}

	@Override
	public String getProjectDirName() {
		return dirName != null ? dirName : artifactId;
	}

	@Override
	public String getPomTemplateResourceName() {
		return "framework-maven-service-app-pom.ftl";
	}

	@Override
	public List<String> getProjectDirectoryPaths() {
		List<String> dirs = new ArrayList<>();
		String basePackagedir = packageName == null ? parentGroupId.replace(".", "/") : packageName.replace(".", "/");
		dirs.add("src/test/resources");
		dirs.add("src/main/resources");
		dirs.add("src/test/java/");
		dirs.add("src/main/java/" + basePackagedir);

		return dirs;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getParentGroupId() {
		return parentGroupId;
	}

	public void setParentGroupId(String parentGroupId) {
		this.parentGroupId = parentGroupId;
	}

	public String getParentArtifactId() {
		return parentArtifactId;
	}

	public void setParentArtifactId(String parentArtifactId) {
		this.parentArtifactId = parentArtifactId;
	}

	public String getParentVersion() {
		return parentVersion;
	}

	public void setParentVersion(String parentVersion) {
		this.parentVersion = parentVersion;
	}

	public List<Dependencie> getDependencies() {
		return dependencies;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	@Override
	public Map<String, File> getResourcesFileMap() {
		Map<String, File> resourcesFileMap = new HashMap<>();
		File file = new File(Thread.currentThread().getContextClassLoader()
				.getResource("copy/service/framework-service.properties").getPath());
		resourcesFileMap.put("src/main/resources/framework-service.properties", file);

		file = new File(
				Thread.currentThread().getContextClassLoader().getResource("copy/service/log4j2.xml").getPath());
		resourcesFileMap.put("src/main/resources/log4j2.xml", file);

		file = new File(Thread.currentThread().getContextClassLoader().getResource("copy/service/spring-context.xml")
				.getPath());
		resourcesFileMap.put("src/main/resources/spring-context.xml", file);

		file = new File(
				Thread.currentThread().getContextClassLoader().getResource("copy/service/spring-dubbo.xml").getPath());
		resourcesFileMap.put("src/main/resources/spring-dubbo.xml", file);

		file = new File(Thread.currentThread().getContextClassLoader().getResource("copy/service/spring-mybatis.xml")
				.getPath());
		resourcesFileMap.put("src/main/resources/spring-mybatis.xml", file);
		return resourcesFileMap;
	}

}
