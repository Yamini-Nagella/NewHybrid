package utilities;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import exceptions.UtilityException;

public class ExcelSheetRead extends GetProperties {
	public static Map<String, String> excelData;

	public ExcelSheetRead() throws Exception {
		super();
		if (excelData == null) {
			try {
				excelData = new HashMap<>();
				FileInputStream file = new FileInputStream(get.getProperty("excel"));
				XSSFWorkbook excelBook = new XSSFWorkbook(file);
				for (int i = 0; i < excelBook.getNumberOfSheets(); i++) {

					XSSFSheet sheet = excelBook.getSheetAt(i);
					Iterator<Row> rows = sheet.iterator();
					while (rows.hasNext()) {
						int d = 0;
						Iterator<Cell> cells = rows.next().cellIterator();
						String key = "";
						while (cells.hasNext()) {
							String data = cells.next().toString();
							if (d == 0) {
								excelData.put(data, "");
								key = data;
							} else {
								excelData.put(key, data);
							}
							d++;
						}
					}
				}
//				System.out.println(excelData);
				excelBook.close();
			} catch (Exception e) {
				throw new UtilityException(e.getMessage());
			}
		}
	}
}
