package iitdurollsix.rollsixInterfaces;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import dto.UserDto;
import entity.Product;
import entity.User;
import iitdurollsix.exception.RollSixCustomException;
import javafx.event.ActionEvent;

public interface DbConnectionInterface {
	String validateUserNamePassword(String userName, String password) throws RollSixCustomException, SQLException;
	boolean registerUser(String email, String password, String firstName, String lastName, String address, String role) throws RollSixCustomException, SQLException;
	User getUserByUsername(String userName) throws RollSixCustomException, SQLException;
	boolean editProfile(String userName, String firstName, String lastName, String address) throws RollSixCustomException, SQLException;
	boolean changePassword(String userName, String newPassword, String txtExistingPass)throws RollSixCustomException, SQLException;
	boolean forgotPassword(String userName, String newPassword) throws RollSixCustomException, SQLException;
	List<UserDto> getAdmins(String userName) throws SQLException;
	boolean addProduct(String name, BigDecimal price, BigDecimal quantity)throws RollSixCustomException, SQLException;
	boolean deleteProductByName(String name) throws RollSixCustomException, SQLException;
	List<Product> getAllProducts() throws SQLException;
	boolean deleteUserByEmail(String email) throws RollSixCustomException, SQLException;
	List<UserDto> getUserByRole(String role) throws RollSixCustomException, SQLException;
}
