package com.dayon.common.other;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IOUtil {

	public static void downLoad(String pathName, File downLoadFile,
			boolean isCover) throws Exception {
		if (pathName == null || downLoadFile == null
				|| downLoadFile.length() == 0) {
			throw new Exception("the pathName or downLoadFile is empty");
		}
		File file = new File(pathName);
		if (file.isDirectory() || file.isHidden()) {
			throw new Exception(
					"lack power,the pathName exist same directory or hiddenFile");
		}
		if (file.isFile() && !isCover) {
			throw new Exception(
					"the pathName exist same file and isCover is false");
		}
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(downLoadFile);
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
		if (file == null || file.length() == 0) {
			throw new Exception("the file is empty");
		}
		FileInputStream fis = null;
		byte[] bytes = new byte[(int) file.length()];
		try {
			fis=new FileInputStream(file);
			fis.read(bytes);
			return bytes;
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
	}

	public static void writeFile(String filePath, byte[] bytes, boolean isCover)
			throws Exception {
		if (filePath == null || bytes == null || bytes.length == 0) {
			throw new Exception(
					"the filePath or bytes is empty");
		}
		File file = new File(filePath);
		if (file.isDirectory() || file.isHidden()) {
			throw new Exception(
					"lack power,the filePath exist same directory or hiddenFile");
		}
		if (file.isFile() && !isCover) {
			throw new Exception(
					"the filePath exist same file and isCover is false");
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

	public static byte[] classToBytes(Object obj) throws Exception {
		if (obj == null) {
			return new byte[] {};
		}
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			return bos.toByteArray();
		} finally {
			if (oos != null) {
				oos.close();
			}
		}
	}

	public static Object bytesToClass(byte[] bytes) throws Exception {
		if (bytes == null || bytes.length == 0) {
			throw new Exception("the bytes is empty");
		}
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			bis = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bis);
			return ois.readObject();
		} finally {
			if (ois != null) {
				ois.close();
			}
		}
	}
}
