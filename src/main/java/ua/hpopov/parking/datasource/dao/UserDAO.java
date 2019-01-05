package ua.hpopov.parking.datasource.dao;

import java.util.List;

import ua.hpopov.parking.beans.UserBean;
import ua.hpopov.parking.beans.UserTypeBean;

public interface UserDAO extends DAO {
	void createUser(UserBean user) throws DAOOperationException;
	UserBean getUserById(int userId) throws DAOOperationException;
	Integer getAllUsersCount() throws DAOOperationException;
	List<UserBean> getAllUsersPage(int fromKeyInd, int limit) throws DAOOperationException;
	Integer getAllUsersByUserTypeCount(UserTypeBean userType) throws DAOOperationException;
	List<UserBean> getAllUsersByUserTypePage
		(UserTypeBean userType, int fromKeyInd, int limit) throws DAOOperationException;
	UpdateResult updateUserById(UserBean user) throws DAOOperationException;
	void deleteUserById(int userId) throws DAOOperationException;
}
