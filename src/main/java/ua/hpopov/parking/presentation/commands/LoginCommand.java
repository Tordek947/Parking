package ua.hpopov.parking.presentation.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.hpopov.parking.beans.UserBean;
import ua.hpopov.parking.presentation.Message;
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
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		LoginResult loginResult = UserService.getInstance().login(login, password);
		Message message = new Message();
		boolean succeed = false;
		switch(loginResult) {
		case ERROR:
			message.setMessage("A server error occurs while logging in");
			break;
		case NO_SUCH_USER:
			message.setMessage("Erroneous verification data: either login and/or password");
			break;
		case PROFILE_NEED_VERIFICATION:
			message.setMessage("This profile need administrator verification");
			break;
		case SUCCESSFUL:
			message.setMessage("Successfully");
			succeed = true;
			break;
		default:
			break;
		}
		request.setAttribute("msg", message);
		if (succeed) {
			UserBean userBean = loginResult.getUserBean();
			request.setAttribute("userBean", userBean);
			if (userBean.getUserTypeId() == 1) {//результат комманды -- страницы, на которую форвардимся из сервлета
				request.getRequestDispatcher("/admin_main.jsp").forward(request, response);
			} else if (userBean.getUserTypeId() == 2) {
				request.getRequestDispatcher("/driver_main.jsp");
			}
		} else {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
	}

}
