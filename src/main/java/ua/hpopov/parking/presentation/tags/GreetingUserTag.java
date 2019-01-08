package ua.hpopov.parking.presentation.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import ua.hpopov.parking.beans.UserBean;

@SuppressWarnings("serial")
public class GreetingUserTag extends TagSupport {

	@Override
	public int doStartTag() throws JspException {
		String greeting;
		UserBean userBean = (UserBean) pageContext.getSession().getAttribute("loginnedUserBean");
		if (userBean==null) {
			return super.doStartTag();
		}
		greeting = "Hello, "+ userBean.getName()+" "+userBean.getSurname()+"!";
		try {
			pageContext.getOut().write(greeting);
		} catch (IOException e) {
			throw new JspTagException(e.getMessage());
		}
		return super.doStartTag();
	}
}
