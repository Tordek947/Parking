package ua.hpopov.parking.presentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.hpopov.parking.presentation.commands.Command;
import ua.hpopov.parking.presentation.commands.CommandHelper;
import ua.hpopov.parking.presentation.commands.CommandResult;


@SuppressWarnings("serial")
@WebServlet("/ParkingServlet")
public class ParkingServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doSth(request,response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doSth(request,response);
	}

	private void doSth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Command command = CommandHelper.getCommand(request.getParameter("command"));
		CommandResult commandResult = command.execute(request, response);
		switch(commandResult) {
		case FORWARD:
			getServletContext().getRequestDispatcher(commandResult.getArgument()).forward(request, response);
			//request.getRequestDispatcher(commandResult.getArgument()).forward(request, response);
			break;
		case REDIRECT:
			break;
		case STAY:
			break;
		}
	}
	
}
