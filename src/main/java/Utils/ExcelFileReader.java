package Utils;

import org.apache.poi.xssf.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelFileReader extends Base{

    public static String stringAmount,stringPeriod,stringInterestRate;
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    public static void readExcelSheet() {

        try {

            FileInputStream file = new FileInputStream("F:\\Projects\\LoanSeleniumJava\\src\\main\\resources\\ExcelSheets\\HomeLoanSheet.xlsx");
            workbook = new XSSFWorkbook(file);

        }catch (IOException e){
            e.printStackTrace();
        }

        sheet = workbook.getSheet(prop.getProperty("excelSheet"));

        //Total numbers of rows and columns in sheet
        int totalRows = sheet.getLastRowNum();
        int totalColumns = sheet.getRow(1).getLastCellNum();

        for(int r=1;r<=totalRows;r++)
        {
            XSSFRow row = sheet.getRow(r);

            for(int c=0;c<totalColumns;c++)
            {

                switch (c)
                {
                    case 0:
                       double amount =  row.getCell(0).getNumericCellValue();
                       stringAmount = convertDoubleToString(amount);
                       break;
                    case 1:
                        double period =  row.getCell(1).getNumericCellValue();
                        stringPeriod = convertDoubleToString(period);
                        break;
                    case 2:
                        double interestRate =  row.getCell(2).getNumericCellValue();
                        stringInterestRate = convertDoubleToString(interestRate);
                        break;
                }

            }
        }

    }

    public static String convertDoubleToString(double value)
    {
        int i = (int)value;
        String s = String.valueOf(i);
        return s;
    }

}
