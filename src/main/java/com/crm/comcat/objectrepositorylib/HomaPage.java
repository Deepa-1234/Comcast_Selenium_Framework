package com.crm.comcat.objectrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Pom of HomePage
 * @author Deepa
 *
 */
public class HomaPage {
	WebDriver driver= null;
	public HomaPage(WebDriver driver){
		this.driver  =driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Organizations")
	private WebElement organizationLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactsLink;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signoutLink;
	
	@FindBy(linkText = "Email")
	private WebElement emailLink;

	public WebElement getEmailLink() {
		return emailLink;
	}

	public WebElement getOrganizationLink() {
		return organizationLink;
	}

	public WebElement getContactsLink() {
		return contactsLink;
	}

	public WebElement getAdministratorImg() {
		return administratorImg;
	}

	public WebElement getSignoutLink() {
		return signoutLink;
	}
	
	/**
	 * This method logout the application
	 */
	public void logOut() {
		Actions actions=new Actions(driver);
		actions.moveToElement(administratorImg).perform();
		signoutLink.click();
	}
}
