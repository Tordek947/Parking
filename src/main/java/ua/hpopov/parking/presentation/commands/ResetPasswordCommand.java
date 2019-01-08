package ua.hpopov.parking.presentation.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.hpopov.parking.beans.LoginInfoBean;
import ua.hpopov.parking.services.SetPasswordResult;
import ua.hpopov.parking.services.UserService;

public final class ResetPasswordCommand extends Command {

	private static ResetPasswordCommand instance=null;
	private ResetPasswordCommand() {
		
	}
	
	public static ResetPasswordCommand getInstance() {
		if (instance == null) {
			instance = new ResetPasswordCommand();
		}
		return instance;
	}
	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String verificationCode = request.getParameter("verificationCode");
		HttpSession session = request.getSession();
		CommandResult result = CommandResult.FORWARD;
		result.setArgument(Page.RESET_PASSWORD.getPath());
		String sentVerificationCode = (String) session.getAttribute("sentVerificationCode");
		if (sentVerificationCode == null) {
			result.setArgument(Page.ERROR.getPath());
			return result;
		}
		if (verificationCode.compareTo(sentVerificationCode) != 0) {
			session.setAttribute("resetPasswordMessage", "Incorrect verification code!");
			return result;
		}
		String repeatPassword = request.getParameter("repeatPassword");
		LoginInfoBean loginInfoBean = new LoginInfoBean();
		loginInfoBean.setEmail((String) session.getAttribute("email"));
		loginInfoBean.setPassword(request.getParameter("password"));
		SetPasswordResult setPasswordResult = UserService.getInstance().setNewPassword(loginInfoBean, repeatPassword);
		switch(setPasswordResult) {
		case ERROR:
			session.setAttribute("resetPasswordMessage", "A server error occurs resetting password");
			break;
		case INVALID_EMAIL:
			session.setAttribute("resetPasswordMessage", "Provided email are invalid");
			break;
		case INVALID_PASSWORD:
			session.setAttribute("resetPasswordMessage", "Provided password are invalid");
			break;
		case PASSWORDS_DIFFERS:
			session.setAttribute("resetPasswordMessage", "Passwords do not match");
			break;
		case SUCCESS:
			clearSessionVariables(session);
			result.setArgument(Page.RESET_PASSWORD_SUCCESSFUL.getPath());
			break;
		}
		return result;
	}

	@Override
	protected void clearSessionVariables(HttpSession session) {
		session.removeAttribute("sentVerificationCode");
		session.removeAttribute("resetPasswordHeaderMessage");
		session.removeAttribute("resetPasswordMessage");
	}

}
