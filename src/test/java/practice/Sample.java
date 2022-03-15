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

public class Sample {

	public static void main(String[] args) throws IOException, EncryptedDocumentException, InvalidFormatException, InterruptedException {
		WebDriver driver= null;
		
		FileInputStream fis= new FileInputStream("./data/commonData.properties.txt");
		Properties p=new Properties();
		p.load(fis);
		String BROWSER=p.getProperty("browser");
		if(BROWSER.equals("chrome"))
		{
			driver=new ChromeDriver();
		}else if(BROWSER.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}else if(BROWSER.equals("edge"))
		{
			driver=new EdgeDriver();
		}else
		{
			driver=new ChromeDriver();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String USERNAME=p.getProperty("username");
		String PASSWORD=p.getProperty("password");
		String URL=p.getProperty("url");
		String CreateOrganisation=p.getProperty("createOrg");
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.xpath("(//a[.='Organizations'])[1]")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		FileInputStream fis1=new FileInputStream("./data/Test_Script_Data.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		
		String ORGNAME = wb.getSheet(CreateOrganisation).getRow(1).getCell(2).getStringCellValue();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(ORGNAME);
		
		String WEBSITE = wb.getSheet(CreateOrganisation).getRow(1).getCell(3).getStringCellValue();
		driver.findElement(By.name("website")).sendKeys(WEBSITE);
		
		String PHONE = wb.getSheet(CreateOrganisation).getRow(1).getCell(4).getStringCellValue();
		driver.findElement(By.name("phone")).sendKeys(PHONE);

		String INDUSTRY = wb.getSheet(CreateOrganisation).getRow(1).getCell(5).getStringCellValue();
		WebElement industry=driver.findElement(By.name("industry"));
		Select s=new Select(industry);
		s.selectByValue(INDUSTRY);

		String TYPE = wb.getSheet(CreateOrganisation).getRow(1).getCell(6).getStringCellValue();
		WebElement type=driver.findElement(By.name("accounttype"));
		Select s1= new Select(type);
		s1.selectByValue(TYPE);
		
		int ANNUAL_REVENUE = (int)wb.getSheet("CreateOrganisation").getRow(1).getCell(7).getNumericCellValue();
		driver.findElement(By.xpath("//input[@name='annual_revenue']")).sendKeys(ANNUAL_REVENUE+"");

		String RATING = wb.getSheet(CreateOrganisation).getRow(1).getCell(8).getStringCellValue();
		WebElement rating=driver.findElement(By.name("rating"));
		Select s2= new Select(rating);
		s2.selectByVisibleText(RATING);
		
		//String USER_GROUP = wb.getSheet("CreateOrganisation").getRow(1).getCell(9).getStringCellValue();
		
		String ASSIGNED = wb.getSheet(CreateOrganisation).getRow(1).getCell(10).getStringCellValue();
		driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
		WebElement assgnTo=driver.findElement(By.name("assigned_group_id"));
		Select s3=new Select(assgnTo);
		s3.selectByVisibleText(ASSIGNED);
		
		String B_ADDRESS = wb.getSheet(CreateOrganisation).getRow(1).getCell(11).getStringCellValue();
		driver.findElement(By.name("bill_street")).sendKeys(B_ADDRESS);
		
		String B_CITY = wb.getSheet(CreateOrganisation).getRow(1).getCell(12).getStringCellValue();
		driver.findElement(By.name("bill_city")).sendKeys(B_CITY);
		
		String B_STATE = wb.getSheet(CreateOrganisation).getRow(1).getCell(13).getStringCellValue();
		driver.findElement(By.id("bill_state")).sendKeys(B_STATE);

		int B_POSTAL_CODE = (int)wb.getSheet(CreateOrganisation).getRow(1).getCell(14).getNumericCellValue();
		driver.findElement(By.id("bill_code")).sendKeys(B_POSTAL_CODE+"");

		String B_COUNTRY = wb.getSheet(CreateOrganisation).getRow(1).getCell(15).getStringCellValue();
		driver.findElement(By.id("bill_country")).sendKeys(B_COUNTRY);
		
		driver.findElement(By.xpath("(//input[@name='cpy'])[2]")).click();
		driver.findElement(By.xpath("(//input[@name='button'])[3]")).click();
		try {
		String text=driver.findElement(By.xpath("//span[contains(@class,'dvHeaderText')]")).getText();
		if(text.contains(ORGNAME)) {
			System.out.println("Organisation created");
		}
		}
		catch(WebDriverException e) {
			driver.switchTo().alert().accept();
			System.out.println("Organisation not created");
		}
		
		driver.close();
	}

}
