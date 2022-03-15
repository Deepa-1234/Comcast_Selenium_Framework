package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class SelectFromMysql {

	public static void main(String[] args) throws SQLException {
		Connection conn=null;
		try {
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
			String query="select * from project";
			Statement stmt=conn.createStatement();
			ResultSet resSet=stmt.executeQuery(query);
			while(resSet.next()) {
				String data=resSet.getString(1);
				System.out.println(data);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());			
		}
		finally {
			conn.close();
		}
		
	}

}
