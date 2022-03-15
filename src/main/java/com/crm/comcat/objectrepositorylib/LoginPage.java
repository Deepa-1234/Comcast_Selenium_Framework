package com.crm.comcat.objectrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Pom of Login page
 * @author Deepa
 *
 */
public class LoginPage {
	WebDriver driver=null;
	public LoginPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name = "user_name")
	private WebElement userNameEdt;

	@FindBy(name = "user_password")
	private WebElement passWordEdt;

	@FindBy(id ="submitButton")
	private WebElement loginBtn;

	public WebElement getUserNameEdt() {
		return userNameEdt;
	}

	public WebElement getPassWordEdt() {
		return passWordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}

	/**
	 * This method is used to login to application
	 * @param username
	 * @param password
	 */
	public void loginApp(String username,String password,String url) {
		driver.get(url);
		userNameEdt.sendKeys(username);
		passWordEdt.sendKeys(password);
		loginBtn.click();

	}
}
