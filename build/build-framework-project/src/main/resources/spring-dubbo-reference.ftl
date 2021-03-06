<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
	xsi:schemaLocation="
		http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://code.alibabatech.com/schema/dubbo        
   		http://code.alibabatech.com/schema/dubbo/dubbo.xsd">
	<!-- 消费者引入 -->
	<#list serviceTypeInfos as serviceTypeInfo>
	<dubbo:reference id="${serviceTypeInfo.javaName}" interface="${serviceTypeInfo.name}" />
	</#list>
</beans>