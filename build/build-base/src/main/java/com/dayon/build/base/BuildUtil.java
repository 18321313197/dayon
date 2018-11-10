package com.dayon.build.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import freemarker.template.Template;

public class BuildUtil {

	private static void deleteFile(File file) {
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

	private static boolean createDirectory(String dirPath) {
		File file = new File(dirPath);
		if (file.exists()) {
			deleteFile(file);
		}
		return file.mkdirs();
	}

	private static boolean createFile(String path, String fileName, String templateResourceName, Object data) {
		File file = new File(path);
		if (!file.exists()) {
			return false;
		}
		if (!file.isDirectory()) {
			return false;
		}
		file = new File(path + "/" + fileName);

		if (file.exists()) {
			deleteFile(file);
		}
		URL url = Thread.currentThread().getContextClassLoader().getResource(templateResourceName);

		Reader reader = null;
		Writer writer = null;
		try {
			reader = new FileReader(url.getPath());
			Template t = new Template(null, reader, null);
			writer = new FileWriter(file);
			t.process(data, writer);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
			if (writer != null) {
				try {
					writer.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		}

	}

	public static boolean buildMavenProject(String outPath, MavenProjectBuildInfo mavenProjectBuildInfo) {

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 开始生成 " + mavenProjectBuildInfo.getProjectDirName()
				+ " >>>>>>>>>>>>>>>>>>>>>");
		String path = outPath + "/" + mavenProjectBuildInfo.getProjectDirName();
		if (!createDirectory(path)) {
			System.err.println("生成文件夹失败 : " + path);
			return false;
		}
		System.out.println("生成文件夹 : " + path);
		if (!createFile(path, "pom.xml", mavenProjectBuildInfo.getPomTemplateResourceName(),
				mavenProjectBuildInfo.getPomData())) {
			System.err.println("生成pom.xml失败 : " + path + "/pom.xml");
			return false;
		}
		System.out.println("生成pom.xml : " + path + "/pom.xml");
		List<String> dirs = mavenProjectBuildInfo.getProjectDirectoryPaths();

		for (String dir : dirs) {
			String dirPath = path + "/" + dir;
			if (createDirectory(dirPath)) {
				System.out.println("生成文件夹 : " + dirPath);
			} else {
				System.err.println("生成文件夹失败 : " + dirPath);
				return false;
			}
		}

		List<JavaFileBuildInfo> JavaFileBuildInfos = mavenProjectBuildInfo.getJavaFileBuildInfos();
		for (JavaFileBuildInfo javaFileBuildInfo : JavaFileBuildInfos) {
			String dirPath = path + "/" + javaFileBuildInfo.getPackageDirName();

			if (!createDirectory(dirPath)) {
				System.err.println("生成文件夹失败 : " + dirPath);
				return false;
			}
			System.out.println("生成文件夹 : " + dirPath);

			for (Entry<String, Object> en : javaFileBuildInfo.getFileNameAndData().entrySet()) {

				if (!createFile(dirPath, en.getKey(), javaFileBuildInfo.getTemplateResourceName(), en.getValue())) {
					System.err.println("生成java文件失败 : " + dirPath + "/" + en.getKey());
					return false;
				}
				System.out.println("生成java文件 : " + dirPath + "/" + en.getKey());
			}

		}

		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< 生成成功  " + mavenProjectBuildInfo.getProjectDirName()
				+ " <<<<<<<<<<<<<<<<<<<<<<");

		return true;
	}

	public static boolean buildMavenProject(String outPath, MavenManagerInfo mavenManagerInfo,
			Set<MavenAppInfo> mavenAppInfos) {
		mavenManagerInfo.getModules().clear();
		for (MavenAppInfo mavenAppInfo : mavenAppInfos) {
			mavenManagerInfo.getModules().add(mavenAppInfo.getProjectDirName());
		}
		buildMavenProject(outPath, mavenManagerInfo);
		for (MavenAppInfo mavenAppInfo : mavenAppInfos) {
			buildMavenProject(outPath + "/" + mavenManagerInfo.getProjectDirName(), mavenAppInfo);
		}
		return true;
	}

	public static void main(String[] args) {
		
		String buildPath = "c:/";
		String[] dataBaseInfo=new String[]{"127.0.0.1:3306", "root", "12345qwe", "b2b2c"};
		String[] appManager = new String[] { "com.test.dayon", "mytest-parent", "mytest" };
		String[][] appManagerProperties = new String[][] { new String[] { "framework.version", "1.0" } };

		String[][] appApis = new String[][] {
				new String[] { "mytest-auth-api", "authapi", "com.test.dayon.mytest.auth.api" ,"t_qx_%"},
				new String[] { "mytest-user-api", "userapi", "com.test.dayon.mytest.user.api" ,"t_yh_%"} };
		String[][] appCenters = new String[][] {
				new String[] { "mytest-auth-center", "authcenter", "com.test.dayon.mytest.auth.center" ,appApis[0][2]},
				new String[] { "mytest-user-center", "usercenter", "com.test.dayon.mytest.user.center" ,appApis[1][2]} };
		String[][] appWebs = new String[][] {
				new String[] { "mytest-manage-web", "manageweb", "com.test.dayon.mytest.manage.web" },
				new String[] { "mytest-member-web", "memberweb", "com.test.dayon.mytest.member.web" }, };
		
				
				
		MySqlDBPool mySqlDBPool = new MySqlDBPool(dataBaseInfo[0], dataBaseInfo[1], dataBaseInfo[2], dataBaseInfo[3]);
		Map<String,EntityInfo> enittyPckageEntityInfoMap=new HashMap<>();
		MavenManagerInfo managerInfo = new MavenManagerInfo(appManager[0], appManager[1], appManager[2]);
		Set<MavenAppInfo> mavenAppInfos = new HashSet<>();
		for (String[] appManagerPropertie : appManagerProperties) {
			managerInfo.getProperties().put(appManagerPropertie[0], appManagerPropertie[1]);
		}

		
		for (String[] appApi : appApis) {

			MavenAppInfo appApiInfo = managerInfo.createChildMavenAppServiceInfo(appApi[0], appApi[1], appApi[2]);
			Collection<Table> tables=mySqlDBPool.getMySqlTablesInfo(appApi[3]);

			EntityInfo ei = new EntityInfo(appApiInfo.getPackageName() + ".entity", tables);
			
			enittyPckageEntityInfoMap.put(appApi[2], ei);
			
			appApiInfo.getJavaFileBuildInfos().add(ei);

			Dependencie dependencie = new Dependencie();
			dependencie.setGroupId("com.dayon.framework");
			dependencie.setArtifactId("framework-api");
			dependencie.setScope("compile");
			dependencie.setVersion("${framework.version}");
			appApiInfo.getDependencies().add(dependencie);
			mavenAppInfos.add(appApiInfo);

		}

		for (String[] appCenter : appCenters) {
			MavenAppInfo appCenterInfo = managerInfo.createChildMavenAppServiceInfo(appCenter[0], appCenter[1],
					appCenter[2]);
			EntityInfo entityInfo = enittyPckageEntityInfoMap.get(appCenter[3]);
			MapperInfo mi =new MapperInfo(appCenterInfo.getPackageName()+".mapper", entityInfo.getPackageName(),entityInfo.getTables());
			appCenterInfo.getJavaFileBuildInfos().add(mi);
			
			Dependencie dependencie = new Dependencie();
			dependencie.setGroupId("com.dayon.framework");
			dependencie.setArtifactId("framework-center");
			dependencie.setScope("compile");
			dependencie.setVersion("${framework.version}");
			appCenterInfo.getDependencies().add(dependencie);
			
			for (String[] appApi : appApis) {
				dependencie = new Dependencie();
				dependencie.setGroupId("${project.groupId}");
				dependencie.setArtifactId(appApi[0]);
				dependencie.setScope("compile");
				dependencie.setVersion("${project.version}");
				appCenterInfo.getDependencies().add(dependencie);
			}
			mavenAppInfos.add(appCenterInfo);
		}
		
		for (String[] appWeb : appWebs) {
			MavenAppInfo appWebInfo = managerInfo.createChildMavenAppWebInfo(appWeb[0], appWeb[1],
					appWeb[2]);
			
			Dependencie dependencie = new Dependencie();
			dependencie.setGroupId("com.dayon.framework");
			dependencie.setArtifactId("framework-web");
			dependencie.setVersion("${framework.version}");
			dependencie.setType("war");
			dependencie.setScope("compile");
			
			appWebInfo.getDependencies().add(dependencie);
			
			dependencie = new Dependencie();
			dependencie.setGroupId("com.dayon.framework");
			dependencie.setArtifactId("framework-web");
			dependencie.setVersion("${framework.version}");
			dependencie.setScope("provided");
			dependencie.setType("jar");
			dependencie.setClassifier("api");
			
			appWebInfo.getDependencies().add(dependencie);
			
			for (String[] appApi : appApis) {
				dependencie = new Dependencie();
				dependencie.setGroupId("${project.groupId}");
				dependencie.setArtifactId(appApi[0]);
				dependencie.setScope("compile");
				dependencie.setVersion("${project.version}");
				appWebInfo.getDependencies().add(dependencie);
			}
			mavenAppInfos.add(appWebInfo);
		}

		buildMavenProject(buildPath, managerInfo, mavenAppInfos);
	}
}
