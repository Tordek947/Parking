package ua.hpopov.parking.presentation.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutCommand extends Command {

	private static LogOutCommand instance=null;
	private LogOutCommand() {}
	
	public static LogOutCommand getInstance() {
		if (instance == null) {
			instance = new LogOutCommand();
		}
		return instance;
	}
	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommandResult result = CommandResult.FORWARD;
		clearSessionVariables(request.getSession());
		result.setArgument(Page.WELCOME.getPath());
		return result;
	}

	@Override
	protected void clearSessionVariables(HttpSession session) {
		session.removeAttribute("loginnedUserBean");
	}

}
