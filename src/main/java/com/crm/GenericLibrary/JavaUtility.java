package com.crm.GenericLibrary;

import java.util.Date;
import java.util.Random;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * This class provides generic methods with respect to java
 * @author Deepa
 *
 */
public class JavaUtility {
	/**
	 * This method will generate random number
	 * @return
	 */
	public int getRandomNumber()
	{
		Random ran=new Random();
		int randomNum=ran.nextInt(1000);
		return randomNum;
	}
	
	/**
	 * This method will return current date
	 * @return
	 */
	public String getSystemDate() {
		Date date=new Date();
		String cuurentDate=date.toString();
		return cuurentDate;	
	}
	
	/**
	 * This method will return the date in specified format
	 * @return
	 */
	public String getSystemDate_YYYY_MM_DD() {
		Date date=new Date();
		String d=date.toString();
		String[] dt=d.split(" ");
		String YYYY=dt[5];
		String DD=dt[2];
		String MM=dt[1];
		String today=YYYY+"-"+MM+"-"+DD;
		return today;
	}
	
	/**
	 * This method is used to pass Virtual key to OS
	 * @throws AWTException
	 */
	
	public void pressVirtualEnterKey() throws AWTException
	{
		Robot rc= new Robot();
		rc.keyPress(KeyEvent.VK_ENTER);
		rc.keyRelease(KeyEvent.VK_ENTER);
	}
	
}
