package com.dayon.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtil {
	private ClassUtil() {
	}

	public static Set<Class<?>> loadPackageClasses(String packageName) throws Exception {
		Set<Class<?>> clcasses = new HashSet<>();
		Enumeration<URL> es = Thread.currentThread().getContextClassLoader()
				.getResources(packageName.replace(".", "/"));
		while (es.hasMoreElements()) {
			URL url = es.nextElement();
			String protocol = url.getProtocol();
			String path = url.getPath();
			if (protocol.equals("file")) {
				loadDirectoryClasses(clcasses, packageName, new File(path));
			} else if (protocol.equals("jar")) {
				loadJarClasses(clcasses, packageName, path.substring(5, path.length() - packageName.length() - 2));
			}
		}
		return clcasses;
	}

	private static void loadJarClasses(Set<Class<?>> clcasses, String packageName, String jarFilePath)
			throws Exception {
		JarFile jarFile = new JarFile(jarFilePath);
		String path = packageName.replace(".", "/") + "/";
		// 从此jar包 得到一个枚举类
		Enumeration<JarEntry> entries = jarFile.entries();
		while (entries.hasMoreElements()) {
			JarEntry jarEntry = entries.nextElement();
			String name = jarEntry.getName();
			if (name.endsWith(".class") && name.startsWith(path)) {
				String className = name.replace("/", ".").substring(0, name.length() - 6);
				Class<?> clazz = Class.forName(className);
				clcasses.add(clazz);
			}
		}
		jarFile.close();
	}

	public static Map<String, Object> hashInterfaceMethod(Set<Class<?>> classes) throws Exception {
		Map<String, Object> map = new HashMap<>();
		for (Class<?> clazz : classes) {
			hashInterfaceMethod(map, clazz);
		}
		return map;
	}

	private static void loadDirectoryClasses(Set<Class<?>> clcasses, String packageName, File dir) throws Exception {
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则:以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return file.isDirectory() || file.getName().endsWith(".class");
			}
		});
		StringBuilder sb = new StringBuilder();
		// 循环所有文件
		for (File file : dirfiles) {
			sb.delete(0, sb.length());
			if (file.isDirectory()) {
				sb.append(packageName).append(".").append(file.getName());
				loadDirectoryClasses(clcasses, sb.toString(), file);
			} else if (file.getName().endsWith(".class")) {
				// 如果是java类文件 去掉后面的.class 只留下类名
				String className = file.getName().substring(0, file.getName().length() - 6);
				sb.append(packageName).append(".").append(className);
				Class<?> clazz = Class.forName(sb.toString());
				clcasses.add(clazz);
			}
		}
	}

	private static void hashInterfaceMethod(Map<String, Object> map, Class<?> clazz) throws Exception {
		Object obj = clazz.newInstance();
		for (Class<?> iclass : clazz.getInterfaces()) {
			map.put(iclass.getName(), obj);
			for (Method method : iclass.getMethods()) {
				Method methodImpl = clazz.getMethod(method.getName(), method.getParameterTypes());
				map.put(method.toString(), methodImpl);
			}
		}
	}

	public static byte[] classToBytes(Object obj) throws Exception {
		if (obj == null) {
			return new byte[] {};
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		try {
			oos.writeObject(obj);
			return bos.toByteArray();
		} finally {
			bos.close();
			oos.close();
		}
	}

	public static Object bytesToClass(byte[] bytes) throws Exception {
		if (bytes == null || bytes.length == 0) {
			throw new Exception("参数：字节数组不能是空的");
		}
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = new ObjectInputStream(bis);
		try {
			return ois.readObject();
		} finally {
			bis.close();
			ois.close();
		}
	}
}
