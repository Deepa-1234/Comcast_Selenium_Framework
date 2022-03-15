package com.crm.GenericLibrary;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerImpClass implements ITestListener{

public void onTestFailure(ITestResult result) {
	JavaUtility ju = new JavaUtility();
	String tcName = result.getMethod().getMethodName();
	tcName = tcName+ju.getSystemDate_YYYY_MM_DD();
	
	EventFiringWebDriver eDriver= new EventFiringWebDriver(BaseClass.sDriver);
	File srcFile = eDriver.getScreenshotAs(OutputType.FILE);

	try {
		FileUtils.copyFile(srcFile, new File("./screenshot/"+tcName+".png"));
	} catch (IOException e) {
		
	}
}

}
