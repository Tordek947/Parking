package ua.hpopov.parking.datasource.dao;

import java.util.List;

import ua.hpopov.parking.beans.RouteBean;
import ua.hpopov.parking.beans.RouteFull;

public interface RouteDAO {
	void createRoute(RouteBean route) throws DAOOperationException;
	RouteFull getRouteById(int routeId) throws DAOOperationException;
	Integer getAllRoutesCount() throws DAOOperationException;
	List<RouteFull> getAllRoutesPage(int fromKeyInd, int limit) throws DAOOperationException;
	Integer getAllFreeRoutesCount() throws DAOOperationException;
	List<RouteFull> getAllFreeRoutesPage(int fromKeyInd, int limit) throws DAOOperationException;
	Integer getAllBusyRoutesCount() throws DAOOperationException;
	List<RouteFull> getAllBusyRoutesPage(int fromKeyInd, int limit) throws DAOOperationException;
	UpdateResult updateRoute(RouteBean route) throws DAOOperationException;
	void deleteRouteById(int routeId) throws DAOOperationException;
	void markRouteAsDeletedById(int routeId) throws DAOOperationException;
}
