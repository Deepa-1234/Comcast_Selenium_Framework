package com.crm.comcat.contactTest;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.crm.GenericLibrary.BaseClass;
import com.crm.comcat.objectrepositorylib.ContactInformationPage;
import com.crm.comcat.objectrepositorylib.ContactsPage;
import com.crm.comcat.objectrepositorylib.CreateNewContactPage;
import com.crm.comcat.objectrepositorylib.CreateNewOrganizationPage;
import com.crm.comcat.objectrepositorylib.HomaPage;
import com.crm.comcat.objectrepositorylib.OrganizationsPage;
import com.crm.comcat.objectrepositorylib.OrganizatonInformationPage;
/**
 * Create contact
 * @author Deepa
 *
 */
@Listeners(com.crm.GenericLibrary.ListenerImpClass.class)

public class ContactTest extends BaseClass {

	String actualTitle;
	String expTitle;
	/**
	 * This methods creates contact
	 * @throws IOException
	 * @throws InvalidFormatException 
	 * @throws EncryptedDocumentException 
	 */
	@Test(groups = {"smokeTest","SystemTest"})
	public void createContactTest() throws IOException, EncryptedDocumentException, InvalidFormatException {

		//Navigating to Contacts page
		HomaPage hp = new HomaPage(driver);
		hp.getContactsLink().click();
		expTitle="Contacts";
		wLib.waitForElementVisible(driver, expTitle);
		actualTitle=driver.getTitle();
		Assert.assertTrue(actualTitle.contains(expTitle),"NOt nvigated to contacts page");
		Reporter.log("Navigated to Contacts",true);


		//Navigating to create new Contact Page
		ContactsPage op = new ContactsPage(driver);
		op.getCreateContactImg().click();
		expTitle="Contacts";
		wLib.waitForElementVisible(driver, expTitle);
		actualTitle=driver.getTitle();

		Assert.assertTrue(actualTitle.contains(expTitle),"Not nvavigated to create new contacts page");
		Reporter.log("Navigated to create new Contact Page",true);

		//creating new contact
		CreateNewContactPage cp = new CreateNewContactPage(driver);
		lastName=eLib.getDataFromExcel("contact", 4, 2);
		cp.createContact(lastName);	
		ContactInformationPage contactInfo = new ContactInformationPage(driver);
		wLib.waitForElementVisible(driver, contactInfo.getsuccessfullMsg());
		String actualMsg = contactInfo.getsuccessfullMsg().getText();
		Assert.assertTrue(actualMsg.contains(lastName),"Contact not created");
		Reporter.log("Contact "+lastName+" created Successfully:Pass",true);
	}

