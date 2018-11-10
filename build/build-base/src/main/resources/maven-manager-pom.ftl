<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
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
		<project.build.sourceEncoding>${encoding}</project.build.sourceEncoding>
		<jdk.version>${jdkVersion}</jdk.version>
		<#list properties?keys as mkey>
		<${mkey}>${properties[mkey]}</${mkey}>
		</#list>
	</properties>
	<build>
		<pluginManagement>
			<plugins>
				<plugin>
					<groupId>org.apache.maven.plugins</groupId>
					<artifactId>maven-compiler-plugin</artifactId>
					<version>${mavenCompilerPluginVersion}</version>
					<configuration>
						<source>${r'${jdk.version}'}</source> <!-- JDK 版本 -->
						<target>${r'${jdk.version}'}</target> <!-- 生成class 版本 -->
						<encoding>${r'${project.build.sourceEncoding}'}</encoding> <!-- 编码格式 -->
					</configuration>
				</plugin>
				<plugin>
					<groupId>org.apache.maven.plugins</groupId>
					<artifactId>maven-resources-plugin</artifactId>
					<version>${mavenResourcesPluginVersion}</version>
				</plugin>
				<plugin>
					<groupId>org.apache.maven.plugins</groupId>
					<artifactId>maven-war-plugin</artifactId>
					<version>${mavenWarPluginVersion}</version>
					<configuration>
						<warName>${r'${project.artifactId}-${project.version}'}</warName>
						<failOnMissingWebXml>false</failOnMissingWebXml>
						<attachClasses>true</attachClasses>
						<classesClassifier>api</classesClassifier>
					</configuration>
				</plugin>
				<plugin>
					<groupId>org.apache.maven.plugins</groupId>
					<artifactId>maven-jar-plugin</artifactId>
					<version>${mavenJarPluginVersion}</version>
				</plugin>
			</plugins>
		</pluginManagement>
	</build>
	
</project>