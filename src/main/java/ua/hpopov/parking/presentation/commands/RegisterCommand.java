package ua.hpopov.parking.presentation.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.hpopov.parking.beans.LoginInfoBean;
import ua.hpopov.parking.beans.UserBean;
import ua.hpopov.parking.services.RegistrationResult;
import ua.hpopov.parking.services.UserService;

public class RegisterCommand extends Command {

	private static RegisterCommand instance=null;
	private RegisterCommand() {
		
	}
	
	public static RegisterCommand getInstance() {
		if (instance == null) {
			instance = new RegisterCommand();
		}
		return instance;
	}
	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserBean userBean = new UserBean();
		LoginInfoBean loginInfoBean = new LoginInfoBean();
		userBean.setName(request.getParameter("name"));
		userBean.setSurname(request.getParameter("surname"));
		userBean.setUserTypeId(Integer.valueOf(request.getParameter("userTypeId")));
		loginInfoBean.setEmail(request.getParameter("email"));
		loginInfoBean.setLogin(request.getParameter("login"));
		loginInfoBean.setPassword(request.getParameter("password"));
		RegistrationResult registrationResult = UserService.getInstance().register(userBean, loginInfoBean);
		HttpSession session = request.getSession();
		CommandResult result = CommandResult.FORWARD;
		result.setArgument(Page.REGISTRATION.getPath());
		switch(registrationResult) {
		case ERROR:
			session.setAttribute("registrationMessage", "An internal server error occured");
			break;
		case INVALID_EMAIL:
			session.setAttribute("registrationMessage", "An Email-field is invalid");
			break;
		case INVALID_NAME_OR_SURNAME:
			session.setAttribute("registrationMessage", "Either name and/or surname fields are invalid");
			break;
		case INVALID_PASSWORD:
			session.setAttribute("registrationMessage", "The password field is invalid");
			break;
		case SUCCESSFUL:
			session.setAttribute("lastRegisteredEmail", registrationResult.getEmail());
			session.setAttribute("registrationMessage", null);
			result.setArgument(Page.REGISTRATION_SUCCESSFUL.getPath());
			break;
		case UNUNIQUE_LOGIN_OR_EMAIL:
			session.setAttribute("registrationMessage", "A user with such email or login is already existing");
			break;
		case UNUNIQUE_NAME_AND_SURNAME:
			session.setAttribute("registrationMessage", "A user with such name and username is already existing");
			break;
		case INVALID_LOGIN:
			session.setAttribute("registrationMessage", "The login field is invalid");
			break;
		}
		return result;
	}

}
