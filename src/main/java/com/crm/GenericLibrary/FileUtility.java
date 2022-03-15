package com.crm.GenericLibrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * This class provides generic methods to read data from property file
 * @author Deepa
 *
 */
public class FileUtility {

	/**
	 * This method will read the data from property file based on key which
	 * you pass as an argument and it will return the value
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String readDataFromPropertyFile(String key) throws IOException {
		FileInputStream fis=new FileInputStream(IpathConstants.FilePath);
		Properties pLoad=new Properties();
		pLoad.load(fis);
		String value=pLoad.getProperty(key);
		return value;
	}
}
