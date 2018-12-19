package ua.hpopov.parking.services;

import ua.hpopov.parking.beans.LoginInfoBean;
import ua.hpopov.parking.beans.UserBean;
import ua.hpopov.parking.datasource.dao.DAOFactories;
import ua.hpopov.parking.datasource.dao.DAOOperationException;
import ua.hpopov.parking.datasource.dao.LoginInfoDAO;
import ua.hpopov.parking.datasource.dao.UserDAO;

public class UserService extends AbstractService {
	private static UserService instance=null;
	
	private UserService() {}
	
	public static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}
	
	public LoginResult login(String login, String password) {
		LoginInfoDAO loginInfoDAO = DAOFactories.get().loginInfoDAO();
		LoginInfoBean loginInfo;
		try {
			loginInfo = loginInfoDAO.getLoginInfoByLoginPassword(login, password);
		} catch (DAOOperationException e) {
			handleException(e);
			return LoginResult.ERROR;
		}
		if (loginInfo == null) {
			return LoginResult.NO_SUCH_USER;
		}
		if (loginInfo.getNeedAdminCheck() == true) {
			return LoginResult.PROFILE_NEED_VERIFICATION;
		}
		
		UserDAO userDAO = DAOFactories.get().userDAO();
		UserBean user;
		try {
			user = userDAO.getUserById(loginInfo.getUserId());
		} catch (DAOOperationException e) {
			handleException(e);
			return LoginResult.ERROR;
		}
		LoginResult result = LoginResult.SUCCESSFUL;
		result.setUserBean(user);
		return result;
	}
}
