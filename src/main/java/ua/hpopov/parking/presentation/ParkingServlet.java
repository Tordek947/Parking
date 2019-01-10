package ua.hpopov.parking.presentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.hpopov.parking.presentation.commands.Command;
import ua.hpopov.parking.presentation.commands.CommandHelper;
import ua.hpopov.parking.presentation.commands.CommandResult;
import ua.hpopov.parking.presentation.commands.Page;


@SuppressWarnings("serial")
@WebServlet("/ParkingServlet")
public class ParkingServlet extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(ParkingServlet.class);
	
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
		try {
			doSth(request,response);
		} catch(Exception e) {
			log.error("Error forwarding to error page",e);
		}
		
	}

	private void doSth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			request.setCharacterEncoding("UTF-8");
			Command command = CommandHelper.getCommand(request.getParameter("command"));
			CommandResult commandResult;
			commandResult = command.execute(request, response);
			switch(commandResult) {
			case FORWARD:
				getServletContext().getRequestDispatcher(commandResult.getArgument()).forward(request, response);
				break;
			case REDIRECT:
				break;
			case STAY:
				break;
			}
		} catch (Exception e) {
			log.error("",e);
			getServletContext().getRequestDispatcher(Page.ERROR.getPath()).forward(request, response);
		}
	}
	
}