	/**
	 * Method to create contact with organization
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	@Test(groups = {"IntegrationTest","SystemTest"})
	public void createContactWithOrgTest() throws IOException,InvalidFormatException {

		//Navigating to Organization page page
		HomaPage hp = new HomaPage(driver);
		hp.getOrganizationLink().click();
		
		OrganizationsPage orgP = new OrganizationsPage(driver);
		wLib.waitForElementVisible(driver, orgP.getCreateOrgImg());
		orgP.getCreateOrgImg().click();

		
		//Navigating to create new Organization page page
		orgName=eLib.getDataFromExcel("organization", 1, 2)+jLib.getRandomNumber();
		CreateNewOrganizationPage corgP = new CreateNewOrganizationPage(driver);
		corgP.createOrg(orgName);

		//creating new Organization
		OrganizatonInformationPage orgInfop = new OrganizatonInformationPage(driver);
		wLib.waitForElementVisible(driver, orgInfop.getsuccessfullMsg());
		String actualOrgName=orgInfop.getsuccessfullMsg().getText();
		Assert.assertTrue(actualOrgName.contains(orgName),"Organization not created");
		Reporter.log("Oganization "+orgName+" created",true);


		//Navigating to contacts page
		wLib.waitForElementVisible(driver, hp.getContactsLink());
		hp.getContactsLink().click();
		ContactsPage op = new ContactsPage(driver);

		//Navigating to create new organization page
		wLib.waitForElementVisible(driver, op.getCreateContactImg());
		op.getCreateContactImg().click();
		
		String expTitle="Contacts";
		wLib.waitForElementVisible(driver, expTitle);
		String actualTitle=driver.getTitle();
		Assert.assertTrue(actualTitle.contains(expTitle),"Navigated to create new Contact Page");
		Reporter.log("Navigated to create new Contact Page",true);

		//creating new contact with organization
		CreateNewContactPage cp = new CreateNewContactPage(driver);
		lastName=eLib.getDataFromExcel("contact", 4, 2);
		cp.createContact(lastName, orgName);

		ContactInformationPage cInfoP = new ContactInformationPage(driver);
		wLib.waitForElementVisible(driver, cInfoP.getsuccessfullMsg());
		String actualMsg = cInfoP.getsuccessfullMsg().getText();
		Assert.assertTrue(actualMsg.contains(lastName),"Contact not created with organization");
		Reporter.log("Contact "+lastName+" is created with "+orgName+" orgamization:Pass",true);

	}

	/**
	 * Method to create contact without mandatory fields
	 */
	@Test(groups = "SystemTest")
	public void createContactWithoutMandFieldTest() {

		//Navigating to Contacts page
		HomaPage hp = new HomaPage(driver);
		hp.getContactsLink().click();
		
		String expTitle="Contacts";
		wLib.waitForElementVisible(driver, expTitle);
		String actualTitle=driver.getTitle();
		Assert.assertTrue(actualTitle.contains(expTitle),"Not navigated to contacts");
		Reporter.log("Navigated to Contacts",true);

		//Navigating to create new contact page
		ContactsPage op = new ContactsPage(driver);
		wLib.waitForElementVisible(driver, op.getCreateContactImg());
		op.getCreateContactImg().click();
		expTitle="Contacts";
		wLib.waitForElementVisible(driver, expTitle);
		actualTitle=driver.getTitle();

		Assert.assertTrue(actualTitle.contains(expTitle),"Not navigated to create new contact");
		Reporter.log("Navigated to create new Contact Page",true);


		//creating contact without lastName (mandatory field)
		CreateNewContactPage cp = new CreateNewContactPage(driver);
		cp.getSaveBtn().click();
		String actualMessage=driver.switchTo().alert().getText();
		String expMessage="Last Name cannot be empty";
		
		Assert.assertTrue(actualMessage.contains(expMessage),"Expected message not came:Fail");
		Reporter.log(expMessage+":Pass",true);
		driver.switchTo().alert().accept();
	}

	/**
	 * method to create contact with group as Marketing group
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	@Test(groups = "SystemTest")
	public void createContactGrpAsMarketingGrpTest() throws EncryptedDocumentException, InvalidFormatException, IOException {

		//Navigating to Contacts page
		HomaPage hp = new HomaPage(driver);
		hp.getContactsLink().click();
		
		String expTitle="Contacts";
		wLib.waitForElementVisible(driver, expTitle);
		String actualTitle=driver.getTitle();
		Assert.assertTrue(actualTitle.contains(expTitle),"Not navigated to contacts");
		Reporter.log("Navigated to Contacts",true);


		//Navigating to create new contact page
		ContactsPage op = new ContactsPage(driver);
		wLib.waitForElementVisible(driver, op.getCreateContactImg());
		op.getCreateContactImg().click();
		
		expTitle="Contacts";
		wLib.waitForElementVisible(driver, expTitle);
		actualTitle=driver.getTitle();
		Assert.assertTrue(actualTitle.contains(expTitle),"Not navigated to create new contact");
		Reporter.log("Navigated to create new Contact Page",true);


		//creating contact with group as marketing group
		CreateNewContactPage cp = new CreateNewContactPage(driver);
		lastName=eLib.getDataFromExcel("contact", 4, 2);
		String assignTo = eLib.getDataFromExcel("contact", 10, 3);
		cp.getAssignGroupBtn().click();
		cp.getAssignedGroupLtbx().sendKeys(assignTo);
		cp.createContact(lastName);
		
		ContactInformationPage contactInfo = new ContactInformationPage(driver);
		wLib.waitForElementVisible(driver, contactInfo.getsuccessfullMsg());
		
		String actualMsg = contactInfo.getsuccessfullMsg().getText();
		Assert.assertTrue(actualMsg.contains(lastName),"dont have lastname:"+lastName);
		Reporter.log("Contact created",true);

		String actualAssign = contactInfo.getAssignToMsg().getText();
		Assert.assertTrue(actualAssign.trim().equals(assignTo),"Contact not assigned to"+assignTo);
		Reporter.log("Assigned to "+actualAssign+":Pass",true);
	}


	/**
	 * method to create contact with group as Support group
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	@Test(groups = "SystemTest")
	public void createContactGrpAsSupportGrpTest() throws EncryptedDocumentException, InvalidFormatException, IOException {

		//Navigating to Contacts page
		HomaPage hp = new HomaPage(driver);
		hp.getContactsLink().click();
		
		String expTitle="Contacts";
		wLib.waitForElementVisible(driver, expTitle);
		String actualTitle=driver.getTitle();
		Assert.assertTrue(actualTitle.contains(expTitle),"Not Navigated to contacts");
		Reporter.log("Navigated to Contacts:Pass",true);

		//Navigating to create new contact page
		ContactsPage op = new ContactsPage(driver);
		wLib.waitForElementVisible(driver, op.getCreateContactImg());
		op.getCreateContactImg().click();
		expTitle="Contacts";
		wLib.waitForElementVisible(driver, expTitle);
		actualTitle=driver.getTitle();
		Assert.assertTrue(actualTitle.contains(expTitle),"Not navigated to create new contact");
		Reporter.log("Navigated to create new Contact Page:Pass",true);


		//create new contact with group as support group
		CreateNewContactPage cp = new CreateNewContactPage(driver);
		lastName=eLib.getDataFromExcel("contact", 4, 2);
		String assignTo = eLib.getDataFromExcel("contact", 11, 3);
		
		cp.getAssignGroupBtn().click();
		cp.getAssignedGroupLtbx().sendKeys(assignTo);
		cp.createContact(lastName);
		
		ContactInformationPage contactInfo = new ContactInformationPage(driver);
		wLib.waitForElementVisible(driver, contactInfo.getsuccessfullMsg());
		String actualMsg = contactInfo.getsuccessfullMsg().getText();
		Assert.assertTrue(actualMsg.contains(lastName),"Dont have lastname"+lastName);


		String actualAssign = contactInfo.getAssignToMsg().getText();
		Assert.assertTrue(actualAssign.trim().equals(assignTo),"contact is not assigned to"+assignTo);
		Reporter.log("Assigned to "+actualAssign+":Pass",true);
	}

	/**
	 * method to create contact with user as administrator
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */

