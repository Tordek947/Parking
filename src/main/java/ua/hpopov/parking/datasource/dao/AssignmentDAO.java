package ua.hpopov.parking.datasource.dao;

import java.util.List;

import ua.hpopov.parking.beans.AssignmentBean;

public interface AssignmentDAO extends DAO {
	AssignmentBean getAssignmentByUserId(int userId) throws DAOOperationException;
	AssignmentBean getAssignmentByDriverId(int driverId) throws DAOOperationException;
	AssignmentBean getAssignmentByBusId(int busId) throws DAOOperationException;
	AssignmentBean getAssignmentByRouteId(int routeId) throws DAOOperationException;
	List<AssignmentBean> getUnconfirmedAssignments() throws DAOOperationException;
}