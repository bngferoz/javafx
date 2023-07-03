package iitdurollsix.rollsixInterfacesImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import dto.UserDto;
import entity.Product;
import entity.User;
import iitdurollsix.exception.RollSixCustomException;
import iitdurollsix.rollsixInterfaces.DbConnectionInterface;
import javafx.event.ActionEvent;

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
	public boolean registerUser(String email, String password, String firstName, String lastName, String address, String role)
			throws RollSixCustomException {
		try {
			con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    		
			if(!userExist(email, con)) {
				PreparedStatement pst = con.prepareStatement("insert into user(email,first_name,last_name,address,password, role) values(?,?,?,?,?,?)");
				pst.setString(1, email);
				pst.setString(2, firstName);
				pst.setString(3, lastName);
				pst.setString(4, address);
				pst.setString(5, password);
				pst.setString(6, role);
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
		PreparedStatement pst = con.prepareStatement("select user_id,email,first_name,last_name,address,password, role from user where email=?");
		pst.setString(1, userName);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			user.setUser_id(rs.getInt(1));
			user.setEmail(rs.getString(2));
			user.setFirst_name(rs.getString(3));
			user.setLast_name(rs.getString(4));
			user.setAddress(rs.getString(5));
			user.setPassword(rs.getString(6));
			user.setRole(rs.getString(7));
			return user;
		}
		con.close();
		throw new RollSixCustomException("User not found in the database!", LocalDateTime.now());
	}
	@Override
	public List<UserDto> getAdmins(String userName) throws SQLException{
		List<UserDto> users = new ArrayList<>();
		con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		PreparedStatement pst = con.prepareStatement("select email,first_name,last_name, address from user where email not in (?) and role=?");
		pst.setString(1, userName);
		pst.setString(2, "admin");
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			UserDto user = new UserDto();
			user.setEmail(rs.getString(1));
			user.setFirst_name(rs.getString(2));
			user.setLast_name(rs.getString(3));
			user.setAddress(rs.getString(4));
			users.add(user);
		}
		con.close();
		return users;
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
	@Override
	public boolean addProduct(String name, BigDecimal price, BigDecimal quantity) throws RollSixCustomException, SQLException {
		try {
			con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    		
			if(!productExist(name, con)) {
				PreparedStatement pst = con.prepareStatement("insert into product(name,price,quantity) values(?,?,?)");
				pst.setString(1, name);
				pst.setBigDecimal(2, price);
				pst.setBigDecimal(3, quantity);
				pst.executeUpdate();
				con.close();
				return true;
			}
			else {
				throw new RollSixCustomException(name+" already exists!", LocalDateTime.now());
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
	private boolean productExist(String name, Connection con) throws SQLException {
		PreparedStatement pst = con.prepareStatement("select id from product where name=?");
		pst.setString(1, name);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			return true;
		}
		return false;
	}
	@Override
	public boolean deleteProductByName(String name) throws RollSixCustomException, SQLException {
		try {
			con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    		
			if(productExist(name, con)) {
				PreparedStatement pst = con.prepareStatement("delete from product where name = ?");
				pst.setString(1, name);
				pst.executeUpdate();
				con.close();
				return true;
			}
			else {
				throw new RollSixCustomException(name+" does not exists!", LocalDateTime.now());
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
	@Override
	public List<Product> getAllProducts() throws SQLException {
		List<Product> products = new ArrayList<>();
		con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		PreparedStatement pst = con.prepareStatement("select name,price,quantity from product");
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			Product product = new Product();
			product.setName(rs.getString(1));
			product.setPrice(rs.getBigDecimal(2));
			product.setQuantity(rs.getBigDecimal(3));
			products.add(product);
		}
		con.close();
		return products;
	}
	@Override
	public boolean deleteUserByEmail(String email) throws RollSixCustomException, SQLException {
		try {
			con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    		
			if(userExist(email, con)) {
				PreparedStatement pst = con.prepareStatement("delete from user where email = ?");
				pst.setString(1, email);
				pst.executeUpdate();
				con.close();
				return true;
			}
			else {
				throw new RollSixCustomException(email+" does not exists!", LocalDateTime.now());
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
	@Override
	public List<UserDto> getUserByRole(String role) throws RollSixCustomException, SQLException {
		List<UserDto> users = new ArrayList<>();
		con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		PreparedStatement pst = con.prepareStatement("select email,first_name,last_name, address from user where role=?");
		pst.setString(1, role);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			UserDto user = new UserDto();
			user.setEmail(rs.getString(1));
			user.setFirst_name(rs.getString(2));
			user.setLast_name(rs.getString(3));
			user.setAddress(rs.getString(4));
			users.add(user);
		}
		con.close();
		return users;
	}
}

