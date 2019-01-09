package ua.hpopov.parking.datasource.dao;

import java.util.List;

import ua.hpopov.parking.beans.UserBean;
import ua.hpopov.parking.beans.UserProfileWithoutPassword;

public interface UserDAO extends DAO {
	void createUser(UserBean user) throws DAOOperationException;
	UserBean getUserById(int userId) throws DAOOperationException;
	UserBean getUserByNameSurname(String name, String surname) throws DAOOperationException;
	Integer getAllUsersCount() throws DAOOperationException;
	List<UserBean> getAllUsersPage(int fromKeyInd, int limit) throws DAOOperationException;
	List<UserProfileWithoutPassword> getUnconfirmedUsersPage(int fromIndex, boolean forward, int pageSize)
			throws DAOOperationException;
	Integer getAllUsersByUserTypeCount(int userTypeId) throws DAOOperationException;
	List<UserBean> getAllUsersByUserTypePage
		(int userTypeId, int fromKeyInd, int limit) throws DAOOperationException;
	UpdateResult updateUserById(UserBean user) throws DAOOperationException;
	UpdateResult deleteUserById(int userId) throws DAOOperationException;
}
