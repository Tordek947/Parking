package ua.hpopov.parking.presentation.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public final class NoActionCommand extends Command {

	private static  NoActionCommand instance=null;
	private NoActionCommand() {}
	
	public static NoActionCommand getInstance() {
		if (instance == null) {
			instance = new NoActionCommand();
		}
		return instance;
	}
	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommandResult result = CommandResult.FORWARD;
		result.setArgument(Page.ERROR.getPath());
		return result;
	}

	@Override
	protected void clearSessionVariables(HttpSession session) {
		
	}

}
