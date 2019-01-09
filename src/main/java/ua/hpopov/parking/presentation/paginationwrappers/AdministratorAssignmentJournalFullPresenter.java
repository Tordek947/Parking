package ua.hpopov.parking.presentation.paginationwrappers;

import ua.hpopov.parking.beans.AssignmentJournalFull;
import ua.hpopov.parking.beans.UserBean;
import ua.hpopov.parking.datasource.Strings;

public class AdministratorAssignmentJournalFullPresenter extends AssignmentJournalFullPresenter {

	private static AdministratorAssignmentJournalFullPresenter instance=null;
	private AdministratorAssignmentJournalFullPresenter() {}
	
	public static AdministratorAssignmentJournalFullPresenter getInstance() {
		if (instance==null) {
			instance = new AdministratorAssignmentJournalFullPresenter();
		}
		return instance;
	}
	
	@Override
	public String present(AssignmentJournalFull assignmentJournalFull) {
		UserBean assignee = assignmentJournalFull.getAssigneeDriverFull().getUserBean();
		return Strings.concat(
				assignee.getName()," ",assignee.getSurname()," confirmed your assignment (route "+
				assignmentJournalFull.getRouteBean().getRouteNumber()," )"
				);
	}

}
