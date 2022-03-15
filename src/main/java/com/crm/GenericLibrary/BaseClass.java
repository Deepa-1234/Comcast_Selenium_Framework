package com.crm.GenericLibrary;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.crm.comcat.objectrepositorylib.HomaPage;
import com.crm.comcat.objectrepositorylib.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
    public 	WebDriver driver=null;
	public	JavaUtility jLib= new JavaUtility();
	public	FileUtility fLib= new FileUtility();
	public	WebDriverUtility wLib= new WebDriverUtility();
	public	ExcelUtility eLib= new ExcelUtility();
	public	String lastName;
	public	String orgName;
	public	String USERNAME;
	public	String PASSWORD;
	public	String BROWSER;
	public static WebDriver sDriver;
	
	/**
	 * This method opens the databse connection
	 * @throws IOException
	 */
	@BeforeSuite(groups = "smokeTest")
	public void configBS() {
		Reporter.log("=================Databade connection opened=============",true);
	}
	
	/**
	 * This method opens the browser
	 * @throws IOException
	 */
	@Parameters("BROWSER")
	@BeforeClass(groups = "smokeTest")
	public void configBC(/*String BROWSER*/) throws IOException {
		
		BROWSER=fLib.readDataFromPropertyFile("browser");
		if(BROWSER.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}else if(BROWSER.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}else if(BROWSER.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}else
		{
			Reporter.log("invalid browser name",true);
		}
		wLib.waitUntillPageLoad(driver);
		Reporter.log("***********Browser opened***********",true);	
		sDriver = driver;
	}

	/**
	 * This method helps to login to application
	 * by entering username and password
	 * @throws IOException
	 */
	@BeforeMethod(groups = "smokeTest")
	public void configBM() throws IOException {
		USERNAME=fLib.readDataFromPropertyFile("username");
		PASSWORD=fLib.readDataFromPropertyFile("password");
		String URL=fLib.readDataFromPropertyFile("url");
		LoginPage lp= new LoginPage(driver);
		lp.loginApp(USERNAME, PASSWORD,URL);
		wLib.maximizeWindow(driver);
		Reporter.log("    Login to application successful",true);
	}
	
	/**
	 * Method to logout application
	 */
	@AfterMethod(groups = "smokeTest")
	public void configAM() {
		HomaPage hp = new HomaPage(driver);
		hp.logOut();
		Reporter.log("-----logged out successfully-----",true);
	}
	
	/**
	 * Method to close the browser
	 */
	@AfterClass(groups = "smokeTest")
	public void configAC() {
		driver.close();
		Reporter.log("***********Browser closed***********",true);
	}
	
	/**
	 * This method closes the databse connection
	 * @throws IOException
	 */
	@AfterSuite(groups = "smokeTest")
	public void configAS() {
		Reporter.log("===============Closed the databade connection==================",true);
	}
}
