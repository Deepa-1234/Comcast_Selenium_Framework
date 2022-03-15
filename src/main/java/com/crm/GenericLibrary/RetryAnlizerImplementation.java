package com.crm.GenericLibrary;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnlizerImplementation implements IRetryAnalyzer {

	public boolean retry(ITestResult result) {
		int count=0;
		int retryLimit=1;
		if(count < retryLimit){
			count++;
			return true;
		}
		return false;
	}

}
