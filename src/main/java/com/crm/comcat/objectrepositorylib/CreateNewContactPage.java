package com.crm.comcat.objectrepositorylib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

/**
 * POM of Create new contact page
 * @author Deepa
 *
 */
public class CreateNewContactPage extends WebDriverUtility{
	WebDriver driver = null;
	public CreateNewContactPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name= "lastname")
	private WebElement lastNameEdt;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img[@alt='Select']")
	private WebElement orgImg;
	
	@FindBy(xpath = "//input[@name='contact_name']/following-sibling::img[@alt='Select']")
	private WebElement reportsToImg;
	
	@FindBy(xpath = "//input[@name='contact_name']/../input[@title='Clear']")
	private WebElement clearImg;

	@FindBy(xpath = "//input[@name='account_name']")
	private WebElement orgEdt;
	
	@FindBy(xpath = "//input[@name='imagename']")
	private WebElement chooseFileBtn;
	
	@FindBy(xpath = "//input[@name='imagename_hidden']")
	private WebElement imageNameHiddenImg;
	
	@FindBy(xpath = "//input[@value='U']")
	private WebElement assignUserBtn;
	
	@FindBy(xpath = "//input[@value='T']")
	private WebElement assignGroupBtn;
	
	@FindBy(xpath = "//select[@name='assigned_group_id']")
	private WebElement assignedGroupLtbx; 
	
	@FindBy(name = "contact_name")
	private WebElement reportsToEdt;
	
	@FindBy(name ="leadsource")
	private WebElement leadSrcLtbx;
	
	@FindBy(xpath = "//select[@name='assigned_user_id']")
	private WebElement assignedUserLtbx; 
	
	public WebElement getLeadSrcLtbx() {
		return leadSrcLtbx;
	}

	public WebElement getReportsToEdt() {
		return reportsToEdt;
	}

	public void setAssignedGroupLtbx(WebElement assignedGroupLtbx) {
		this.assignedGroupLtbx = assignedGroupLtbx;
	}

	
	
	public WebElement getAssignedUserLtbx() {
		return assignedUserLtbx;
	}

	public WebElement getAssignGroupBtn() {
		return assignGroupBtn;
	}

	public WebElement getAssignedGroupLtbx() {
		return assignedGroupLtbx;
	}

	public WebElement getAssignUserBtn() {
		return assignUserBtn;
	}

	public WebElement getImageNameHiddenImg() {
		return imageNameHiddenImg;
	}

	public WebElement getChooseFileBtn() {
		return chooseFileBtn;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getOrgEdt() {
		return orgEdt;
	}

	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}
	
	public WebElement getOrgImg() {
		return orgImg;
	}


	public WebElement getClearImg() {
		return clearImg;
	}

	public WebElement getReportsToImg() {
		return reportsToImg;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createContact(String contactName) {
		lastNameEdt.sendKeys(contactName);
		saveBtn.click();
	}
	
	/**
	 * method to create contact with organization name
	 * @param contactName
	 * @param orgName
	 */
	public void createContact(String contactName,String orgName) {
		lastNameEdt.sendKeys(contactName);
		orgImg.click();
		swithchToWindow(driver, "Accounts&action");
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getSearchEdt().sendKeys(orgName);
		op.getSearchBtn().click();
		driver.findElement(By.xpath("//a[.='"+orgName+"']")).click();
		swithchToWindow(driver,"Contacts&action");
		saveBtn.click();
	}
	

	/**
	 * method to select organization from Accounts&action page
	 * @param contactName
	 * @param orgName
	 * @return
	 */
	public String selectOrgName(String orgName) {
		orgImg.click();
		swithchToWindow(driver, "Accounts&action");
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getSearchEdt().sendKeys(orgName);
		op.getSearchBtn().click();
		driver.findElement(By.xpath("//a[.='"+orgName+"']")).click();
		swithchToWindow(driver,"Contacts&action");
		waitForElementVisible(driver, orgEdt);
		String orgEdtValue =orgEdt.getAttribute("value");
		return orgEdtValue;
	}
	
	/**
	 * method to enter lastName in create contact page
	 * @param contactName
	 */
	public void createContactWithOutSave(String contactName) {
		lastNameEdt.sendKeys(contactName);
	}
	
	/**
	 * method to select report to contact
	 * @param reportsTocontact
	 */
	public void selectingWithReportTo(String reportsTocontact) {
		reportsToImg.click();
		swithchToWindow(driver, "Contacts&action");
		ContactsPage contactCp = new ContactsPage(driver);
		contactCp.getSearchEdt().sendKeys(reportsTocontact);
		contactCp.getSearchBtn().click();
		driver.findElement(By.xpath("//a[contains(text(),'"+reportsTocontact+"')]")).click();	
		swithchToWindow(driver,"Contacts&action");
	}
	
}
