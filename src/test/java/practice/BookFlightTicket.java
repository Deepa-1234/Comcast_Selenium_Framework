package practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BookFlightTicket {
	@Test(dataProvider = "dp_bookTicketTest")
	public void bookTicketTest(String src,String dest,int noOfTks) {
		System.out.println("Book ticket from "+src+" to "+dest+"with "+noOfTks+"ticlts");
	}
@DataProvider
public Object[][] dp_bookTicketTest(){
	Object[][] obj = new Object[5][3];
	obj[0][0] = "Bangalore";
	obj[0][1] = "Goa";
	obj[0][2] = 20;
	obj[1][0] = "Hyd";
	obj[1][1] = "Bng";
	obj[1][2] = 10;
	obj[2][0] = "Mumbai";
	obj[2][1] = "Bng";
	obj[2][2] = 100;
	obj[3][0] = "Pune";
	obj[3][1] = "Delhi";
	obj[3][2] = 2;
	obj[4][0] = "Delhi";
	obj[4][1] = "Hyd";
	obj[4][2] = 30;

	return obj;
	
}
}
