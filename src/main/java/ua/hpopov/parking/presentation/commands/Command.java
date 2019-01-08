package ua.hpopov.parking.presentation.commands;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class Command 
{
	public abstract CommandResult execute(HttpServletRequest request , HttpServletResponse response)
			throws ServletException, IOException;
	protected abstract void clearSessionVariables(HttpSession session);
}
