package ua.hpopov.parking.datasource.dao;

import ua.hpopov.parking.beans.RouteBean;

public interface RouteDAO {
	void createRoute(RouteBean route) throws DAOOperationException;
	RouteBean getRouteById(int routeId) throws DAOOperationException;
	UpdateResult updateRoute(RouteBean route) throws DAOOperationException;
	void deleteRouteById(int routeId) throws DAOOperationException;
}
