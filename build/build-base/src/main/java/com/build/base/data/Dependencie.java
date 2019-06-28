package com.build.base.data;

public class Dependencie {
	private String groupId;
	private String artifactId;
	private String version;
	
	private String scope;
	private String classifier;
	private String type;

	public Dependencie() {
	}

	public Dependencie(String groupId, String artifactId, String version, String scope,String type, String classifier) {
		super();
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.version = version;
		this.scope = scope;
		this.classifier = classifier;
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

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getClassifier() {
		return classifier;
	}

	public void setClassifier(String classifier) {
		this.classifier = classifier;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
