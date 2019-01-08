package ua.hpopov.parking.services;

import java.util.List;

import ua.hpopov.parking.beans.AssignmentJournalFull;
import ua.hpopov.parking.beans.DriverBean;
import ua.hpopov.parking.beans.UserBean;
import ua.hpopov.parking.beans.UserTypeBean;
import ua.hpopov.parking.datasource.dao.AssignmentJournalDAO;
import ua.hpopov.parking.datasource.dao.DAOFactories;
import ua.hpopov.parking.datasource.dao.DAOOperationException;
import ua.hpopov.parking.datasource.dao.DriverDAO;

public class AssignmentService extends AbstractService {

	private static AssignmentService instance=null;
	private AssignmentService() {}
	
	public static AssignmentService getInstance() {
		if (instance == null) {
			instance = new AssignmentService();
		}
		return instance;
	}
	
	public List<AssignmentJournalFull> getRecentEvents(UserBean userBean, int count){
		UserTypeBean userType = UserTypeBean.fromUserTypeId(userBean.getUserTypeId());
		if (userType.isAdministrator()) {
			return getAdministratorRecentEvents(userBean, count);
		} else if (userType.isDriver()) {
			return getDriverRecentEvents(userBean, count);
		}
		return null;
	}

	private List<AssignmentJournalFull> getAdministratorRecentEvents(UserBean userBean, int count) {
		AssignmentJournalDAO assignmentJournalDAO = DAOFactories.getFactory().createAssignmentJournalDAO();
		List<AssignmentJournalFull> result = null;
		try {
			result = assignmentJournalDAO
					.getConfirmedAssignmentsByDelegatorIdSortedByConfirmation(userBean.getUserId(), count);
		} catch (DAOOperationException e) {
			handleException(e);
			return null;
		}
		return result;
	}

	private List<AssignmentJournalFull> getDriverRecentEvents(UserBean userBean, int count) {
		AssignmentJournalDAO assignmentJournalDAO = DAOFactories.getFactory().createAssignmentJournalDAO();
		DriverDAO driverDAO = DAOFactories.getFactory().createDriverDAO();
		List<AssignmentJournalFull> result = null;
		try {
			DriverBean driverBean = driverDAO.getDriverByUserId(userBean.getUserId());
			result = assignmentJournalDAO
					.getAssignmentsByAssigneeIdSortedByDelegation(driverBean.getDriverId(), count);
		} catch (DAOOperationException e) {
			handleException(e);
			return null;
		}
		return result;
	}
}
