package ua.hpopov.parking.datasource.dao;

import ua.hpopov.parking.beans.LoginInfoBean;

public interface LoginInfoDAO extends DAO {
	void createLoginInfo(LoginInfoBean loginInfo) throws DAOOperationException;
	LoginInfoBean getLoginInfoByUserId(int userId) throws DAOOperationException;
	LoginInfoBean getLoginInfoByLoginPassword(String login, String password)
			throws DAOOperationException;
	UpdateResult updateLoginInfoByUserId(LoginInfoBean loginInfo) throws DAOOperationException;
}
