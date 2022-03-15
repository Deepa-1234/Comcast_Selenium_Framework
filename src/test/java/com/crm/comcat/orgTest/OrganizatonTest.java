package com.crm.comcat.orgTest;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.crm.GenericLibrary.BaseClass;
import com.crm.comcat.objectrepositorylib.CreateNewOrganizationPage;
import com.crm.comcat.objectrepositorylib.HomaPage;
import com.crm.comcat.objectrepositorylib.OrganizationsPage;
import com.crm.comcat.objectrepositorylib.OrganizatonInformationPage;
/**
 * Organization
 * @author Deepa
 *
 */
public class OrganizatonTest extends BaseClass {


	String actualTitle;
	String expTitle;
	/**
	 * Method to create new Organization
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	@Test(groups = "smokeTest")
	public void createOrgTest() throws EncryptedDocumentException, InvalidFormatException, IOException {

		//Navigating to OganizaionPage
		HomaPage hp = new HomaPage(driver);
		hp.getOrganizationLink().click();
		expTitle="Organizations";
		wLib.waitForElementVisible(driver, expTitle);
		actualTitle=driver.getTitle();
		Assert.assertTrue(actualTitle.contains(expTitle),"Not navigated to Organisation");
		Reporter.log("Navigated to Organization",true);

		//Navigating to create new Organization Page
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgImg().click();
		expTitle="Organizations";
		wLib.waitForElementVisible(driver, expTitle);
		actualTitle=driver.getTitle();

		Assert.assertTrue(actualTitle.contains(expTitle),"Not navigated to create new Organization");
		Reporter.log("Navigated to create new Organization",true);

		//Creating new organization
		CreateNewOrganizationPage cp = new CreateNewOrganizationPage(driver);
		orgName=eLib.getDataFromExcel("organization", 4, 2)+jLib.getRandomNumber();
		cp.createOrg(orgName);

		OrganizatonInformationPage orgInfo = new OrganizatonInformationPage(driver);
		wLib.waitForElementVisible(driver, orgInfo.getsuccessfullMsg());
		String actualMsg = orgInfo.getsuccessfullMsg().getText();
		Assert.assertTrue(actualMsg.contains(orgName));
		Reporter.log("Organization created Successfully:Pass",true);
	}

	/**
	 * Method to create organization with industry and type
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	@Test(groups = {"IntegrationTest","SystemTest"})
	public void createNewOrgWithIndustryTypeTest() throws EncryptedDocumentException, InvalidFormatException, IOException {
		//Navigating to Organization page
		HomaPage hp = new HomaPage(driver);
		hp.getOrganizationLink().click();
		expTitle="Organizations";
		wLib.waitForElementVisible(driver, expTitle);
		actualTitle=driver.getTitle();
		Assert.assertTrue(actualTitle.contains(expTitle),"Not navigated to Organization");
		Reporter.log("Navigated to Organization",true);


		//Navigating to create new organization page
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgImg().click();
		expTitle="Organizations";
		wLib.waitForElementVisible(driver, expTitle);
		actualTitle=driver.getTitle();
		Assert.assertTrue(actualTitle.contains(expTitle),"Not navigated to create new Organization");
		Reporter.log("Navigated to create new Organization",true);


		//creating organization with industry and type
		CreateNewOrganizationPage cp = new CreateNewOrganizationPage(driver);
		orgName=eLib.getDataFromExcel("organization", 4, 2)+jLib.getRandomNumber();
		String industry=eLib.getDataFromExcel("organization", 5, 3);
		String type=eLib.getDataFromExcel("organization", 5, 4);
		cp.createOrg(orgName,industry,type);
		OrganizatonInformationPage orgInfo = new OrganizatonInformationPage(driver);
		wLib.waitForElementVisible(driver, orgInfo.getsuccessfullMsg());
		String actualType=orgInfo.getTypeMsg().getText();
		String actualIndusty=orgInfo.getindustryMsg().getText();
		String actualMsg = orgInfo.getsuccessfullMsg().getText();
		
		Assert.assertTrue(actualMsg.contains(orgName),"Organization not created");
		Reporter.log("Organization created Successfully:Pass",true);
		
		Assert.assertTrue(actualType.contains(type),"Organization not created with specified type");
		Reporter.log("Type :"+type,true);
		
		Assert.assertTrue(actualIndusty.contains(industry),"Organization not created with specified industry");
		Reporter.log("Industry :"+industry,true);
		
		
		
		

	}

	@Test(groups = "SystemTest")
	public void createOrgWithIndustryTest() throws IOException, EncryptedDocumentException, InvalidFormatException {
		//Navigating to Organization page
		HomaPage hp = new HomaPage(driver);
		hp.getOrganizationLink().click();
		expTitle="Organizations";
		wLib.waitForElementVisible(driver, expTitle);
		String actualTitle=driver.getTitle();
		Assert.assertTrue(actualTitle.contains(expTitle),"Not navigated to Organization");
		Reporter.log("Navigated to Organization",true);
		

		//Navigating to create new organization page
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgImg().click();
		expTitle="Organizations";
		wLib.waitForElementVisible(driver, expTitle);
		actualTitle=driver.getTitle();
		Assert.assertTrue(actualTitle.contains(expTitle),"Not Navigated to create new Organization");
		Reporter.log("Navigated to create new Organization",true);
		

		//creating new organization with industry
		CreateNewOrganizationPage cp = new CreateNewOrganizationPage(driver);
		orgName=eLib.getDataFromExcel("organization", 4, 2)+jLib.getRandomNumber();
		
		String industry=eLib.getDataFromExcel("organization", 4, 3);
		
		cp.createOrg(orgName,industry);
		
		OrganizatonInformationPage orgInfo = new OrganizatonInformationPage(driver);
		wLib.waitForElementVisible(driver, orgInfo.getsuccessfullMsg());
		
		String actualIndusty=orgInfo.getindustryMsg().getText();
		String actualMsg = orgInfo.getsuccessfullMsg().getText();
		
		Assert.assertTrue(actualMsg.trim().contains(orgName),"Organization not created");
		Reporter.log("Organization created successfully");
		
		Assert.assertTrue(actualIndusty.trim().contains(industry),"Organization not created with"+industry);
		Reporter.log("Organization created successfully with"+industry,true);
		
	}
}
