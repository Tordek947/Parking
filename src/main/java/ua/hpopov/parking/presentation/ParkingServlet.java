package ua.hpopov.parking.presentation;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/ParkingServlet")
public class ParkingServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String command = request.getParameter("command");
		System.out.println("Get:"+command);
		Enumeration<String> anames = request.getParameterNames();
		while(anames.hasMoreElements()) {
			String name = anames.nextElement();
			System.out.println(name+"="+request.getParameter(name));
		}
//		String action = request.getParameter("action");
//		System.out.println("doGet(...)");
//		if (action.compareTo("login") == 0) {
//			BeanSample bean = new BeanSample();
//			bean.setName("Petro");
//			request.setAttribute("bean", bean);
//			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
//		}
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String command = request.getParameter("command");
		System.out.println("Post"+command);
		Enumeration<String> anames = request.getParameterNames();
		while(anames.hasMoreElements()) {
			String name = anames.nextElement();
			System.out.println(name+"="+request.getParameter(name));
		}
	}

}
