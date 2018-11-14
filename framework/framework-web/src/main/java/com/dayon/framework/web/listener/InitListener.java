package com.dayon.framework.web.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.dayon.common.logger.Log4jLogFactory;
import com.dayon.common.other.ConfigUtil;

public class InitListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 加载Log4j2SystemLogDriver
		try {
			Log4jLogFactory.getLogger().info("加载 com.dayon.common.logger.Log4jSystemLogDriver");
			Class.forName("com.dayon.common.logger.Log4jSystemLogDriver");
		} catch (ClassNotFoundException e) {
			Log4jLogFactory.getLogger().error("加载 com.dayon.common.logger.Log4jSystemLogDriver 异常", e);
		}

		// 缓存contextPath
		Log4jLogFactory.getLogger().info("缓存 application attribute:");
		ServletContext context = sce.getServletContext();
		String contextPath = context.getContextPath();
		context.setAttribute("contextPath", contextPath);
		Log4jLogFactory.getLogger().info("缓存字符串 contextPath = " + contextPath);
		// 缓存application-attribute.properties信息
		Properties p = ConfigUtil.loadClassPathProperties("/application-cfgattr.properties");
		if (p != null) {
			Map<Object, Object> cfgattr = new HashMap<>(p);
			context.setAttribute("cfgattr", cfgattr);
			Log4jLogFactory.getLogger().info("缓存Map cfgattr = " + cfgattr);
		}
		
		Log4jLogFactory.getLogger().info("InitListener 初始化完成");

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		Log4jLogFactory.getLogger().info("InitListener 销毁完成");
	}

}
