package com.dayon.common.file;

import java.io.OutputStream;
import java.util.List;

import com.dayon.common.file.excel.WorkBookRule;
import com.dayon.common.file.excel.WorkSheetRule;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class FileUtil {
	private FileUtil() {
	}

	public static WorkBookRule importExcel() {
		return null;
	}

	public static void exportExcel(WorkBookRule pwbr, OutputStream os) {

		try {
			WritableWorkbook book = Workbook.createWorkbook(os);
			int i = 0;
			for (WorkSheetRule item : pwbr.getSheets()) {
				WritableSheet sheet = book.createSheet(item.getName(), i++);
				for (int j = 0; j < item.getTitles().length; j++) {
					Label label = new Label(j, 0, item.getTitles()[j]);
					sheet.addCell(label);
				}
				List<List<String>> llist = item.getRowDatas();
				for (int j = 0; j < llist.size(); j++) {
					List<String> strs = llist.get(j);
					for (int k = 0; k < strs.size(); k++) {
						Label label = new Label(k, j + 1, llist.get(j).get(k));
						sheet.addCell(label);
					}

				}

			}
			book.write();
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
