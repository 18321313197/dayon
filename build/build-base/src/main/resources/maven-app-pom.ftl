<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>${parentGroupId}</groupId>
		<artifactId>${parentArtifactId}</artifactId>
		<version>${parentVersion}</version>
	</parent>
	<artifactId>${artifactId}</artifactId>
	<packaging>${packaging}</packaging>
	<dependencies>
		<#list dependencies as dep>
		<dependency>
			<groupId>${dep.groupId}</groupId>
			<artifactId>${dep.artifactId}</artifactId>
			<version>${dep.version}</version>
		<#if dep.scope??>
			<scope>${dep.scope}</scope>
		</#if>
		<#if dep.type??>
			<type>${dep.type}</type>
		</#if>
		<#if dep.classifier??>
			<classifier>${dep.classifier}</classifier>
		</#if>
		</dependency>
		</#list>
	</dependencies>
</project>