	@Test(groups = "SystemTest")
	public void createContactUserAsAdminTest() throws EncryptedDocumentException, InvalidFormatException, IOException {

		//Navigating to contacts page
		HomaPage hp = new HomaPage(driver);
		hp.getContactsLink().click();
		
		String expTitle="Contacts";
		wLib.waitForElementVisible(driver, expTitle);
		String actualTitle=driver.getTitle();
		Assert.assertTrue(actualTitle.contains(expTitle),"Not navigated to contacts");
		Reporter.log("Navigated to Contacts:Pass",true);

		//navigating to create new contact page
		ContactsPage op = new ContactsPage(driver);
		wLib.waitForElementVisible(driver, op.getCreateContactImg());
		op.getCreateContactImg().click();
		expTitle="Contacts";
		wLib.waitForElementVisible(driver, expTitle);
		actualTitle=driver.getTitle();
		Assert.assertTrue(actualTitle.contains(expTitle),"Not navigated to create new contact");
		Reporter.log("Navigated to create new Contact Page:Pass",true);


		//creating contact with user as administrator
		CreateNewContactPage cp = new CreateNewContactPage(driver);
		lastName=eLib.getDataFromExcel("contact", 4, 2);
		String assignTo = eLib.getDataFromExcel("contact", 14, 3);
		cp.getAssignUserBtn().click();
		cp.getAssignedUserLtbx().sendKeys(assignTo);
		cp.createContact(lastName);
		
		ContactInformationPage contactInfo = new ContactInformationPage(driver);
		wLib.waitForElementVisible(driver, contactInfo.getsuccessfullMsg());
		String actualMsg = contactInfo.getsuccessfullMsg().getText();
		Assert.assertTrue(actualMsg.contains(lastName),"contact is not created with:"+lastName);
		Reporter.log("Contact "+lastName+" created Successfully:Pass",true);

		String actualAssign = contactInfo.getAssignToMsg().getText();
		Assert.assertTrue(actualAssign.trim().equals(assignTo),"Not assigned to"+actualAssign);
		Reporter.log("Assigned to "+actualAssign+":Pass",true);

	}

