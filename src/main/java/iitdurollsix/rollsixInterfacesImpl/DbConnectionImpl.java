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

import entity.User;
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
	public String validateUserNamePassword(String userName, String password) throws RollSixCustomException {
		
		
		try{
			con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    		PreparedStatement obj = con.prepareStatement("select email from user where email=? and password = ?");
			obj.setString(1, userName);
			obj.setString(2, password);
			ResultSet rs = obj.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
			con.close();
			throw new RollSixCustomException("Invalid Username or Password!", LocalDateTime.now());
				   
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
    		
			if(!userExist(email, con)) {
				PreparedStatement pst = con.prepareStatement("insert into user(email,first_name,last_name,address,password) values(?,?,?,?,?)");
				pst.setString(1, email);
				pst.setString(2, firstName);
				pst.setString(3, lastName);
				pst.setString(4, address);
				pst.setString(5, password);
				pst.executeUpdate();
				con.close();
				return true;
			}
			else {
				throw new RollSixCustomException(email+" already exists!", LocalDateTime.now());
			}
			
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
	private boolean userExist(String email, Connection con) throws SQLException {
		PreparedStatement pst = con.prepareStatement("select user_id,email,first_name,last_name,address,password from user where email=?");
		pst.setString(1, email);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			return true;
		}
		return false;
	}
	@Override
	public User getUserByUsername(String userName) throws RollSixCustomException, SQLException {
		User user = new User();
		con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		PreparedStatement pst = con.prepareStatement("select user_id,email,first_name,last_name,address,password from user where email=?");
		pst.setString(1, userName);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			user.setUser_id(rs.getInt(1));
			user.setEmail(rs.getString(2));
			user.setFirst_name(rs.getString(3));
			user.setLast_name(rs.getString(4));
			user.setAddress(rs.getString(5));
			user.setPassword(rs.getString(6));
			return user;
		}
		con.close();
		throw new RollSixCustomException("User not found in the database!", LocalDateTime.now());
	}
	@Override
	public boolean editProfile(String userName, String firstName, String lastName, String address)
			throws RollSixCustomException, SQLException {
		User user = getUserByUsername(userName);
		if(user == null) {
			throw new RollSixCustomException("User not found in the database!", LocalDateTime.now());
		}
		if(!firstName.equals("") && !lastName.equals("") && !address.equals("")) {
			PreparedStatement pst = con.prepareStatement("update user set first_name =?,last_name=?,address=? where email=?");
			pst.setString(1, firstName);
			pst.setString(2, lastName);
			pst.setString(3, address);
			pst.setString(4, userName);
			pst.executeUpdate();
			con.close();
			return true;
		}
		return false;
	}
	@Override
	public boolean changePassword(String userName, String newPassword, String txtExistingPass) throws RollSixCustomException, SQLException {
		if(!validateUserNamePassword(userName, txtExistingPass).equals("")) {
			User user = getUserByUsername(userName);
			if(user == null) {
				throw new RollSixCustomException("User not found in the database!", LocalDateTime.now());
			}
			if(!newPassword.equals("")) {
				PreparedStatement pst = con.prepareStatement("update user set password =? where email=?");
				pst.setString(1, newPassword);
				pst.setString(2, userName);
				pst.executeUpdate();
				con.close();
				return true;
			}
			return false;
		}
		else {
			throw new RollSixCustomException("Existing Password does not match!", LocalDateTime.now());
		}
		
	}
	@Override
	public boolean forgotPassword(String userName, String newPassword) throws RollSixCustomException, SQLException {
		User user = getUserByUsername(userName);
		if(user == null) {
			throw new RollSixCustomException("User not found in the database!", LocalDateTime.now());
		}
		if(!newPassword.equals("")) {
			PreparedStatement pst = con.prepareStatement("update user set password =? where email=?");
			pst.setString(1, newPassword);
			pst.setString(2, userName);
			pst.executeUpdate();
			con.close();
			return true;
		}
		return false;
	}

}

