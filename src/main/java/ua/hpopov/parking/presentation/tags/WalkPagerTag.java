package ua.hpopov.parking.presentation.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import ua.hpopov.parking.datasource.Strings;

@SuppressWarnings("serial")
public class WalkPagerTag extends TagSupport {
	private String ariaLabel=null;
	private boolean prevDisabled=true;
	private boolean nextDisabled=false;
	
	public void setAriaLabel(String ariaLabel) {
		this.ariaLabel = ariaLabel;
	}

	public void setPrevDisabled(boolean prevDisabled) {
		this.prevDisabled = prevDisabled;
	}

	public void setNextDisabled(boolean nextDisabled) {
		this.nextDisabled = nextDisabled;
	}

	@Override
	public int doStartTag() throws JspException {
		String html = Strings.concat(
				"<nav ",(ariaLabel==null?"":"aria-label=\""+ariaLabel+"\""),">\r\n",
				"  <ul class=\"pager\">\r\n",
				"    <li ",(prevDisabled?"class=\"disabled\" ":""),"><a href=\"#\">Previous</a></li>\r\n",
				"    <li ",(nextDisabled?"class=\"disabled\" ":""),"><a href=\"#\">Next</a></li>\r\n",
				"  </ul>\r\n",
				"</nav>"
				);
		try {
			pageContext.getOut().write(html);
		} catch (IOException e) {
			throw new JspTagException(e.getMessage());
		}
		return super.doStartTag();
	}
}
