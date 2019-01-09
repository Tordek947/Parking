package ua.hpopov.parking.presentation.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeclineNewUserCommand extends Command {

	private static DeclineNewUserCommand instance=null;
	private DeclineNewUserCommand() {}
	
	public static DeclineNewUserCommand getInstance() {
		if (instance == null) {
			instance = new DeclineNewUserCommand();
		}
		return instance;
	}
	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void clearSessionVariables(HttpSession session) {
		// TODO Auto-generated method stub

	}

}
