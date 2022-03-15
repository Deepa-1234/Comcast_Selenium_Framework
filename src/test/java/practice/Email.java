package practice;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.comcat.objectrepositorylib.ContactsPage;
import com.crm.comcat.objectrepositorylib.CreateNewContactPage;
import com.crm.comcat.objectrepositorylib.EmailComposePage;
import com.crm.comcat.objectrepositorylib.HomaPage;

public class Email extends BaseClass{
	
	@Test
	public void openBrowserTest() throws IOException, EncryptedDocumentException, InvalidFormatException {
		
		//Navigating to Emial page
		HomaPage hp = new HomaPage(driver);
		hp.getContactsLink().click();
		ContactsPage op = new ContactsPage(driver);
		op.getCreateContactImg().click();
		CreateNewContactPage cp = new CreateNewContactPage(driver);
		lastName=eLib.getDataFromExcel("contact", 4, 2);
		cp.createContact(lastName);	
		
		
		String expTitle = "Email";
		wLib.swithchToWindow(driver, expTitle);

		wLib.waitForElementVisible(driver, hp.getEmailLink());;

		hp.getEmailLink().click();
		String actTitle = driver.getTitle();
		assertTrue(actTitle.contains(expTitle),"Not navigated to email page");
		Reporter.log("Navigate to Email page",true);
		
		
		//Navigating to email compose page
		EmailComposePage eCpg = new EmailComposePage(driver);
		eCpg.getComposLink().click();
		expTitle = "Administrator - Email";
		wLib.waitForElementVisible(driver, expTitle);
		actTitle =  driver.getTitle();
		Assert.assertTrue(actTitle.contains(expTitle),"Nor navigted to email compose page");
		Reporter.log("Navigated to email compose page");
		
		wLib.swithchToWindow(driver, expTitle);
		
//		Set<String> allwh = driver.getWindowHandles();
//		String parent=driver.getWindowHandle();
//		for(String wh:allwh) 
//		{
//			driver.switchTo().window(wh);
//			if(!wh.equals(parent))
//			{
//				driver.findElement(By.xpath("//img[@alt='Select']")).click();
//				Set<String> contactAllwh = driver.getWindowHandles();
//				String cantactParent=driver.getWindowHandle();
//				for(String wh1:contactAllwh) 
//				{
//					driver.switchTo().window(wh1);
//					if(!wh1.equals(cantactParent)) 
//					{
//						driver.findElement(By.xpath("(//a)[6]")).click();
//					}
//				}
//				driver.switchTo().window(cantactParent);
//				driver.findElement(By.name("subject")).sendKeys("Test mail");
//				//driver.findElement(By.xpath("//p")).sendKeys("Hello this is a test mail");
//				WebElement ele = driver.findElement(By.xpath("//iframe[contains(@title,'Rich text editor')]"));
//				driver.switchTo().frame(ele);
//				System.out.println("Hai");
//				String mail="Hi, "+'\n'+"This is a test mail."+'\n'+ "Thanks & Regards,"+ '\n'+"Deepa.";
//				driver.findElement(By.xpath("//html[@dir='ltr']/body")).sendKeys(mail);
			
	}
}