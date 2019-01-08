package ua.hpopov.parking.presentation.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public final class ForwardToLoginCommand extends Command {

	private static ForwardToLoginCommand instance=null;
	private ForwardToLoginCommand() {
		
	}
	
	public static ForwardToLoginCommand getInstance() {
		if (instance == null) {
			instance = new ForwardToLoginCommand();
		}
		return instance;
	}
	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommandResult result = CommandResult.FORWARD;
		result.setArgument(Page.LOG_IN.getPath());
		return result;
	}

	@Override
	protected void clearSessionVariables(HttpSession session) {
		
	}

}
