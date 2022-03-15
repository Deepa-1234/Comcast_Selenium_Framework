package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

public class SampleTestAllrecords{

	public static void main(String[] args) throws IOException, EncryptedDocumentException, InvalidFormatException, InterruptedException {
		
		FileInputStream fis= new FileInputStream("./data/commonData.properties.txt");
		Properties p=new Properties();
		p.load(fis);
		
		
		String CreateOrganisation=p.getProperty("createOrg");
//		driver.get(URL);
//		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
//		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
//		driver.findElement(By.id("submitButton")).click();
//
//		driver.findElement(By.xpath("(//a[.='Organizations'])[1]")).click();
//		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

		FileInputStream fis1=new FileInputStream("./data/Test_Script_Data.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		
		int rcount=wb.getSheet(CreateOrganisation).getLastRowNum();
		int cCount=wb.getSheet(CreateOrganisation).getRow(0).getLastCellNum();
		for(int i=1;i<rcount;i++)
		{
			for(int j=1;j<6;j++) {
				String proName=wb.getSheet(CreateOrganisation).getRow(i).getCell(j).getStringCellValue();
				System.out.print(proName);
			}
			System.out.println();
		}
		

	}

}


