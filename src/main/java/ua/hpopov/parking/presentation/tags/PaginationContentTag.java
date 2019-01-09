package ua.hpopov.parking.presentation.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import ua.hpopov.parking.presentation.paginationwrappers.BeanWrapper;
import ua.hpopov.parking.presentation.paginationwrappers.PaginationBeanSet;

@SuppressWarnings("serial")
public class PaginationContentTag extends TagSupport {
	
	private String rowClass="";
	private String cellClass="";
	private String pgBeanSetAttrName;
	private PaginationBeanSet paginationBeanSet;

	public String getPgBeanSetAttrName() {
		return pgBeanSetAttrName;
	}

	public void setPgBeanSetAttrName(String pgBeanSetAttrName) {
		this.pgBeanSetAttrName = pgBeanSetAttrName;
	}

	public void setRowClass(String rowClass) {
		this.rowClass = rowClass;
	}

	public void setCellClass(String cellClass) {
		this.cellClass = cellClass;
	}

	public void setPaginationBeanSet(PaginationBeanSet paginationBeanSet) {
		this.paginationBeanSet = paginationBeanSet;
	}

	@Override
	public int doStartTag() throws JspException {
		setPaginationBeanSet((PaginationBeanSet)pageContext.getRequest().getAttribute(pgBeanSetAttrName));
		try {
			//pageContext.getOut().write("<ul class=\""+holderClass+"\">");
			writeBody();
			//pageContext.getOut().write("</ul>");
		} catch (IOException e) {
			throw new JspTagException(e);
		}
		return super.doStartTag();
	}

	private void writeBody() throws IOException {
		writeHeader(paginationBeanSet.getHeader());
		for(BeanWrapper beanWrapper : paginationBeanSet) {
			writeRow(beanWrapper);
		}
	}

	private void writeHeader(BeanWrapper beanWrapper) throws IOException {
		StringBuilder rowContent = new StringBuilder();
		rowContent.append("<div class=\""+rowClass+"\">");
		while(beanWrapper.hasNext()) {
			rowContent.append("<p class=\""+cellClass+"\">")
					  .append(beanWrapper.next())
					  .append("</p>");
		}
		rowContent.append("</div>");
		pageContext.getOut().write(rowContent.toString());
	}
	
	private void writeRow(BeanWrapper beanWrapper) throws IOException {
		StringBuilder rowContent = new StringBuilder();
		rowContent.append("<div beanId=\""+beanWrapper.getBeanId()+"\" class=\""+rowClass+"\">");
		while(beanWrapper.hasNext()) {
			rowContent.append("<p class=\""+cellClass+"\">")
					  .append(beanWrapper.next())
					  .append("</p>");
		}
		rowContent.append("</div>");
		pageContext.getOut().write(rowContent.toString());
	}
}
