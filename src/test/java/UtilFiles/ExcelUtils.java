package UtilFiles;


import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
    private Workbook workbook;
    private Sheet sheet;

    // Constructor to initialize workbook and sheet
    public ExcelUtils(String filePath, String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get data from a specific cell
    public String getCellData(int row, int col) {
        Row excelRow = sheet.getRow(row);
        Cell cell = excelRow.getCell(col);
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    // Get row count
    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    // Get column count
    public int getColumnCount() {
        return sheet.getRow(0).getPhysicalNumberOfCells();
    }

    // Close workbook
    public void closeWorkbook() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

