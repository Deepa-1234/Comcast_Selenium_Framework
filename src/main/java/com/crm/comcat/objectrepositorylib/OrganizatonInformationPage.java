package com.crm.comcat.objectrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Pom for OrganizatonInformationPage
 * @author Deepa
 *
 */
public class OrganizatonInformationPage {
	
	public OrganizatonInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement successfullMsg;
	
	@FindBy(xpath = "//span[@id='dtlview_Industry']")
	private WebElement industryMsg;
	
	@FindBy(xpath = "//span[@id='dtlview_Type']")
	private WebElement typeMsg;
	
	public WebElement getsuccessfullMsg() {
		return successfullMsg;
	}
	
	public WebElement getindustryMsg() {
		return industryMsg;
	}
	
	public WebElement getTypeMsg() {
		return typeMsg;
	}
}
