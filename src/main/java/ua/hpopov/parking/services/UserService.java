package ua.hpopov.parking.services;

import java.util.regex.Pattern;
import org.apache.commons.lang3.RandomStringUtils;

import ua.hpopov.parking.beans.DriverBean;
import ua.hpopov.parking.beans.LoginInfoBean;
import ua.hpopov.parking.beans.UserBean;
import ua.hpopov.parking.beans.UserTypeBean;
import ua.hpopov.parking.datasource.Strings;
import ua.hpopov.parking.datasource.dao.DAOFactories;
import ua.hpopov.parking.datasource.dao.DAOOperationException;
import ua.hpopov.parking.datasource.dao.DriverDAO;
import ua.hpopov.parking.datasource.dao.LoginInfoDAO;
import ua.hpopov.parking.datasource.dao.Transaction;
import ua.hpopov.parking.datasource.dao.TransactionWork;
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
	
	public LoginResult login(String loginOrEmail, String password) {
		LoginInfoDAO loginInfoDAO = DAOFactories.getFactory().createLoginInfoDAO();
		LoginInfoBean loginInfo;
		if (false == ((checkLogin(loginOrEmail) || checkEmail(loginOrEmail)) && checkPassword(password))) {
			return LoginResult.INVALID_DATA;
		}
		try {
			loginInfo = loginInfoDAO.getLoginInfoByLoginOrEmailPassword(loginOrEmail, password);
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
		
		UserDAO userDAO = DAOFactories.getFactory().createUserDAO();
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
	
	private boolean checkEmail(String email) {
		if (email.length() > 64) {
			return false;
		}
		String regex = "^[a-z0-9](\\.?[a-z0-9]){5,}@gmail\\.com$";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		return pattern.matcher(email).matches();
	}
	
	private boolean checkLogin(String login) {
		if (login.length() > 32) {
			return false;
		}
		String regex = "^[a-z0-9](\\.?[a-z0-9]){4,}$";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		return pattern.matcher(login).matches();
	}

	private boolean checkPassword(String password) {
		String regex = "^[\\w0-9!@#$%^&*()]{5,64}$";
		return Pattern.matches(regex, password);
	}
	
	public RegistrationResult register(UserBean userBean, LoginInfoBean loginInfoBean, String repeatPassword) {
		if (!checkEmail(loginInfoBean.getEmail())) {
			return RegistrationResult.INVALID_EMAIL;
		}
		loginInfoBean.setEmail(normalizeEmail(loginInfoBean.getEmail()));
		String login = loginInfoBean.getLogin();
		if (login == null || login.compareTo("")==0 || login.compareTo(" ")==0) {
			login = loginInfoBean.getEmail();
			login = login.substring(0, login.indexOf('@'));
			loginInfoBean.setLogin(login);
		}
		if (!checkLogin(login)) {
			return RegistrationResult.INVALID_LOGIN;
		}
		if (!checkNameAndSurname(userBean.getName(), userBean.getSurname())) {
			return RegistrationResult.INVALID_NAME_OR_SURNAME;
		}
		if (loginInfoBean.getPassword().compareTo(repeatPassword) != 0) {
			return RegistrationResult.PASSWORDS_DIFFERS;
		}
		if (!checkPassword(loginInfoBean.getPassword())) {
			return RegistrationResult.INVALID_PASSWORD;
		}
		UserDAO userDAO = DAOFactories.getFactory().createUserDAO();
		LoginInfoDAO loginInfoDAO = DAOFactories.getFactory().createLoginInfoDAO();
		DriverDAO driverDAO = DAOFactories.getFactory().createDriverDAO();
		RegistrationResult[] result = new RegistrationResult[1];
		TransactionWork transactionWork = ()->{
			result[0] = RegistrationResult.UNUNIQUE_NAME_AND_SURNAME;
			userDAO.createUser(userBean);
			result[0] = RegistrationResult.ERROR;
			UserBean fullUserBean = userDAO.getUserByNameSurname(userBean.getName(), userBean.getSurname());
			Integer userId = fullUserBean.getUserId();
			loginInfoBean.setUserId(userId);
			result[0] = RegistrationResult.UNUNIQUE_LOGIN_OR_EMAIL;
			loginInfoDAO.createLoginInfo(loginInfoBean);
			result[0] = RegistrationResult.ERROR;
			if (userBean.getUserTypeId() == UserTypeBean.driver().getUserTypeId()) {
				DriverBean driverBean = new DriverBean();
				driverBean.setUserId(userId);
				driverDAO.createDriver(driverBean);
			}
		};
		Transaction transaction = DAOFactories.getFactory().createTransaction(transactionWork);
		try {
			transaction.execute();
		} catch (DAOOperationException e) {
			handleException(e);
			return result[0];
		}
		result[0] = RegistrationResult.SUCCESSFUL;
		result[0].setEmail(loginInfoBean.getEmail());
		return result[0];
	}

	private String normalizeEmail(String email) {
		return email.toLowerCase();
	}

	private boolean checkNameAndSurname(String name, String surname) {
		String regex = "^\\p{javaUpperCase}\\p{javaLowerCase}{1,30}$";
		Pattern pattern = Pattern.compile(regex, Pattern.UNICODE_CASE|Pattern.UNICODE_CHARACTER_CLASS);
		return pattern.matcher(name).matches() && pattern.matcher(surname).matches();
	}
	
	public EmailSendingResult sendRestorePasswordEmail(String email) {
		if (!checkEmail(email)) {
			return EmailSendingResult.INVALID_EMAIL;
		}
		email = normalizeEmail(email);
		LoginInfoDAO loginInfoDAO = DAOFactories.getFactory().createLoginInfoDAO();
		LoginInfoBean loginInfoBean;
		try {
			loginInfoBean = loginInfoDAO.getLoginInfoByEmail(email);
		} catch (DAOOperationException e) {
			handleException(e);
			return EmailSendingResult.ERROR;
		}
		if (loginInfoBean == null) {
			return EmailSendingResult.NO_USER_WITH_SUCH_EMAIL;
		}
		String verificationCode = RandomStringUtils.randomAlphanumeric(20);
		String subject = "Resetting the password in MyParking account";
		String text = Strings.concat(
				"Hello, ",loginInfoBean.getLogin(),"!\r\n",
				"You recieve this message because previously you choose password resetting in MyParking system.\r\n",
				"Your temporal verification code is ",verificationCode,".\r\n",
				"Please, enter this to the opened window to complete.\r\n",
				"If you did not do this, please, ignore this email.\r\n",
				"Do not reply this email, it was generated automatically."
				);
		boolean succeed = EmailSender.getInstance().send(subject, text, loginInfoBean.getEmail());
		if (!succeed) {
			return EmailSendingResult.FAIL;
		}
		EmailSendingResult result = EmailSendingResult.SUCCESS;
		result.setParameter(verificationCode);
		return result;
	}

	public SetPasswordResult setNewPassword(LoginInfoBean loginInfoBean, String repeatPassword) {
		String email = loginInfoBean.getEmail();
		if (!checkEmail(email)) {
			return SetPasswordResult.INVALID_EMAIL;
		}
		String password = loginInfoBean.getPassword();
		if (password.compareTo(repeatPassword) != 0) {
			return SetPasswordResult.PASSWORDS_DIFFERS;
		}
		if (!checkPassword(password)) {
			return SetPasswordResult.INVALID_PASSWORD;
		}
		email = normalizeEmail(email);
		final String finalEmail = email;
		LoginInfoDAO loginInfoDAO = DAOFactories.getFactory().createLoginInfoDAO();
		TransactionWork transactionWork = ()->{
			LoginInfoBean loginInfo = loginInfoDAO.getLoginInfoByEmail(finalEmail);
			loginInfo.setPassword(password);
			loginInfoDAO.updateLoginInfoByUserId(loginInfo);
		};
		Transaction transaction = DAOFactories.getFactory().createTransaction(transactionWork);
		try {
			transaction.execute();
		} catch (DAOOperationException e) {
			handleException(e);
			return SetPasswordResult.ERROR;
		}
		return SetPasswordResult.SUCCESS;
	}
	
}
