package com.crm.comcat.objectrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Pom of ContactsPage page
 * @author Deepa
 *
 */
public class ContactsPage {
	public ContactsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//img[@alt='Create Contact...']")
	private WebElement createContactImg;

	@FindBy(name= "search_text")
	private WebElement searchEdt;
	
	@FindBy(name = "search")
	private WebElement searchBtn;

	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}
	public WebElement getCreateContactImg() {
		return createContactImg;
	}
	
	
}
