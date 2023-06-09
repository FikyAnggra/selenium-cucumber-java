package helper;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class TestDataDriven {

    public static void main(String[] args){
        testData("login", "Sheet1", "Password");
    }

    public static String testData(String ExcelName, String SheetName, String HeaderName) {
        String data = null;
        try {
            String excelFilePath = "src/test/java/TestData/"+ExcelName+".xlsx";
            FileInputStream fileInputStream = new FileInputStream(excelFilePath);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet(SheetName);


            Row headerRow = sheet.getRow(0);
            int columnIndex = -1;
            int totalColumns = headerRow.getLastCellNum();
            for (int j = 0; j < totalColumns; j++) {
                Cell cell = headerRow.getCell(j);
                String headerValue = cell.getStringCellValue();
                if (headerValue.equalsIgnoreCase(HeaderName)) {
                    columnIndex = j;
                    break;
                }
            }

            if (columnIndex == -1) {
                System.out.println("Header tidak ditemukan.");
            } else {
                // Mencetak isi kolom berdasarkan indeks kolom
                int totalRows = sheet.getLastRowNum() + 1;
                for (int i = 1; i < totalRows; i++) {
                    Row row = sheet.getRow(i);
                    Cell cell = row.getCell(columnIndex);


                    if (cell != null && cell.getCellType() != CellType.BLANK) {
                        // Mengubah tipe sel menjadi STRING jika diperlukan
                        if (cell.getCellType() == CellType.NUMERIC) {
                            cell.setCellType(CellType.STRING);
                        }

                        data = cell.getStringCellValue();
                        System.out.println("Data[" + i + "][" + columnIndex + "]: " + data);
                    }
                }
            }

            workbook.close();
            fileInputStream.close();

        } catch (IOException e) {
            // Tangani pengecualian IOException di sini
            e.printStackTrace();
        }
        System.out.println(data);
        return data;
    }
}
