package com.crm.comcat.objectrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Pom for CreateNewOrganizationPage
 * @author Deepa
 *
 */
public class CreateNewOrganizationPage {
	
	public CreateNewOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name= "accountname")
	private WebElement orgNameEdt;
	
	@FindBy(name = "industry")
	private WebElement indstryEdt;
	
	@FindBy(name = "accounttype")
	private WebElement accounttypeEdt;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	
	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}


	public WebElement getAccounttypeEdt() {
		return accounttypeEdt;
	}

	public WebElement getIndstryEdt() {
		return indstryEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}


	public void createOrg(String orgName) {
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
	}
	
	public void createOrg(String orgName,String industry) {
		orgNameEdt.sendKeys(orgName);
		indstryEdt.sendKeys(industry);
		saveBtn.click();
	}
	public void createOrg(String orgName,String industry,String type) {
		orgNameEdt.sendKeys(orgName);
		indstryEdt.sendKeys(industry);
		accounttypeEdt.sendKeys(type);
		saveBtn.click();
	}
}
