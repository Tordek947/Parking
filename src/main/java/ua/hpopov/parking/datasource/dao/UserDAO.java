package ua.hpopov.parking.datasource.dao;

import java.util.List;

import ua.hpopov.parking.beans.UserBean;

public interface UserDAO extends DAO {
	void createUser(UserBean user) throws DAOOperationException;
	UserBean getUserById(int userId) throws DAOOperationException;
	Integer getAllUsersCount() throws DAOOperationException;
	List<UserBean> getAllUsersPage(int fromKeyInd, int limit) throws DAOOperationException;
	Integer getAllUsersByUserTypeCount(int userTypeId) throws DAOOperationException;
	List<UserBean> getAllUsersByUserTypePage
		(int userTypeId, int fromKeyInd, int limit) throws DAOOperationException;
	UpdateResult updateUserById(UserBean user) throws DAOOperationException;
	UpdateResult deleteUserById(int userId) throws DAOOperationException;
}
