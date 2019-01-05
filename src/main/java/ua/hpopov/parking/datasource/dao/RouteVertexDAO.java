package ua.hpopov.parking.datasource.dao;

import java.util.List;

import ua.hpopov.parking.beans.RouteVertexBean;

public interface RouteVertexDAO extends DAO {
	void createVertex(String routeVertexName) throws DAOOperationException;
	List<RouteVertexBean> getVerticesByRouteId(int routeId) throws DAOOperationException;
	RouteVertexBean getVertexByName(String routeVertexName) throws DAOOperationException;
	//List<RouteVertexBean> getAllVerticesPage(int fromKeyInd, int limit) throws DAOOperationException;
	//UpdateResult updateRouteVertexById(RouteVertexBean routeVertex) throws DAOOperationException;
	void deleteRouteVertexById(int routeVertexId) throws DAOOperationException;
	void markRouteVertexByIdAsDeleted(int routeVertexId) throws DAOOperationException;
}
