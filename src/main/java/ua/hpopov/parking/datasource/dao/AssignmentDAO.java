package ua.hpopov.parking.datasource.dao;

import java.util.List;

import ua.hpopov.parking.beans.AssignmentBean;
import ua.hpopov.parking.beans.AssignmentFull;

public interface AssignmentDAO extends DAO {
	void createAssignment(AssignmentBean assignment) throws DAOOperationException;
	AssignmentFull getAssignmentWithoutDelegatorByUserId(int userId) throws DAOOperationException;
	AssignmentFull getAssignmentWithoutDelegatorByDriverId(int driverId) throws DAOOperationException;
	AssignmentFull getAssignmentWithoutDelegatorByBusId(int busId) throws DAOOperationException;
	AssignmentFull getAssignmentWithoutDelegatorByRouteId(int routeId) throws DAOOperationException;
	Integer getUnconfirmedAssignmentsByDelegatorIdCount(int delegatorUserId) throws DAOOperationException;
	List<AssignmentFull> getUnconfirmedAssignmentsPageByDelegatorId
		(int delegatorUserId, int fromKeyInd, int limit) throws DAOOperationException;
	UpdateResult updateAssignmentByDriverId(AssignmentBean assignment) throws DAOOperationException;
	UpdateResult deleteByDriverId(int driverId) throws DAOOperationException;
	UpdateResult deleteByBusId(int busId) throws DAOOperationException;
	UpdateResult deleteAssignmentsByRouteId(int routeId) throws DAOOperationException;
}