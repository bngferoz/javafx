package iitdurollsix.component.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;

import iitdurollsix.exception.RollSixCustomException;

public class DbConnectionImpl implements DbConnectionInterface {

	@Override
	public boolean validateUserNamePassword(String userName, String password) throws RollSixCustomException {
		try (Connection con = DriverManager
				  .getConnection("jdbc:mysql://localhost:3306/rollsix", "root", "root")) {
			Statement statement = con.createStatement();
			//statement.executeUpdate("create table Temp (col1 char(5), col2 char(5))");
			ResultSet resultSet = statement.executeQuery("select city from address");
			while(resultSet.next()) {
				System.out.println(resultSet.getString(1));
				
			}
				   
		}catch (Exception e) {
			e.printStackTrace();
			throw new RollSixCustomException(password, LocalDateTime.now());
		}
		return false;
	}

}
