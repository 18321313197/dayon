package com.dayon.build.framework.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.build.base.data.JavaFileBuildInfo;
import com.build.base.data.MavenProjectBuildInfo;
import com.dayon.build.framework.info.FrameworkMavenWebAppInfo;
import com.dayon.build.framework.info.FrameworkMavenParentInfo;
import com.dayon.common.util.FileUtil;

import freemarker.template.Template;

public class BuildUtil {

	private BuildUtil() {
	}

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

	private static boolean createDirectory(String dirPath, boolean isNew) {
		File file = new File(dirPath);

		if (file.exists()) {
			if (isNew) {
				deleteFile(file);
				return file.mkdirs();
			}
			return true;
		} else {
			return file.mkdirs();
		}

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
		if (!createDirectory(path, true)) {
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
			if (createDirectory(dirPath, true)) {
				System.out.println("生成文件夹 : " + dirPath);
			} else {
				System.err.println("生成文件夹失败 : " + dirPath);
				return false;
			}
		}
		Map<String, File> sf = mavenProjectBuildInfo.getResourcesFileMap();
		for (String pa : sf.keySet()) {
			try {
				FileUtil.copy(path + "/" + pa, sf.get(pa));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<JavaFileBuildInfo> JavaFileBuildInfos = mavenProjectBuildInfo.getJavaFileBuildInfos();
		for (JavaFileBuildInfo javaFileBuildInfo : JavaFileBuildInfos) {
			String dirPath = path + "/" + javaFileBuildInfo.getResourceDirName();

			if (!createDirectory(dirPath, false)) {
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

		Map<String, File> map = mavenProjectBuildInfo.getResourcesFileMap();
		for (Entry<String, File> item : map.entrySet()) {
			try {
				FileUtil.copy(path + "/" + item.getKey(), item.getValue());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< 生成成功  " + mavenProjectBuildInfo.getProjectDirName()
				+ " <<<<<<<<<<<<<<<<<<<<<<");

		return true;
	}

	public static boolean buildMavenProject(String outPath, MavenProjectBuildInfo mavenManagerInfo,
			Collection<MavenProjectBuildInfo> mavenAppInfos) {
		buildMavenProject(outPath, mavenManagerInfo);
		for (MavenProjectBuildInfo mavenAppInfo : mavenAppInfos) {
			buildMavenProject(outPath + "/" + mavenManagerInfo.getProjectDirName(), mavenAppInfo);
		}
		return true;
	}

}
