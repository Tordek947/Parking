package ua.hpopov.parking.presentation.tags;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import ua.hpopov.parking.beans.UserBean;
import ua.hpopov.parking.beans.UserTypeBean;
import ua.hpopov.parking.presentation.commands.Page;

@SuppressWarnings("serial")
public class RefToMainPageTag extends BodyTagSupport {
	private String href=Page.WELCOME.getName();
	
	@Override
	public int doStartTag() throws JspException {
		HttpSession session = pageContext.getSession();
		UserBean userBean = (UserBean) session.getAttribute("loginnedUserBean");
		if (userBean == null) {
			href = Page.WELCOME.getName();
		} else if (UserTypeBean.fromUserTypeId(userBean.getUserTypeId()).isAdministrator()) {
			href = Page.ADMIN_MAIN.getName();
		} else if (UserTypeBean.fromUserTypeId(userBean.getUserTypeId()).isDriver()) {
			href = Page.DRIVER_MAIN.getName();
		}
		try {
			pageContext.getOut().write("<a href=\""+href+"\">");
		} catch (IOException e) {
			throw new JspTagException(e.getMessage());
		}
		return EVAL_BODY_INCLUDE;
	}
	
	@Override
	public int doAfterBody() throws JspException {
		try {
			pageContext.getOut().write("</a>");
		} catch (IOException e) {
			throw new JspTagException(e.getMessage());
		}
		return super.doAfterBody();
	}
}
