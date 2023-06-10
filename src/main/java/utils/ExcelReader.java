package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    public static Object[][] getTestData(String ExcelName, String SheetName) {
        Object[][] testData = null;
        try {
            String excelFilePath = "src/test/java/TestData/"+ExcelName+".xlsx";
            FileInputStream fileInputStream = new FileInputStream(excelFilePath);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet(SheetName);

            int rowCount = sheet.getLastRowNum();
            int colCount = sheet.getRow(0).getLastCellNum();

            testData = new Object[rowCount][colCount];
            for (int i = 0; i < rowCount; i++) {
                Row row = sheet.getRow(i + 1);
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        if (cell.getCellType() == CellType.NUMERIC) {
                            cell.setCellType(CellType.STRING);
                        }
                        testData[i][j] = cell.toString();
                    } else {
                        testData[i][j] = "";
                    }
                }
            }
            workbook.close();
            fileInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return testData;
    }
}
