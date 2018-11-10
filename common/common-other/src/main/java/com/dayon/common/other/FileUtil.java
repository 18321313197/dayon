package com.dayon.common.other;

import java.io.File;

public class FileUtil {
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
}
