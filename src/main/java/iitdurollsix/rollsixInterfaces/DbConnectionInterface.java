package iitdurollsix.rollsixInterfaces;

import java.sql.SQLException;

import entity.User;
import iitdurollsix.exception.RollSixCustomException;

public interface DbConnectionInterface {
	String validateUserNamePassword(String userName, String password) throws RollSixCustomException, SQLException;
	boolean registerUser(String email, String password, String firstName, String lastName, String address) throws RollSixCustomException, SQLException;
	User getUserByUsername(String userName) throws RollSixCustomException, SQLException;
	boolean editProfile(String userName, String firstName, String lastName, String address) throws RollSixCustomException, SQLException;
	boolean changePassword(String userName, String newPassword, String txtExistingPass)throws RollSixCustomException, SQLException;
	boolean forgotPassword(String userName, String newPassword) throws RollSixCustomException, SQLException;
}
