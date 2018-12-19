package ua.hpopov.parking.datasource.dao;

import java.util.List;

import ua.hpopov.parking.beans.RouteVertexBean;

public interface RouteVertexDAO extends DAO {
	void createVertex(String routeVertexName) throws DAOOperationException;
	List<RouteVertexBean> getVerticesByRouteId(int routeId) throws DAOOperationException;
	RouteVertexBean getVertexByName(String routeVertexName) throws DAOOperationException;
}
