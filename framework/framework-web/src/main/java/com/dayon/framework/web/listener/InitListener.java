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
			Log4jLogFactory.getLogger().info("loading com.dayon.common.logger.Log4j2SystemLogDriver class");
			Class.forName("com.dayon.common.logger.Log4j2SystemLogDriver");
			Log4jLogFactory.getLogger().info("loading com.dayon.common.logger.Log4j2SystemLogDriver class success");
		} catch (ClassNotFoundException e) {
			Log4jLogFactory.getLogger().error("loading com.dayon.common.logger.Log4j2SystemManage class error", e);
		}

		// 缓存contextPath
		Log4jLogFactory.getLogger().info("cache application attribute information");
		ServletContext context = sce.getServletContext();
		String contextPath = context.getContextPath();
		context.setAttribute("contextPath", contextPath);
		Log4jLogFactory.getLogger().info("cache String contextPath = " + contextPath);
		// 缓存application-attribute.properties信息
		Properties p = ConfigUtil.loadClassPathProperties("/application-cfgattr.properties");
		if (p != null) {
			Map<Object, Object> cfgattr = new HashMap<>(p);
			context.setAttribute("cfgattr", cfgattr);
			Log4jLogFactory.getLogger().info("cache Map cfgattr = " + cfgattr);
		}
		Log4jLogFactory.getLogger().info("cache application attribute information success");

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
