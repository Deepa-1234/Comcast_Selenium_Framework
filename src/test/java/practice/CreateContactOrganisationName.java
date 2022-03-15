/*TC_02*/

package practice;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.crm.GenericLibrary.ExcelUtility;
import com.crm.GenericLibrary.FileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.comcat.objectrepositorylib.ContactInformationPage;
import com.crm.comcat.objectrepositorylib.ContactsPage;
import com.crm.comcat.objectrepositorylib.CreateNewContactPage;
import com.crm.comcat.objectrepositorylib.CreateNewOrganizationPage;
import com.crm.comcat.objectrepositorylib.HomaPage;
import com.crm.comcat.objectrepositorylib.LoginPage;
import com.crm.comcat.objectrepositorylib.OrganizationsPage;
import com.crm.comcat.objectrepositorylib.OrganizatonInformationPage;

public class CreateContactOrganisationName {
	WebDriver driver=null;
	JavaUtility jLib= new JavaUtility();
	FileUtility fLib= new FileUtility();
	WebDriverUtility wLib= new WebDriverUtility();
	ExcelUtility eLib= new ExcelUtility();
	String lastName;
	String orgName;

	String USERNAME;
	String PASSWORD;
	String BROWSER;

	/**
	 * Method to open browser
	 * @throws IOException
	 */
	@Test(priority = 0)
	public void openBrowserTest() throws IOException {
		BROWSER=fLib.readDataFromPropertyFile("browser");
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
			Reporter.log("invalid browser name",true);
		}
		wLib.waitUntillPageLoad(driver);
		Reporter.log("Browser opened",true);
	}
	/**
	 * Method to login to application
	 * @throws IOException
	 */
	@Test(priority = 1)
	
	public void loginTest() throws IOException{
		USERNAME=fLib.readDataFromPropertyFile("username");
		PASSWORD=fLib.readDataFromPropertyFile("password");
		String URL=fLib.readDataFromPropertyFile("url");
		LoginPage lp= new LoginPage(driver);
		lp.loginApp(USERNAME, PASSWORD,URL);
		Reporter.log("Login to application successful:pass",true);
	}

	@Test(priority = 2)
	public void contactListPageTest() {
		HomaPage hp = new HomaPage(driver);
		hp.getContactsLink().click();
		String actualTitle=driver.getTitle();
		String expTitle="Contacts";
		if(actualTitle.contains(expTitle))
		{
			Reporter.log("Navigated to Contacts:Pass",true);
		}else {
			Reporter.log("Not Navigated to Contacts:Fail",true);
			Assert.fail();
		}
	}

	/**
	 * this method navigates to create new contact page
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	@Test(priority =3)
	public void contactCreatePageTest() throws IOException,InvalidFormatException {
		ContactsPage op = new ContactsPage(driver);
		op.getCreateContactImg().click();
		String actualTitle=driver.getTitle();
		String expTitle="Contacts";
		if(actualTitle.contains(expTitle))
		{
			Reporter.log("Navigated to create new Contact Page:Pass",true);
		}else {
			Reporter.log("Not Navigated create new Contact Page:Fail",true);
			Assert.fail();
		}
	}
	@Test(priority = 4)
	public void organizationNewBrowserWindowsTest() throws EncryptedDocumentException, InvalidFormatException, IOException {
		HomaPage hp = new HomaPage(driver);
		hp.getOrganizationLink().click();
		OrganizationsPage orgP = new OrganizationsPage(driver);
		orgP.getCreateOrgImg().click();
		
		orgName=eLib.getDataFromExcel("organization", 4, 2)+jLib.getRandomNumber();
		CreateNewOrganizationPage corgP = new CreateNewOrganizationPage(driver);
		corgP.createOrg(orgName);
		
		OrganizatonInformationPage orgInfop = new OrganizatonInformationPage(driver);
		String actualOrgName=orgInfop.getsuccessfullMsg().getText();
		if(actualOrgName.contains(orgName))
		{
			Reporter.log("Oganization "+orgName+" created:Pass",true);
		}else {
			Reporter.log("Oganization "+orgName+" not created:Fail",true);
			Assert.fail();
		}
		wLib.waitForElementVisible(driver, orgInfop.getsuccessfullMsg());
		hp.getContactsLink().click();
		ContactsPage op = new ContactsPage(driver);
		op.getCreateContactImg().click();
		String actualTitle=driver.getTitle();
		String expTitle="Contacts";
		if(actualTitle.contains(expTitle))
		{
			Reporter.log("Navigated to create new Contact Page:Pass",true);
		}else {
			Reporter.log("Not Navigated create new Contact Page:Pass",true);
			Assert.fail();
		}
		CreateNewContactPage cp = new CreateNewContactPage(driver);
		lastName=eLib.getDataFromExcel("contact", 4, 2);		
		String actualOrg = cp.selectOrgName(orgName);
		String expOrgName = orgName;
		
		if(actualOrg.equals(expOrgName)) {
			Reporter.log("Organisation name is displayed:Pass",true);
		}else {
			Reporter.log("Organisation name is not displayed:Pass",false);

		}
	}
	
	@Test(priority = 5)
	public void contactSaveTest()
	{
		CreateNewContactPage cp = new CreateNewContactPage(driver);
		cp.getSaveBtn().click();
		ContactInformationPage cInfoP = new ContactInformationPage(driver);
		String actualMsg = cInfoP.getsuccessfullMsg().getText();
		if(actualMsg.contains(lastName))
		{
			Reporter.log("Contact "+lastName+" is created with "+orgName+" orgamization:Pass",true);
		}else {
			Reporter.log("Contact not created:fail", true);
		}
	}
	

	/**
	 * Method to logout application
	 */
	@Test(priority = 6)
	public void logOutTest() {
		HomaPage hp = new HomaPage(driver);
		hp.logOut();
		Reporter.log("logged out successfully:Pass",true);
	}

	/**
	 * Method to close the browser
	 */
	@Test(priority = 7)

	public void closeBrowserTest() {

		driver.close();
		Reporter.log("Browser closed:pass",true);
	}
}

