package ua.hpopov.parking.presentation.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.hpopov.parking.services.PopupMessage;
import ua.hpopov.parking.services.PopupMessage.PopupMessageType;
import ua.hpopov.parking.services.ResolveUserResult;
import ua.hpopov.parking.services.UserService;

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
		Integer choosenBeanId = Integer.valueOf(request.getParameter("choosenBeanId"));
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
		request.setAttribute("pageSize", Integer.valueOf(10).toString());
		request.setAttribute("fromIndex", Integer.valueOf(1).toString());
		request.setAttribute("forward", Boolean.valueOf(true).toString());
		NewUsersCommand.getInstance().execute(request, response);
	}

	@Override
	protected void clearSessionVariables(HttpSession session) {
		
	}

}
