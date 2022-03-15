/*TC_03*/
package com.crm.comcat.contactTest;
import java.io.File;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.crm.GenericLibrary.BaseClass;
import com.crm.GenericLibrary.IpathConstants;
import com.crm.comcat.objectrepositorylib.ContactsPage;
import com.crm.comcat.objectrepositorylib.CreateNewContactPage;
import com.crm.comcat.objectrepositorylib.HomaPage;


@Listeners(com.crm.GenericLibrary.ListenerImpClass.class)

/**
 * Create contact Choosefile
 * @author Deepa
 *
 */
public class CreateContactChooseFileTest extends BaseClass {



	/**
	 * create contact and choosing contact image
	 * @throws IOException
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws InterruptedException 
	 */
	@Test(groups = "SystemTest")

	public void chooseFileOpenTest() throws IOException, EncryptedDocumentException, InvalidFormatException, InterruptedException {
		//Navigating to contacts page
		HomaPage hp = new HomaPage(driver);
		hp.getContactsLink().click();
		
		String expTitle="Contacts";
		wLib.waitForElementVisible(driver, expTitle);
		String actualTitle=driver.getTitle();
		Assert.assertTrue(actualTitle.contains(expTitle),"Not navigated to contacts");
		Reporter.log("Navigated to Contacts:Pass",true);

		//Navigating to create new contact page
		ContactsPage op = new ContactsPage(driver);
		op.getCreateContactImg().click();

		expTitle="Contacts";
		wLib.waitForElementVisible(driver, expTitle);
		actualTitle=driver.getTitle();
		Assert.assertTrue(actualTitle.contains(expTitle),"Not navigated to create new contact");
		Reporter.log("Navigated to create new Contact Page:Pass",true);


		//Clicking on choose file image button
		CreateNewContactPage cp = new CreateNewContactPage(driver);
		lastName=eLib.getDataFromExcel("contact", 5, 2);
		driver.manage().window().maximize();
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		String actualVal=cp.getChooseFileBtn().getAttribute("type");
		String expVal="file";
		Assert.assertEquals(actualVal, expVal);
		Reporter.log("choose file Page is opened in new browser",true);		

		File relPath = new File(IpathConstants.contactImgPath);
		String absPath = relPath.getAbsolutePath();
		cp.getChooseFileBtn().sendKeys(absPath);
		
		String actualImagName = cp.getImageNameHiddenImg().getAttribute("value");
		System.out.println("Hi");
		System.out.println(actualImagName);
		String expImagName="contactImg";
		Assert.assertTrue(actualImagName.contains(expImagName),"Contact image name not is displayed in contact information page");
		Reporter.log("Contact image name is displayed in contact information page",true);
		cp.createContact(lastName);
	}


}

