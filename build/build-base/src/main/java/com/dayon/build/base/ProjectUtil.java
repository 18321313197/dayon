package com.dayon.build.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import freemarker.template.Template;

public class ProjectUtil {
	public static void deleteFile(File file) {
		if (!file.exists())
			return;
		if (file.isFile()) {
			file.delete();
			return;
		}
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			deleteFile(files[i]);
		}
		file.delete();
	}

	private static File createDir(File file, String dirName) {
		if (file == null) {
			System.out.println("路径文件不能为空");
		}
		if (dirName == null || dirName.trim().isEmpty()) {
			System.out.println("文件夹的名称不能为空");
		}
		dirName = dirName.trim();

		if (file.exists()) {
			if (!file.isDirectory()) {
				System.out.println("路径文件不是文件夹 " + file.getPath());
				return null;
			}
		}

		file = new File(file.getPath() + "/" + dirName);
		if (file.exists()) {
			deleteFile(file);
			System.out.println("删除文件  : " + file.getPath());
		}
		file.mkdirs();
		System.out.println("创建文件夹: " + file.getPath());
		return file;
	}

	public static File createMavenProject(String outPath, MavenProjectBuildInfo projectBuildInfo) {

		String projectDirName = projectBuildInfo.getProjectDirName();
		File file = new File(outPath);
		System.out
				.println("------------------开始生成 > " + file.getPath() + "/" + projectDirName + "---------------------");

		file = createDir(file, projectDirName);
		if (file == null) {
			System.out.println("创建文件夹失败");
			return null;
		}
		URL url = Thread.currentThread().getContextClassLoader()
				.getResource(projectBuildInfo.getPomTemplateResourceName());
		String pomPath = url.getPath();
		System.out.println("获取模板文件 :" + pomPath);
		Reader reader = null;
		try {
			reader = new FileReader(pomPath);
		} catch (FileNotFoundException e) {
			System.out.println("获取模板文件失败 : " + pomPath);
			e.printStackTrace();
			return null;
		}

		// 创建一个模板对象
		System.out.println("生成pom文件 : " + file.getPath() + "/" + "pom.xml");
		try {
			Template t = new Template(null, reader, null);
			Writer writer = new FileWriter(file.getPath() + "/" + "pom.xml");
			t.process(projectBuildInfo, writer);
			System.out.println("生成pom文件成功");
		} catch (Exception e) {
			System.out.println("生成pom文件失败");
			e.printStackTrace();
			return null;
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		List<String> dirs = projectBuildInfo.getProjectDirectoryPaths();
		String path = file.getPath();
		for (String dir : dirs) {
			file = new File(path + "/" + dir);
			file.mkdirs();
			System.out.println("创包文件 : " + file.getPath());
		}
		System.out.println("------------------生成成功 : " + file.getPath() + "---------------------");
		return file;
	}

	public static void createMavenProject(String outPath, MavenManagerInfo mavenManagerInfo,
			Set<MavenAppInfo> mavenAppInfos) {
		for (MavenAppInfo mavenAppInfo : mavenAppInfos) {
			mavenManagerInfo.getModules().add(mavenAppInfo.getProjectDirName());
		}
		createMavenProject(outPath, mavenManagerInfo);
		for (MavenAppInfo mavenAppInfo : mavenAppInfos) {
			createMavenProject(outPath+"/"+mavenManagerInfo.getProjectDirName(), mavenAppInfo);
		}

	}

	public static void main(String[] args) throws Exception {
		MavenManagerInfo mp = new MavenManagerInfo("com.test.dayon", "mytest-parent","mytest");
		mp.getProperties().put("tomcat-version", "8.0.30");
		mp.getProperties().put("log4j-version", "2.8.2");
		Set<MavenAppInfo> mavenAppInfos=new HashSet<>();
		MavenAppInfo mavenAppInfo=mp.createChildMavenAppServiceInfo("mytest-api-auth", "authapi", "com.test.dayon.mytest.auth");
		mavenAppInfos.add(mavenAppInfo);
		mavenAppInfo=mp.createChildMavenAppServiceInfo("mytest-center-auth", "authcenter", "com.test.dayon.mytest.auth");
		mavenAppInfos.add(mavenAppInfo);
		mavenAppInfo=mp.createChildMavenAppServiceInfo("mytest-api-user", "userapi", "com.test.dayon.mytest.user");
		mavenAppInfos.add(mavenAppInfo);
		mavenAppInfo=mp.createChildMavenAppServiceInfo("mytest-center-user", "usercenter", "com.test.dayon.mytest.user");
		mavenAppInfos.add(mavenAppInfo);
		mavenAppInfo=mp.createChildMavenAppWebInfo("mytest-web-manage", "manage", "com.test.dayon.mytest.manage");
		mavenAppInfos.add(mavenAppInfo);
		mavenAppInfo=mp.createChildMavenAppWebInfo("mytest-web-member", "member", "com.test.dayon.mytest.member");
		mavenAppInfos.add(mavenAppInfo);
		createMavenProject("c:/test",mp,mavenAppInfos);
		
	}
}
