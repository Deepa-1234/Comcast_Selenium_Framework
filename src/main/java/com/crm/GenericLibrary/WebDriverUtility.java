package com.crm.GenericLibrary;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * This class provides generic methods for webdriver actions
 * @author Deepa
 *
 */
public class WebDriverUtility {

	/**
	 * This method will maximize the window
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	/**
	 * This method waits for 20sec for page loading
	 * @param driver
	 */
	public void waitUntillPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
	}

	/**
	 * This method waits for element to be visible
	 * @param driver
	 * @param element
	 */
	public void waitForElementVisible(WebDriver driver, WebElement element) 
	{
		WebDriverWait wait= new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method waits for title contains
	 * @param driver
	 * @param element
	 */
	public void waitForElementVisible(WebDriver driver,String title) 
	{
		WebDriverWait wait= new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleContains(title));
	}
	
	

	/**
	 * This method waits for the element to be clicked,its custom wait created to 
	 * avoid elementNotInteractable exception
	 * @param element
	 * @throws InterruptedException
	 */
	public void waitAndClick(WebElement element) throws InterruptedException
	{
		int count=0;
		while(count<20) {
			try {
				element.click();break;
			}catch (Throwable e) {
				Thread.sleep(1000);
				count++;
			}
		}	
	}


	/**
	 * this method enables the user to handle drop down using index
	 * @param element
	 * @param index
	 */
	public void select(WebElement element,int index) {
		Select select=new Select(element);
		select.selectByIndex(index);
	}

	/**
	 * this method enables the user to handle drop down using index
	 * @param element
	 * @param visible text
	 */
	public void select(WebElement element,String text) {
		Select select=new Select(element);
		select.selectByVisibleText(text);
	}

	/**
	 * this method enables the user to handle drop down using value
	 * @param value
	 * @param element
	 */
	public void select(String value,WebElement element) {
		Select select=new Select(element);
		select.selectByValue(value);
	}

	/**
	 * This method will perform mouse over action
	 * @param driver
	 * @param element
	 */
	public void mouseOver(WebDriver driver, WebElement element) {
		Actions actions= new Actions(driver);
		actions.moveToElement(element).perform();
	}

	/**
	 * This method will perform double click
	 * @param driver
	 * @param element
	 */
	public void doubleClick(WebDriver driver, WebElement element) {
		Actions actions= new Actions(driver);
		actions.doubleClick(element).perform();
	}

	/**
	 * This method will perform right click
	 * @param driver
	 * @param element
	 */
	public void rightClick(WebDriver driver, WebElement element) 
	{
		Actions actions= new Actions(driver);
		actions.contextClick(element).perform();
	}

	/**
	 * This method will perform drag and drop
	 * @param driver
	 * @param element
	 */
	public void dragAndDrop(WebDriver driver, WebElement src,WebElement dest)
	{
		Actions actions= new Actions(driver);
		actions.dragAndDrop(src,dest).perform();
	}
	
	/**
	 * Accept the alert popup
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver) 
	{
		driver.switchTo().alert().accept();	
	}
	
	/**
	 * Cancel the alert popUp
	 * @param driver
	 */
	public void cancelAlert(WebDriver driver) 
	{
		driver.switchTo().alert().dismiss();	
	}
	
	/**
	 * This method takes screen shot and saves in the destination
	 * @param driver
	 * @param screenShotName
	 * @throws IOException
	 */
	public void getScreenshot(WebDriver driver,String screenShotName) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File dest= new File("./screenshot/"+screenShotName+".PNG");
		Files.copy(src, dest);
	}
	
	/**
	 * This method used for scrolling action in a webpage until the element
	 * @param driver
	 * @param element
	 */
	public void scrollToElement(WebDriver driver,WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		int y=element.getLocation().getY();
		js.executeScript("window.scrollBy(0,"+y+")", element);
	}
	
	/**
	 * This method used for scrolling action in a webpage
	 * @param driver
	 */
	public void scrollToElement(WebDriver driver) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		
		js.executeScript("window.scrollBy(0,500)","");
	}
	
	/**
	 * This method switch to window depending upon partial title
	 * @param driver
	 * @param partialTitle
	 */
	public void swithchToWindow(WebDriver driver,String partialTitle)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String winId=it.next();
			driver.switchTo().window(winId);
			String currentTitle=driver.getTitle();
			if(currentTitle.contains(partialTitle)){
				break;
			}
		}
	}
	
	/**
	 * This method switch to frame based on index
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method switch to frame based on id or name
	 * @param driver
	 * @String idOrName
	 */
	public void switchToFrame(WebDriver driver,String idOrName) {
		driver.switchTo().frame(idOrName);
	}
	
	/**
	 * This method switch to frame based on web element
	 * @param driver
	 * @param element
	 */
	public void switchToFrame(WebDriver driver,WebElement element) {
		driver.switchTo().frame(element);
	}
	
	/**
	 * This method switch to outermost frame
	 * @param driver
	 */
	public void switchToDefaultFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	/**
	 * This method switch to parent frame
	 * @param driver
	 */
	public void switchToParentFrame(WebDriver driver) {
		driver.switchTo().parentFrame();
	}
}
