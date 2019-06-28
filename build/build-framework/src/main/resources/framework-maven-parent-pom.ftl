<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>com.dayon</groupId>
		<artifactId>dayon-parent</artifactId>
		<version>1.0.0</version>
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
		<dayon-version>${r'${parent.version}'}</dayon-version>
		<apache-tomcat.version>8.0.30</apache-tomcat.version>
		<javax-servlet-jstl.version>1.2</javax-servlet-jstl.version>
		<spring-framework.version>4.3.13.RELEASE</spring-framework.version>
		<spring-session-redis.version>1.3.1.RELEASE</spring-session-redis.version>
		<jackson-databind.version>2.9.5</jackson-databind.version>
		<mysql-connector.version>5.1.35</mysql-connector.version>
		<alibaba-druid.version>1.1.10</alibaba-druid.version>
		<redis-jedis.version>2.9.0</redis-jedis.version>
		<mybatis.version>3.4.1</mybatis.version>
		<mybatis-spring.version>1.3.0</mybatis-spring.version>
		<apache-log4j.version>2.8.2</apache-log4j.version>
		<freemarker.version>2.3.23</freemarker.version>
		<org-aspectj.version>1.9.2</org-aspectj.version>
		<alibaba-dubbo.version>2.5.3</alibaba-dubbo.version>
		<zkclient-101tec.version>0.10</zkclient-101tec.version>
		
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