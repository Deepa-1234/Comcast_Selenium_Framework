package com.crm.comcat.objectrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * PPM Contact Information page
 * @author Deepa
 *
 */
public class ContactInformationPage {
	
	public ContactInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement successfullMsg;
	
	@FindBy(id = "dtlview_Assigned To")
	private WebElement assignToMsg;
	
	@FindBy(id = "mouseArea_Lead Source")
	private WebElement leadSourceMsg;
	
	public WebElement getLeadSourceMsg() {
		return leadSourceMsg;
	}

	public WebElement getAssignToMsg() {
		return assignToMsg;
	}

	public WebElement getsuccessfullMsg() {
		return successfullMsg;
	}
	
}
