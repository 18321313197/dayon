<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>${pom.groupId}</groupId>
	<artifactId>${pom.actifactId}</artifactId>
	<version>${pom.version}</version>
	<packaging>${pom.packaging}</packaging>
	<modules>
		<#list pom.modules as module>
		<module>module</module>
		</#list>
	</modules>
	<properties>
		<project.build.sourceEncoding>${pom.encoding}</project.build.sourceEncoding>
		<jdk.version>${pom.jdkVersion}</jdk.version>
		<#list pom.properties?keys as mkey>
		<${mkey}>${pom.properties[mkey]}</${mkey}>
		</#list>
	</properties>
	<build>
		<pluginManagement>
			<plugins>
				<plugin>
					<groupId>org.apache.maven.plugins</groupId>
					<artifactId>maven-compiler-plugin</artifactId>
					<version>3.5.1</version>
					<configuration>
						<source>${r'${jdk.version}'}</source> <!-- JDK 版本 -->
						<target>${r'${jdk.version}'}</target> <!-- 生成class 版本 -->
						<encoding>${r'${project.build.sourceEncoding}'}</encoding> <!-- 编码格式 -->
					</configuration>
				</plugin>
				<plugin>
					<groupId>org.apache.maven.plugins</groupId>
					<artifactId>maven-resources-plugin</artifactId>
					<version>2.6</version>
				</plugin>
				<plugin>
					<groupId>org.apache.maven.plugins</groupId>
					<artifactId>maven-war-plugin</artifactId>
					<version>2.6</version>
					<configuration>
						<warName>${r'${project.artifactId}-${project.version}'}</warName>
						<failOnMissingWebXml>false</failOnMissingWebXml>
						<attachClasses>true</attachClasses>
						<classesClassifier>api</classesClassifier>
					</configuration>
				</plugin>
			</plugins>
		</pluginManagement>
	</build>
	
</project>