package com.dayon.build.base.info;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dayon.build.base.data.Dependencie;
import com.dayon.build.base.data.JavaFileBuildInfo;
import com.dayon.build.base.data.MavenProjectBuildInfo;
import com.dayon.common.base.model.DataMap;

public class MavenAppInfo implements MavenProjectBuildInfo {
	private String parentGroupId;
	private String parentArtifactId;
	private String parentVersion;
	private String parentRelativePath;
	private String artifactId;
	private String packaging;
	private String packageName;
	private String dirName;
	private Map<String, File> resourcesFileMap = new HashMap<>();

	private List<Dependencie> dependencies = new ArrayList<>();

	private List<JavaFileBuildInfo> javaFileBuildInfos = new ArrayList<>();

	public MavenAppInfo() {

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
		dataMap.put("packaging", this.getPackaging());
		dataMap.put("parentArtifactId", this.getParentArtifactId());
		dataMap.put("parentGroupId", this.getParentGroupId());
		dataMap.put("parentRelativePath", this.getParentRelativePath());
		dataMap.put("parentVersion", this.getParentVersion());
		return dataMap;
	}

	@Override
	public String getProjectDirName() {
		return dirName != null ? dirName : artifactId;
	}

	@Override
	public String getPomTemplateResourceName() {
		return "maven-app-pom.ftl";
	}

	@Override
	public List<String> getProjectDirectoryPaths() {
		List<String> dirs = new ArrayList<>();
		String basePackagedir = packageName == null ? parentGroupId.replace(".", "/") : packageName.replace(".", "/");
		dirs.add("src/test/resources");
		dirs.add("src/main/resources");
		dirs.add("src/test/java/");
		dirs.add("src/main/java/" + basePackagedir);

		if ("war".equals(packaging)) {
			dirs.add("src/main/webapp/WEB-INF/templates");
			dirs.add("src/main/webapp/static/css");
			dirs.add("src/main/webapp/static/html");
			dirs.add("src/main/webapp/static/img");
			dirs.add("src/main/webapp/static/js");

			File file = new File(Thread.currentThread().getContextClassLoader()
					.getResource("copy/application-usercfg.properties").getPath());
			resourcesFileMap.put("src/main/resources/application-usercfg.properties", file);
			
			file = new File(Thread.currentThread().getContextClassLoader()
					.getResource("copy/index.html").getPath());
			resourcesFileMap.put("src/main/webapp/WEB-INF/templates/index.html", file);
		}
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

	public String getParentRelativePath() {
		return parentRelativePath;
	}

	public void setParentRelativePath(String parentRelativePath) {
		this.parentRelativePath = parentRelativePath;
	}

	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}

	public List<Dependencie> getDependencies() {
		return dependencies;
	}

	public Dependencie createDependencie(String scope, String classifier) {
		Dependencie dependencie = new Dependencie();
		dependencie.setArtifactId(artifactId);
		dependencie.setGroupId(parentGroupId);
		dependencie.setClassifier(classifier);
		dependencie.setScope(scope);
		dependencie.setVersion(parentVersion);
		return dependencie;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	@Override
	public Map<String, File> getResourcesFileMap() {

		return resourcesFileMap;
	}

}
