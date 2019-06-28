<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
	version="3.1">

 	<!--WEB初始化Listenner 优先级最高-->
	<listener>
		<listener-class>${initListenerTypeInfo}</listener-class>
	</listener>
	<!-- filter -->
	<filter>
		<filter-name>encode</filter-name>
		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
		<async-supported>true</async-supported> <!-- 异步 -->
		<init-param>
			<param-name>encoding</param-name>
			<param-value>UTF-8</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>encode</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>

	<!-- servlet -->
	<servlet>
		<servlet-name>springmvc</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>classpath:spring-context.xml</param-value>
		</init-param>
		<load-on-startup>0</load-on-startup> 
	</servlet>
	<servlet-mapping>
		<servlet-name>springmvc</servlet-name>
		<url-pattern>/</url-pattern>
		<url-pattern>/index</url-pattern>
	</servlet-mapping>

	 <servlet-mapping>
      	<servlet-name>default</servlet-name>
      	<url-pattern>/static/*</url-pattern>
      	<url-pattern>/favicon.ico</url-pattern>
 	 </servlet-mapping>
	 <welcome-file-list>
	    <welcome-file>index</welcome-file>
	 </welcome-file-list>

</web-app>
