package utils;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelDataProviderUtil {

    public static Object[][] readTestDataFromExcel(String excelFilePath) throws IOException {
        Object[][] data;

        try (FileInputStream inputStream = new FileInputStream(new File(excelFilePath))) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

            data = new Object[rowCount - 1][colCount];

            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    switch (cell.getCellType()) {
                        case STRING:
                            data[i - 1][j] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            data[i - 1][j] = cell.getNumericCellValue();
                            break;
                        // Add more cases based on your data types

                        default:
                            // Handle other cell types as needed
                            break;
                    }
                    System.out.println(data[i - 1][j]);
                }
            }
        }

        return data;
    }
}
