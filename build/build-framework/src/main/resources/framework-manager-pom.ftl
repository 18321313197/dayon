<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>com.dayon</groupId>
		<artifactId>dayon-parent</artifactId>
		<version>1.0</version>
	</parent>
	<groupId>${groupId}</groupId>
	<artifactId>${artifactId}</artifactId>
	<version>${version}</version>
	<packaging>pom</packaging>
	<modules>
		<#list modules as module>
		<module>${module}</module>
		</#list>
	</modules>
	<properties>
		<!-- 用户依赖版本变量 -->
		<#list properties?keys as mkey>
		<${mkey}>${properties[mkey]}</${mkey}>
		</#list>
	</properties>
	<dependencyManagement>
		<dependencies>
			<#list childApiAppArtifactIds as childApiAppArtifactId>
			<dependency>
				<groupId>${r'${project.groupId}'}</groupId>
				<artifactId>${childApiAppArtifactId}</artifactId>
				<version>${r'${project.version}'}</version>
				<scope>compile</scope>
			</dependency>
			</#list>
		</dependencies>
	</dependencyManagement>
</project>