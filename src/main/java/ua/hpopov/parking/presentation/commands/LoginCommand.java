package ua.hpopov.parking.presentation.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.hpopov.parking.beans.UserBean;
import ua.hpopov.parking.beans.UserTypeBean;
import ua.hpopov.parking.services.LoginResult;
import ua.hpopov.parking.services.UserService;

public final class LoginCommand extends Command{

	private static LoginCommand instance=null;
	private LoginCommand() {}
	
	public static LoginCommand getInstance() {
		if (instance == null) {
			instance = new LoginCommand();
		}
		return instance;
	}
	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginOrEmail = request.getParameter("loginOrEmail");
		String password = request.getParameter("password");
		LoginResult loginResult = UserService.getInstance().login(loginOrEmail, password);
		HttpSession session = request.getSession();
		boolean succeed = false;
		switch(loginResult) {
		case ERROR:
			session.setAttribute("loginMessage", "A server error occurs while logging in");
			break;
		case NO_SUCH_USER:
			session.setAttribute("loginMessage", "There is no user with such verification data");
			break;
		case PROFILE_NEED_VERIFICATION:
			session.setAttribute("loginMessage", "This profile need administrator verification");
			break;
		case SUCCESSFUL:
			clearSessionVariables(session);
			succeed = true;
			break;
		case INVALID_DATA:
			session.setAttribute("loginMessage", "Erroneous verification data: either login/email and/or password");
			break;
		}
		CommandResult result = CommandResult.FORWARD;
		if (succeed) {
			UserBean userBean = loginResult.getUserBean();
			UserTypeBean userType =  UserTypeBean.fromUserTypeId(userBean.getUserTypeId());
			session.setAttribute("loginnedUserBean", userBean);
			if (userType.isAdministrator()) {//результат комманды -- страницы, на которую форвардимся из сервлета
				result.setArgument(Page.ADMIN_MAIN.getPath());
			} else if (userType.isDriver()) {
				result.setArgument(Page.DRIVER_MAIN.getPath());
			} else {
				session.setAttribute("loginMessage", "A server error occurs while logging in");
				result.setArgument(Page.LOG_IN.getPath());
			}
		} else {
			result.setArgument(Page.LOG_IN.getPath());
		}
		return result;
	}

	@Override
	protected void clearSessionVariables(HttpSession session) {
		session.removeAttribute("loginMessage");
	}

}
