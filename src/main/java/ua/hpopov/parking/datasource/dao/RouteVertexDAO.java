package ua.hpopov.parking.datasource.dao;

import ua.hpopov.parking.beans.RouteVertexBean;

public interface RouteVertexDAO extends DAO {
	void createVertex(String routeVertexName) throws DAOOperationException;
	RouteVertexBean getVertexByName(String routeVertexName) throws DAOOperationException;
	//List<RouteVertexBean> getAllVerticesPage(int fromKeyInd, int limit) throws DAOOperationException;
	//UpdateResult updateRouteVertexById(RouteVertexBean routeVertex) throws DAOOperationException;
	UpdateResult deleteRouteVertexById(int routeVertexId) throws DAOOperationException;
	UpdateResult markRouteVertexByIdAsDeleted(int routeVertexId) throws DAOOperationException;
}
