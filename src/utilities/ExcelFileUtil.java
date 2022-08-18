package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	XSSFWorkbook wb;
	//constructor for reading excel path
	public  ExcelFileUtil(String excelpath) throws Throwable {
	FileInputStream fi = new FileInputStream(excelpath);
	wb = new XSSFWorkbook(fi);
	}
	//count no of rows in a sheet
	public int rowcount(String sheetName) {
		return wb.getSheet(sheetName).getLastRowNum();
	}
	//count no of cell in a row
	public int cellCount(String sheetName) {
		return wb.getSheet(sheetName).getRow(0).getLastCellNum();
	}
	//read cell data from sheet
	public String getcellData(String sheetName,int row,int column) {
		String data = "";
		if(wb.getSheet(sheetName).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC) {
		int cellData =(int)wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
		data = String.valueOf(cellData);
		}
		else
		{
			data = wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}
	//set cell data
	public void setcellData(String sheetName,int row,int column,String status,String writeexcel) throws Throwable {
		//get row from wb
		XSSFSheet wsSheet= wb.getSheet(sheetName);
		//get row from sheet
		XSSFRow rowNum = wsSheet.getRow(row);
		//create cell
		XSSFCell cell = rowNum.createCell(column);
		//write status int cell
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("pass")) {
		XSSFCellStyle style= wb.createCellStyle();
		XSSFFont font = wb.createFont();
		font.setColor(IndexedColors.BRIGHT_GREEN.getIndex());
		font.setBold(true);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rowNum.getCell(column).setCellStyle(style);
		}
		
		if(status .equalsIgnoreCase("fail")) {
			XSSFCellStyle style= wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
				
		}
		if(status.equalsIgnoreCase("blocked")) {
			XSSFCellStyle style= wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
	}
FileOutputStream fo = new FileOutputStream(writeexcel);
wb.write(fo);
	}
	public static void main(String[] args) throws Throwable {
 ExcelFileUtil xl= new ExcelFileUtil("D://prabh.xlsx");
 int rc = xl.rowcount("Raju");
 System.out.println(rc);
 for(int i=1;i<=rc;i++) {
	 String user = xl.getcellData("Raju", i, 0);
	 String pass = xl.getcellData("Raju", i, 1);
	 System.out.println(user+"   "+pass);
	// xl.setcellData("Raju", i,2, "pass", "D:/Results.xlsx");
	 //xl.setcellData("Raju", i, 2, "fail", "D://Results.xlsx");
   xl.setcellData("Raju", i, 2, "blocked", "D://Results.xlsx");
	}
}
}
