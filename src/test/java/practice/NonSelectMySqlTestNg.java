package practice;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;
public class NonSelectMySqlTestNg {

	@Test
	public  void nonSelectTest() throws SQLException {
		Connection conn=null;
		boolean flag=false;
		try {
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
			Statement stmt=conn.createStatement();
			String query="insert into project values('TY5009','deepak','12/01/2022','kotak_123','ongoing',8)";
			String ExpectedResult="kotak_123";
			int result=stmt.executeUpdate(query);
			String query1="select * from project";
			ResultSet resSet=stmt.executeQuery(query1);
			String actualResut=null;
			while(resSet.next()) 
			{

				actualResut=resSet.getString(4);
				try {
					Assert.assertEquals(actualResut, ExpectedResult);
					flag=true;
					break;
				}catch(Exception e){

				}


			}
			if(flag) {
				Reporter.log(actualResut+"found");
			}
			
		}
		catch (SQLException e) 
		{
			Assert.fail();
			Reporter.log(e.getMessage());
		}


		finally {
			conn.close();
		}

	}	}
