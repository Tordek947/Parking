package ua.hpopov.parking.datasource.dao;

import java.util.List;

import ua.hpopov.parking.beans.UserBean;
import ua.hpopov.parking.beans.UserTypeBean;

public interface UserDAO extends DAO {
	void createUser(UserBean user) throws DAOOperationException;
	UserBean getUserById(int userId) throws DAOOperationException;
	UpdateResult updateUserById(UserBean user) throws DAOOperationException;
	void deleteUserById(int userId) throws DAOOperationException;
	List<UserBean> getAllUsers() throws DAOOperationException;
	List<UserBean> getAllUsersByUserType(UserTypeBean userType) throws DAOOperationException;
}
