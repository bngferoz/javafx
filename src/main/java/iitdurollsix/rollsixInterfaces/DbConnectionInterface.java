package iitdurollsix.rollsixInterfaces;

import java.sql.SQLException;

import iitdurollsix.exception.RollSixCustomException;

public interface DbConnectionInterface {
	boolean validateUserNamePassword(String userName, String password) throws RollSixCustomException, SQLException;
	boolean registerUser(String email, String password, String firstName, String lastName, String address) throws RollSixCustomException, SQLException;
}
