/*TC_04*/
package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateContractReportContact {
	WebDriver driver= null;

	FileInputStream fis;
	Properties p;

	String USERNAME;
	String PASSWORD;
	Actions action;
	Set<String> windows;
	String parent;
	String expectedText;

	@BeforeClass
	public void openBrowserTest() throws IOException {
		fis= new FileInputStream("./data/commonData.properties.txt");
		p=new Properties();
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


		String URL=p.getProperty("url");

		driver.get(URL);
		action=new Actions(driver);
		Reporter.log("Browser opened");
	}
	@Test(priority = 0)
	public void loginPageTest() throws IOException {


		USERNAME=p.getProperty("username");
		PASSWORD=p.getProperty("password");
		String actualTitle=driver.getTitle();
		String expectedTitle="vtiger CRM 5 - Commercial Open Source CRM";
		Assert.assertEquals(actualTitle, expectedTitle);
		Reporter.log("Login page found",true);

	}

	@Test(priority =1)
	public void homePageTest() {

		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		Reporter.log("Home page found",true);
	}

	@Test(priority = 2)
	public void contactListPageTest() {
		Actions action=new Actions(driver);
		WebElement element=driver.findElement(By.linkText("Contacts"));
		action.moveToElement(element).doubleClick().perform();

		String actualTitle=driver.getTitle();
		String expectedTitle="Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";
		Assert.assertEquals(actualTitle, expectedTitle);
		Reporter.log("ContactsPage Displayed",true);
	}

	@Test(priority = 3)
	public void createNewContactPageTest() {
		driver.findElement(By.xpath("//img[contains(@title,'Create Contact')]")).click();
		Boolean flag=driver.findElement(By.xpath("//span[.='Creating New Contact']")).isDisplayed();
		Assert.assertTrue(flag);
		Reporter.log("Creating New Contact is displayed",true);

	}

	@Test(priority=4)
	public void contactNewBrowserWindowTest() {
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("vaj sighn");
		driver.findElement(By.xpath("(//img[contains(@src,'select.gif')])[2]")).click();

		windows=driver.getWindowHandles();
		parent=driver.getWindowHandle();
		
		boolean expected=true;
		for( String wh:windows) {
			driver.switchTo().window(wh);
			if(!(wh.equals(parent)))
			{
				boolean actual=driver.findElement(By.xpath("//td[.='Contacts']")).isDisplayed();
				Assert.assertEquals(actual,expected);
				Reporter.log("Contact page displayed in another browser window",true);
			}
		}

	}

	@Test(priority = 5)
		public void contactNameTest() {
			
			driver.findElement(By.xpath("(//a)[4]")).click();
	
			driver.switchTo().window(parent);
			String actualText=driver.findElement(By.xpath("//input[@name='contact_name']")).getText();
			try 
			{
			Assert.assertEquals(actualText, expectedText);
			Reporter.log("contact name is displayed",true);
			}catch (Error e) {
				Reporter.log("contact name is not displayed",true);
				Assert.fail();
			}
		}
	@Test(priority = 6)
	public void saveContactTest() {
		driver.findElement(By.xpath("(//input[contains(@title,'Save')])[2]")).click();
		String actMessage1=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		String actMessage2=driver.findElement(By.xpath("//span[@class='small']")).getText();
		String expMessage1="vaj sighn - Contact Information";
		String expMessage2="Updated today";
		if(actMessage1.contains(expMessage1)&&actMessage2.contains(expMessage2)) 
		{
			Reporter.log(actMessage1+" "+actMessage2,true);
		}else {
			Reporter.log("contact not added",true);
		}	
	}
	@Test(priority = 7)
	public void logOutTest() {
		WebElement elemet=driver.findElement(By.xpath("//img[contains(@src,'user.PNG')]"));
		action.moveToElement(elemet).doubleClick().perform();
		driver.findElement(By.linkText("Sign Out")).click();
		Reporter.log("Successfully loged out",true);
		
	}
	
	@AfterClass
	public void closeBrowserTest() {
		
		driver.close();
		Reporter.log("Browser closed",true);
	}

}
