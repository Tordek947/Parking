package ua.hpopov.parking.presentation.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardToRegistrationCommand extends Command {

	private static ForwardToRegistrationCommand instance=null;
	private ForwardToRegistrationCommand() {
		
	}
	
	public static ForwardToRegistrationCommand getInstance() {
		if (instance == null) {
			instance = new ForwardToRegistrationCommand();
		}
		return instance;
	}
	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommandResult result = CommandResult.FORWARD;
		result.setArgument(Page.REGISTRATION.getPath());
		return result;
	}

}
