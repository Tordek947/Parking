package ua.hpopov.parking.presentation.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import ua.hpopov.parking.datasource.Strings;
import ua.hpopov.parking.services.PopupMessage;

@SuppressWarnings("serial")
public class PopupMessageTag extends TagSupport {

	private PopupMessage popupMessage;
	private String popupMsgName;

	
	@Override
	public int doStartTag() throws JspException {
		popupMessage = (PopupMessage) pageContext.getRequest().getAttribute(popupMsgName);
		String popupMsgHtml = Strings.concat(
				"<div class=\"alert alert-",popupMessage.getMessageType().toString().toLowerCase(),
				" alert-dismissible\" role=\"alert\">\r\n",
				"  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">",
				"<span aria-hidden=\"true\">&times;</span></button>\r\n", 
				"	",popupMessage.getMessageText(),"\r\n",
				"</div>"
				);
		try {
			pageContext.getOut().write(popupMsgHtml);
		} catch (IOException e) {
			throw new JspTagException(e.getMessage());
		}
		return super.doStartTag();
	}

	public void setPopupMsgName(String popupMsgName) {
		this.popupMsgName = popupMsgName;
	}
	
}
