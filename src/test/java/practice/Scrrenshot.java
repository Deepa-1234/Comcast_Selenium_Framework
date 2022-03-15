package practice;


import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;

@Listeners(com.crm.GenericLibrary.ListenerImpClass.class)

public class Scrrenshot extends BaseClass	{
//@Test(retryAnalyzer = com.crm.GenericLibrary.RetryAnlizerImplementation.class)
	@Test
public void test() {

System.out.println("=========start=====");
Assert.assertEquals(true, false);
}
}
