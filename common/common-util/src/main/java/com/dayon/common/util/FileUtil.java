package com.dayon.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileUtil {

	

	public static void copy(String pathName, File cpFile) throws Exception {

		if (!cpFile.isDirectory()) {
			copyFile(pathName, cpFile, true);
			return;
		}
		File file = new File(pathName);
		if (!file.exists()) {
			file.mkdirs();
		} else {
			deleteFile(file);
			file.mkdirs();
		}
		File[] files = cpFile.listFiles();
		for (File f : files) {
			copy(file.getPath() + "/" + f.getName(), f);
		}

	}

	public static void copyFile(String pathName, File cpFile, boolean isCover) throws Exception {

		if (pathName == null) {
			throw new Exception("参数：文件路径不能为空");
		}
		if (cpFile == null || !cpFile.exists()) {
			throw new Exception("参数：文件不是为空或文件不存在");
		}
		File file = new File(pathName);
		if (file.isDirectory() || file.isHidden()) {
			throw new Exception("目标文件" + file.getPath() + "已存在一个同名的文件夹");
		}
		if (file.isFile()) {
			if (!isCover) {
				throw new Exception("目标文件" + file.getPath() + "已存在");
			} else {
				file.delete();
			}
		}

		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(cpFile);
			fos = new FileOutputStream(file);
			byte[] bytes = new byte[1024];
			int len;
			while ((len = fis.read(bytes)) > 0) {
				fos.write(bytes, 0, len);
			}
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
	}

	public static byte[] readFile(File file) throws Exception {
		if (file == null || !file.exists()) {
			throw new Exception("参数：文件不是为空或文件不存在");
		}
		FileInputStream fis = null;
		byte[] bytes = new byte[(int) file.length()];
		try {
			fis = new FileInputStream(file);
			fis.read(bytes);
			return bytes;
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
	}

	public static void writeFile(String filePath, byte[] bytes, boolean isCover) throws Exception {
		if (filePath == null) {
			throw new Exception("参数：文件路径不能为空");
		}
		if (bytes == null || bytes.length == 0) {
			throw new Exception("参数：字节数组不能是空的");
		}
		File file = new File(filePath);
		if (file.isDirectory()) {
			throw new Exception("目标文件" + file.getPath() + "已存在一个同名的文件夹");
		}
		if (file.isFile()) {
			if (!isCover) {
				throw new Exception("目标文件" + file.getPath() + "已存在");
			} else {
				file.delete();
			}
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(bytes);
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	

	public static void deleteFile(File file) {
		if (!file.exists()) {
			return;
		}
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

	

	public static Properties loadClassPathProperties(String path) {
		path = path.trim();
		if (path == null || path.length() == 0) {
			return null;
		}
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		if (is == null) {
			return null;
		}
		try {
			Properties cofig = new Properties();
			cofig.load(is);
			return cofig;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
