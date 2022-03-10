package ExcelReader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

        File file;
        FileInputStream fis;
        XSSFWorkbook wb;
        XSSFSheet sheet;
        XSSFRow row;
        XSSFCell cell;

        public ExcelReader(String filePath) throws IOException {
            file = new File(filePath);
            fis = new FileInputStream(file);
            wb = new XSSFWorkbook(fis);
        }

        public String getStringData(String sheetName, int rowNumber, int cellNumber) {
            sheet = wb.getSheet(sheetName);
            row = sheet.getRow(rowNumber);
            cell = row.getCell(cellNumber);
            return cell.getStringCellValue();
        }

        public int getIntegerData(String sheetName, int rowNumber, int cellNumber) throws IllegalStateException {
            try {
                sheet = wb.getSheet(sheetName);
                row = sheet.getRow(rowNumber);
                cell = row.getCell(cellNumber);
                return (int) cell.getNumericCellValue();
            } catch (IllegalStateException ise) {
                ise = new IllegalStateException("Uneta vrednost nije broj.");
                throw ise;
            }
        }

        public int getLastRow(String sheetName) {
            sheet = wb.getSheet(sheetName);
            int rowNumber = sheet.getLastRowNum();
            return rowNumber;
        }

        public void removeRow(String sheetName, int rowNumber){
            sheet = wb.getSheet(sheetName);
            row = sheet.getRow(rowNumber);
            sheet.removeRow(row);
        }

        public Cell getCell(String sheetName, int rowNumber, int cellNumber){
            sheet = wb.getSheet(sheetName);
            row = sheet.getRow(rowNumber);
            cell  = row.getCell(cellNumber);
            return cell;

        }
    }
