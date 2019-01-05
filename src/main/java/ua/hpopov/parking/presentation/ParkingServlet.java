package ua.hpopov.parking.presentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.hpopov.parking.presentation.commands.Command;
import ua.hpopov.parking.presentation.commands.CommandHelper;


@SuppressWarnings("serial")
@WebServlet("/ParkingServlet")
public class ParkingServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Command command = CommandHelper.getCommand(request.getParameter("command"));
		command.execute(request, response);
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Command command = CommandHelper.getCommand(request.getParameter("command"));
		command.execute(request, response);
	}

}
