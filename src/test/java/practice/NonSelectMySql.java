package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class NonSelectMySql {
	public static void main(String[] args) throws SQLException {
		Connection conn=null;
		boolean flag=false;
		try {
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
			Statement stmt=conn.createStatement();
			String query="insert into project values('TY_proj_2000','deepak','12/01/2022','hdfc_2','created',8)";
			int result=stmt.executeUpdate(query);
			if(result>0) {
				flag=true;
			}
			System.out.println("created successfully");

		} catch (SQLException e) {
			if(flag==false) {
				System.out.println("not created");
			}

		}
		finally {
			conn.close();
		}
	}
}
