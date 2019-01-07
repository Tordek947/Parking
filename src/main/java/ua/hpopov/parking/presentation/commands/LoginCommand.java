package ua.hpopov.parking.presentation.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.hpopov.parking.beans.UserBean;
import ua.hpopov.parking.services.LoginResult;
import ua.hpopov.parking.services.UserService;

public class LoginCommand extends Command{

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
		String message=null;
		HttpSession session = request.getSession();
		boolean succeed = false;
		switch(loginResult) {
		case ERROR:
			message = "A server error occurs while logging in";
			break;
		case NO_SUCH_USER:
			message = "There is no user with such verification data";
			break;
		case PROFILE_NEED_VERIFICATION:
			message = "This profile need administrator verification";
			break;
		case SUCCESSFUL:
			message = null;
			succeed = true;
			break;
		case INVALID_DATA:
			message = "Erroneous verification data: either login/email and/or password";
			break;
		}
		session.setAttribute("loginMessage", message);
		CommandResult result = CommandResult.FORWARD;
		if (succeed) {
			UserBean userBean = loginResult.getUserBean();
			request.setAttribute("userBean", userBean);
			if (userBean.getUserTypeId() == 1) {//результат комманды -- страницы, на которую форвардимся из сервлета
				result.setArgument(Page.ADMIN_MAIN.getPath());
			} else if (userBean.getUserTypeId() == 2) {
				result.setArgument(Page.DRIVER_MAIN.getPath());
			}
		} else {
			result.setArgument(Page.LOG_IN.getPath());
		}
		return result;
	}

}
