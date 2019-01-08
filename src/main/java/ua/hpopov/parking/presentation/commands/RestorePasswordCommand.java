package ua.hpopov.parking.presentation.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.hpopov.parking.services.EmailSendingResult;
import ua.hpopov.parking.services.UserService;

public final class RestorePasswordCommand extends Command {

	private static RestorePasswordCommand instance=null;
	private RestorePasswordCommand() {
		
	}
	
	public static RestorePasswordCommand getInstance() {
		if (instance == null) {
			instance = new RestorePasswordCommand();
		}
		return instance;
	}
	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		EmailSendingResult emailSendingResult = UserService.getInstance().sendRestorePasswordEmail(email);
		HttpSession session = request.getSession();
		CommandResult result = CommandResult.FORWARD;
		result.setArgument(Page.FORGOT_PASSWORD.getPath());
		switch(emailSendingResult) {
		case ERROR:
			session.setAttribute("restorePasswordMessage", "A server error occurs restoring password");
			break;
		case FAIL:
			session.setAttribute("restorePasswordMessage", "Sending a message to the specified email has not been succeed");
			break;
		case INVALID_EMAIL:
			session.setAttribute("restorePasswordMessage", "Provided email are invalid");
			break;
		case NO_USER_WITH_SUCH_EMAIL:
			session.setAttribute("restorePasswordMessage", "There are no user with such email");
			break;
		case SUCCESS:
			clearSessionVariables(session);
			session.setAttribute("email", email);
			session.setAttribute("resetPasswordHeaderMessage", "A letter with temporal verification code was sent to your email."+
			" Please, enter it here");
			session.setAttribute("sentVerificationCode", emailSendingResult.getParameter());
			result.setArgument(Page.RESET_PASSWORD.getPath());
			break;
		}
		return result;
	}

	@Override
	protected void clearSessionVariables(HttpSession session) {
		session.removeAttribute("restorePasswordMessage");
	}

}
