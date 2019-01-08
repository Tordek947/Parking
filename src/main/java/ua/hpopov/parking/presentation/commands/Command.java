package ua.hpopov.parking.presentation.commands;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.hpopov.parking.beans.UserBean;
import ua.hpopov.parking.beans.UserTypeBean;

public abstract class Command 
{
	public abstract CommandResult execute(HttpServletRequest request , HttpServletResponse response)
			throws ServletException, IOException;
	protected abstract void clearSessionVariables(HttpSession session);
	protected UserTypeBean getLoginnedUserTypeBean(HttpSession session) {
		Object temp = session.getAttribute("loginnedUserBean");
		if (temp == null) {
			return null;
		}
		if ((temp instanceof UserBean) == false) {
			return null;
		}
		UserBean userBean = (UserBean) temp;
		UserTypeBean result = UserTypeBean.fromUserTypeId(userBean.getUserTypeId());
		return result;
	}
}