	/**
	 * method to test contact is added to Report to field and erased successfully
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	@Test(groups = "SystemTest")
	public void CreateContactReportToTest() throws EncryptedDocumentException, InvalidFormatException, IOException{
		
		//Navigating to contact page
		HomaPage hp = new HomaPage(driver);
		hp.getContactsLink().click();
		String expTitle="Contacts";
		wLib.waitForElementVisible(driver, expTitle);
		String actualTitle=driver.getTitle();
		Assert.assertTrue(actualTitle.contains(expTitle),"Not navigated to contacts");
		Reporter.log("Navigated to Contacts",true);
		
		//Navigating to create new contact page
		ContactsPage op = new ContactsPage(driver);
		wLib.waitForElementVisible(driver, op.getCreateContactImg());
		op.getCreateContactImg().click();
		expTitle="Contacts";
		wLib.waitForElementVisible(driver, expTitle);
		actualTitle=driver.getTitle();
		Assert.assertTrue(actualTitle.contains(expTitle),"Not navigated to create new contact page");
		Reporter.log("Navigated to create new Contact Page",true);
		
		String reportsTocontact=eLib.getDataFromExcel("contact", 5, 2)+jLib.getRandomNumber();
		CreateNewContactPage cp = new CreateNewContactPage(driver);
		cp.createContact(reportsTocontact);
		ContactInformationPage cinp = new ContactInformationPage(driver);
		wLib.waitForElementVisible(driver, cinp.getsuccessfullMsg());
		ContactsPage contactCp = new ContactsPage(driver);
		contactCp.getCreateContactImg().click();
		lastName = eLib.getDataFromExcel("contact", 13, 2)+jLib.getRandomNumber();
		cp.getReportsToImg().click();

		String expectedTitle="Administrator - Contacts";
		wLib.waitForElementVisible(driver, expectedTitle);
		actualTitle=driver.getTitle();

		Assert.assertTrue(actualTitle.contains(expectedTitle),"Contact page is not displayed in another browser window");
		Reporter.log("Contact page displayed in another browser window",true);
		
		cp.selectingWithReportTo(reportsTocontact);
		String actualReportToVal =  cp.getReportsToEdt().getAttribute("value");

		Assert.assertTrue(actualReportToVal.trim().equals(reportsTocontact),"Selected contact is not displayed");
		Reporter.log("selected contact "+reportsTocontact+" is displayed in reports to:Pass",true);
		cp.getClearImg().click();
		String actualReportsVal = cp.getReportsToEdt().getAttribute("value");
		
		Assert.assertEquals(actualReportsVal, "");
		Reporter.log("Contact name erased:pass",true);	
		
	}
	
	/**
	 * method to create contact with Lead source
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	@Test(groups = "SystemTest")
	public void createContactWithLeadSourceTest() throws EncryptedDocumentException, InvalidFormatException, IOException  {

		//Navigating to Contacts page
		HomaPage hp = new HomaPage(driver);
		hp.getContactsLink().click();
		String expTitle="Contacts";
		wLib.waitForElementVisible(driver, expTitle);
		String actualTitle=driver.getTitle();

		Assert.assertTrue(actualTitle.contains(expTitle),"Not navigated to contact");
		Reporter.log("Navigated to Contacts",true);


		//Navigating to create new contact page
		ContactsPage op = new ContactsPage(driver);
		op.getCreateContactImg().click();
		expTitle="Contacts";
		wLib.waitForElementVisible(driver, expTitle);
		actualTitle=driver.getTitle();
		Assert.assertTrue(actualTitle.contains(expTitle),"not navigate to contacts");
		Reporter.log("Navigated to create new Contact Page",true);


		//creating contact with lead source
		CreateNewContactPage cp = new CreateNewContactPage(driver);
		lastName=eLib.getDataFromExcel("contact", 9, 2);
		String leadSource = eLib.getDataFromExcel("contact", 9, 4);
		cp.getLeadSrcLtbx().sendKeys(leadSource);
		cp.createContact(lastName);
		ContactInformationPage contactInfo = new ContactInformationPage(driver);
		wLib.waitForElementVisible(driver, contactInfo.getsuccessfullMsg());
		String actualMsg = contactInfo.getsuccessfullMsg().getText();
		Assert.assertTrue(actualMsg.contains(lastName),"Dont have expected lastname value");


		String actualLdsource = contactInfo.getLeadSourceMsg().getText();
		Assert.assertTrue(actualLdsource.trim().equals(leadSource),"Dont have expected Leadsource value");
		Reporter.log("LeadSource "+leadSource+":Pass",true);
	}


	
}
