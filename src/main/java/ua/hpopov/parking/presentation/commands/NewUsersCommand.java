package ua.hpopov.parking.presentation.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.hpopov.parking.services.PaginationService;
import ua.hpopov.parking.services.WalkPaginationResult;

public class NewUsersCommand extends Command {
	
	private static NewUsersCommand instance=null;
	private NewUsersCommand() {	}
	
	public static NewUsersCommand getInstance() {
		if (instance == null) {
			instance = new NewUsersCommand();
		}
		return instance;
	}

	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommandResult result = CommandResult.FORWARD;
		result.setArgument(Page.ADMIN_MAIN.getPath());
		
		Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
		Integer fromIndex = Integer.valueOf(request.getParameter("fromIndex"));
		Boolean forward = Boolean.valueOf(request.getParameter("forward"));
		WalkPaginationResult walkPaginationResult = PaginationService
				.getInstance().getNewUsersSimplePage(fromIndex, forward, pageSize);
		switch(walkPaginationResult.getPaginationResult()) {
		case NO_RECORDS:
			request.setAttribute("newUsersMessage", "There are no records yet");
			result.setArgument(Page.NEW_USERS.getPath());
			clearSessionVariables(request.getSession());
			break;
		case EMPTY_PAGE:
		case ERROR:
			result.setArgument(Page.ERROR.getPath());
			break;
		case SUCCESS:
			result.setArgument(Page.NEW_USERS.getPath());
			clearSessionVariables(request.getSession());
			request.setAttribute("newUsersBeanSet", walkPaginationResult.getPaginationBeanSet());
			request.setAttribute("hasPrevPage", walkPaginationResult.isHasPrevPage());
			request.setAttribute("hasNextPage", walkPaginationResult.isHasNextPage());
			break;		
		}
		return result;
	}

	@Override
	protected void clearSessionVariables(HttpSession session) {
		//session.removeAttribute("newUsersMessage");
	}

}
