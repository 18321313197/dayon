package com.dayon.common.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dayon.common.other.ClassUtil;


@WebFilter(filterName = "centerFilter", urlPatterns = "/*")
public class CenterFilter implements Filter {
	private Map<String, Method> formMethods = new HashMap<String, Method>();
	private Map<String, Method> intercept = new HashMap<String, Method>();

	@Override
	public void destroy() {

		try {
			Method method = this.intercept.get("onExit");
			if (method != null) {
				method.invoke(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		Method method = this.formMethods.get(req.getServletPath());
		if (method != null) {

			boolean isdo = true;

			Method onBefore = this.intercept.get("onBefore");
			if (onBefore != null) {
				try {
					isdo = (Boolean) onBefore.invoke(null, req, res);
				} catch (Exception e) {
					isdo = false;
					e.printStackTrace();
				}
			}

			if (isdo) {
				try {
					method.invoke(null, req, res);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				res.getWriter().print("have no right to access");
			}

			Method onAfter = this.intercept.get("onAfter");
			if (onAfter != null) {
				try {
					onAfter.invoke(null, req, res);
				} catch (Exception e) {
					e.printStackTrace();

				}
			}

			return;
		}

		chain.doFilter(req, res);

	}

	@Override
	public void init(FilterConfig config) throws ServletException {

		try {
			String s = Thread.currentThread().getContextClassLoader().getResource("/centerFilterConfig.xml").getPath();

			CenterFilterConfig cfg = new CenterFilterConfig(s);

			for (String id : cfg.getWebPackages().keySet()) {
				String packageName = cfg.getWebPackages().get(id);
				Set<Class<?>> classes = ClassUtil.loadPackageClasses(packageName);
				for (Class<?> clazz : classes) {
					for (Method method : clazz.getDeclaredMethods()) {
						if (Modifier.isStatic(method.getModifiers())) {
							Class<?>[] mpts = method.getParameterTypes();
							if (mpts.length == 2 && mpts[0] == HttpServletRequest.class
									&& mpts[1] == HttpServletResponse.class) {

								String className = clazz.getSimpleName();

								if (className.endsWith(cfg.getTail())) {
									className = className.substring(0, className.length() - cfg.getTail().length());
								}

								StringBuilder formkey = new StringBuilder("/").append(id).append("/");

								if ("".equals(className)) {
									formkey.append(method.getName());
									this.formMethods.put(formkey.toString(), method);
									this.formMethods.put(formkey.toString() + "/", method);
								} else {
									formkey.append(className).append("/").append(method.getName());
									this.formMethods.put(formkey.toString(), method);
									this.formMethods.put(formkey.toString() + "/", method);
									int num = formkey.indexOf("/", 1) + 1;
									formkey.setCharAt(num, Character.toLowerCase(formkey.charAt(num)));
									this.formMethods.put(formkey.toString(), method);
									this.formMethods.put(formkey.toString() + "/", method);
								}

							}
						}
					}
				}

			}

			if (cfg.getInterceptClassName() != null) {

				Class<?> clazz = Class.forName(cfg.getInterceptClassName());
				Method onInit = clazz.getMethod("onInit", ServletContext.class, Map.class);
				Method onExit = clazz.getMethod("onExit");
				Method onBefore = clazz.getMethod("onBefore", HttpServletRequest.class, HttpServletResponse.class);
				Method onAfter = clazz.getMethod("onAfter", HttpServletRequest.class, HttpServletResponse.class);

				if (Modifier.isStatic(onInit.getModifiers()) && Modifier.isStatic(onExit.getModifiers())
						&& Modifier.isStatic(onBefore.getModifiers()) && Modifier.isStatic(onAfter.getModifiers())) {
					this.intercept.put("onExit", onExit);
					this.intercept.put("onBefore", onBefore);
					this.intercept.put("onAfter", onAfter);
					this.intercept.put("onInit", onInit);
					onInit.invoke(null, config.getServletContext(), cfg.getInterceptParams());
				} else {
					System.err.println("intercept init error:" + cfg.getInterceptClassName()
							+ " lack of static method : onInit or onExit or onBefore or onAfter");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
