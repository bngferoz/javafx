package iitdurollsix.rollsixInterfacesImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import iitdurollsix.exception.RollSixCustomException;
import iitdurollsix.rollsixInterfaces.DbConnectionInterface;

public class DbConnectionImpl implements DbConnectionInterface {

	Properties properties = new Properties();
	String dbUrl = "";
	String dbUsername = "";
	String dbPassword = "";
	Connection con = null;
	
	public DbConnectionImpl() {
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            
			properties.load(inputStream);
            this.dbUrl = properties.getProperty("db.url");
    		this.dbUsername = properties.getProperty("db.username");
    		this.dbPassword = properties.getProperty("db.password");
    		
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
            
        }
	}
	@Override
	public boolean validateUserNamePassword(String userName, String password) throws RollSixCustomException {
		
		
		try{
			con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    		PreparedStatement obj = con.prepareStatement("select email from user where email=? and password = ?");
			obj.setString(1, userName);
			obj.setString(2, password);
			ResultSet rs = obj.executeQuery();
			if(rs.next()) {
				return true;
			}
			con.close();
			return false;
				   
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RollSixCustomException(e.getLocalizedMessage(), LocalDateTime.now());
		}
	}

	@Override
	public boolean registerUser(String email, String password, String firstName, String lastName, String address)
			throws RollSixCustomException {
		try {

			con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    		PreparedStatement pst = con.prepareStatement("insert into user(email,first_name,last_name,address,password) values(?,?,?,?,?)");
			pst.setString(1, email);
			pst.setString(2, firstName);
			pst.setString(3, lastName);
			pst.setString(4, address);
			pst.setString(5, password);
			pst.executeUpdate();
			con.close();
			return true;
		}catch (SQLException e ) {
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
			throw new RollSixCustomException(e.getLocalizedMessage(), LocalDateTime.now());
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
			throw new RollSixCustomException(e.getLocalizedMessage(), LocalDateTime.now());
		}
	}

}

