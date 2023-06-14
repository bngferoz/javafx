package iitdurollsix.component.dbconnection;

import iitdurollsix.exception.RollSixCustomException;

public interface DbConnectionInterface {
	boolean validateUserNamePassword(String userName, String password) throws RollSixCustomException;
}
