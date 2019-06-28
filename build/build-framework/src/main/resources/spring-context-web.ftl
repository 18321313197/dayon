<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="
		http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        ">

    <!-- 设置使用注解和扫描包 -->
	<context:component-scan base-package="${componentScanBasePackage}" />
	
	${r'<!-- Spring的xml配置文件中，可以通过${属性名}使用properties文件配置的值 -->'}
	${r'<!-- 可以使用@Value("${属性名}")注解读取properties文件配置的值，再给字段赋值 -->'}
	<context:property-placeholder location="classpath:framwork-web.properties"/>
	<import resource="classpath:spring-mvc.xml"/>
	<import resource="classpath:spring-dubbo.xml"/>
</beans>