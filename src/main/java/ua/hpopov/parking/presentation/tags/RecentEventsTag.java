package ua.hpopov.parking.presentation.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import ua.hpopov.parking.beans.AssignmentJournalFull;
import ua.hpopov.parking.beans.UserBean;
import ua.hpopov.parking.beans.UserTypeBean;
import ua.hpopov.parking.presentation.paginationwrappers.AdministratorAssignmentJournalFullPresenter;
import ua.hpopov.parking.presentation.paginationwrappers.AssignmentJournalFullPresenter;
import ua.hpopov.parking.presentation.paginationwrappers.DriverAssignmentJournalFullPresenter;
import ua.hpopov.parking.services.AssignmentService;

@SuppressWarnings("serial")
public class RecentEventsTag extends TagSupport {
	
	private int count;
	
	private List<AssignmentJournalFull> list;
	private UserBean userBean;
	private UserTypeBean userTypeBean;	
	
	@Override
	public int doStartTag() throws JspException {
		initializeFields();
		writeContent();
		return super.doStartTag();
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	private void initializeFields() {
		userBean = (UserBean) pageContext.getSession().getAttribute("loginnedUserBean");
		if (userBean == null) {
			return;
		}
		userTypeBean = UserTypeBean.fromUserTypeId(userBean.getUserTypeId());
		if (userTypeBean == null) {
			return;
		}
		list = AssignmentService.getInstance().getRecentEvents(userBean, count);
	}

	private void writeContent() throws JspTagException {
		AssignmentJournalFullPresenter presenter;//А можно было лямбдой!
		if (userTypeBean.isAdministrator()) {
			presenter = AdministratorAssignmentJournalFullPresenter.getInstance();
		} else if (userTypeBean.isDriver()) {
			presenter = DriverAssignmentJournalFullPresenter.getInstance();
		} else {
			return;
		}
		JspWriter writer = pageContext.getOut();
		try {
			if (list.isEmpty()) {
				writer.write("<li><a>There are none events present now</a></li>");
			}
			for(AssignmentJournalFull assignment : list) {
				writer.write("<li><a>"+presenter.present(assignment)+"</a></li>");
			}
		} catch(IOException e) {
			throw new JspTagException(e.getMessage());
		}
	}
}
