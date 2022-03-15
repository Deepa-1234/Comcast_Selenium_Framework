package com.crm.comcat.objectrepositorylib;
/**
 * POM of Email compose page
 * @author Deepa
 *
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmailComposePage {
	public EmailComposePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Compose")
	private WebElement composLink;

	public WebElement getComposLink() {
		return composLink;
	}
	
}
