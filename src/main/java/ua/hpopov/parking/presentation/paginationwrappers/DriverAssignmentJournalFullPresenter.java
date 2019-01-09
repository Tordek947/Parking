package ua.hpopov.parking.presentation.paginationwrappers;

import ua.hpopov.parking.beans.AssignmentJournalFull;
import ua.hpopov.parking.beans.UserBean;
import ua.hpopov.parking.datasource.Strings;

public class DriverAssignmentJournalFullPresenter extends AssignmentJournalFullPresenter {

	private static DriverAssignmentJournalFullPresenter instance=null;
	private DriverAssignmentJournalFullPresenter() {}
	
	public static DriverAssignmentJournalFullPresenter getInstance() {
		if (instance==null) {
			instance = new DriverAssignmentJournalFullPresenter();
		}
		return instance;
	}
	
	@Override
	public String present(AssignmentJournalFull assignmentJournalFull) {
		UserBean delegator = assignmentJournalFull.getDelegatorUserBean();
		return Strings.concat(
				delegator.getName()," ",delegator.getSurname()," delegate you on route number "+
				assignmentJournalFull.getRouteBean().getRouteNumber()
				);
	}

}
