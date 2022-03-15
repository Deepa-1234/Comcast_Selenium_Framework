package com.crm.GenericLibrary;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class is developed by using Apache poi libraries,which is used to handle excell sheet
 * 
 * This class provides generic methods to read data from and write data to excel sheet
 * @author Deepa
 *
 */
public class ExcelUtility {

	/**
	 * This method will read data from excel sheet and return the cell value
	 * @param sheet
	 * @param rowCnt
	 * @param cellCnt
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public String getDataFromExcel(String sheetName, int rowNum, int cellNum) throws EncryptedDocumentException, InvalidFormatException, IOException {
		FileInputStream fis= new FileInputStream(IpathConstants.ExcelPath);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(sheetName);
		Row row=sh.getRow(rowNum);
		Cell cel=row.getCell(cellNum);
		String data=cel.getStringCellValue();
		return data;	
	}
	
	/**
	 * This method will return total row count
	 * @param sheet
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public int getRowcount(String sheetName) throws EncryptedDocumentException, InvalidFormatException, IOException {
		FileInputStream fis= new FileInputStream(IpathConstants.ExcelPath);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(sheetName);
		int rowCount=sh.getLastRowNum();
		return rowCount;
	}
	
	/**
	 * This method will write the data on to excel sheet
	 * @param sheet
	 * @param rowCnt
	 * @param cellCnt
	 * @param data
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public void writeDataToExcel(String sheet, int rowCnt, int cellCnt, String data) throws EncryptedDocumentException, InvalidFormatException, IOException {
		FileInputStream fis= new FileInputStream(IpathConstants.ExcelPath);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(sheet);
		Row row=sh.getRow(rowCnt);
		Cell cel=row.getCell(cellCnt);
		cel.setCellValue(data);
		FileOutputStream fos=new FileOutputStream(IpathConstants.ExcelPath);
		wb.write(fos);
		wb.close();
	}
		
}
