package com.dayon.framework.web.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.dayon.common.file.FileUtil;
import com.dayon.common.log4j.Log4jLogFactory;

public class InitListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 加载Log4j2SystemLogDriver
		try {
			Class.forName("com.dayon.common.log4j.Log4jSystemLogDriver");
			Log4jLogFactory.getLogger().info("加载com.dayon.common.log4j.Log4jSystemLogDriver");
		} catch (ClassNotFoundException e) {
			Log4jLogFactory.getLogger().error("加载 com.dayon.common.log4j.Log4jSystemLogDriver 异常", e);
		}

		// 缓存contextPath
		ServletContext context = sce.getServletContext();
		String contextPath = context.getContextPath();
		context.setAttribute("contextPath", contextPath);
		Log4jLogFactory.getLogger().info("缓存字符串 contextPath = " + contextPath);
		// 缓存application-attribute.properties信息
		Properties p = FileUtil.loadClassPathProperties("/application-usercfg.properties");
		if (p != null) {
			Map<Object, Object> usercfg = new HashMap<>(p);
			context.setAttribute("usercfg", usercfg);
			Log4jLogFactory.getLogger().info("缓存Map usercfg = " + usercfg);
		}
		
		Log4jLogFactory.getLogger().info("InitListener 初始化完成");

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		Log4jLogFactory.getLogger().info("InitListener 销毁完成");
	}

}
