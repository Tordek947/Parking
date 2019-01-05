package ua.hpopov.parking.datasource.dao;

import java.util.List;

import ua.hpopov.parking.beans.AssignmentJournalBean;
import ua.hpopov.parking.beans.AssignmentJournalFull;

public interface AssignmentJournalDAO extends DAO {
	void create(AssignmentJournalBean assignment) throws DAOOperationException;
	
	Integer getAssignmentsByDelegatorIdCount(int delegatorUserId) throws DAOOperationException;
	List<AssignmentJournalFull> getAssignmentsPageByDelegatorId
	(int delegatorUserId, int fromKeyInd, int limit) throws DAOOperationException;
	Integer getAssignmentsPageByAssigneeIdCount(int assigneeDriverId) throws DAOOperationException;
	List<AssignmentJournalFull> getAssignmentsPageByAssigneeId
	(int assigneeDriverId, int fromKeyInd, int limit) throws DAOOperationException;
	Integer getAssignmentsPageByBusIdCount(int busId) throws DAOOperationException;
	List<AssignmentJournalFull> getAssignmentsPageByBusId
	(int busId, int fromKeyInd, int limit) throws DAOOperationException;
	Integer getAssignmentsPageByRouteIdCount(int routeId) throws DAOOperationException;
	List<AssignmentJournalFull> getAssignmentsPageByRouteId
	(int routeId, int fromKeyInd, int limit) throws DAOOperationException;	
		
}
