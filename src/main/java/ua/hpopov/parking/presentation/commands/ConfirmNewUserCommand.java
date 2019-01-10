package ua.hpopov.parking.presentation.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.hpopov.parking.services.PaginationService;
import ua.hpopov.parking.services.PopupMessage;
import ua.hpopov.parking.services.PopupMessage.PopupMessageType;
import ua.hpopov.parking.services.ResolveUserResult;
import ua.hpopov.parking.services.UserService;
import ua.hpopov.parking.services.WalkPaginationResult;

public class ConfirmNewUserCommand extends Command {

	private static ConfirmNewUserCommand instance=null;
	private ConfirmNewUserCommand() {}
	
	public static ConfirmNewUserCommand getInstance() {
		if (instance == null) {
			instance = new ConfirmNewUserCommand();
		}
		return instance;
	}
	
	@Override
	public CommandResult execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommandResult result = CommandResult.FORWARD;
		result.setArgument(Page.NEW_USERS.getPath());
		PopupMessage resolveNewUserPopupMsg = new PopupMessage();
		String str = request.getParameter("choosenBeanId");
		Integer choosenBeanId = Integer.valueOf(str);
		ResolveUserResult resolveUserResult = UserService.getInstance().confirmUser(choosenBeanId);
		switch(resolveUserResult) {
		case ERROR:
			resolveNewUserPopupMsg.setMessageType(PopupMessageType.DANGER);
			resolveNewUserPopupMsg.setMessageText("A server error occurs");
			break;
		case SUCCESS:
			resolveNewUserPopupMsg.setMessageType(PopupMessageType.SUCCESS);
			resolveNewUserPopupMsg.setMessageText(resolveUserResult.getUserBean().getName()+" "+
					resolveUserResult.getUserBean().getSurname()+" was successfully confirmed");
			updateNewUsersBeanSet(request, response);
			break;
		case WAS_ALREADY_RESOLVED:
			resolveNewUserPopupMsg.setMessageType(PopupMessageType.INFO);
			resolveNewUserPopupMsg.setMessageText(resolveUserResult.getUserBean().getName()+" "+
					resolveUserResult.getUserBean().getSurname()+" has already been resolved");
			break;
		
		}
		request.setAttribute("resolveNewUserPopupMsg", resolveNewUserPopupMsg);
		return result;
	}

	private void updateNewUsersBeanSet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommandResult result = CommandResult.FORWARD;
		result.setArgument(Page.ADMIN_MAIN.getPath());
		
		Integer pageSize = 10;
		Integer fromIndex = Integer.MAX_VALUE;
		Boolean forward = true;
		WalkPaginationResult walkPaginationResult = PaginationService
				.getInstance().getNewUsersSimplePage(fromIndex, forward, pageSize);
		switch(walkPaginationResult.getPaginationResult()) {
		case NO_RECORDS:
			request.setAttribute("newUsersMessage", "There are no records yet");
			result.setArgument(Page.NEW_USERS.getPath());
			break;
		case EMPTY_PAGE:
		case ERROR:
			result.setArgument(Page.ERROR.getPath());
			break;
		case SUCCESS:
			result.setArgument(Page.NEW_USERS.getPath());
			request.setAttribute("newUsersBeanSet", walkPaginationResult.getPaginationBeanSet());
			request.setAttribute("hasPrevPage", walkPaginationResult.isHasPrevPage());
			request.setAttribute("hasNextPage", walkPaginationResult.isHasNextPage());
			break;		
		}
		//return result;
	}

	@Override
	protected void clearSessionVariables(HttpSession session) {
		
	}

}
