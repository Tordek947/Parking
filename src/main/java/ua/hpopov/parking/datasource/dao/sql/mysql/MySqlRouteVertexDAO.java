package ua.hpopov.parking.datasource.dao.sql.mysql;

import java.util.List;

import ua.hpopov.parking.beans.RouteVertexBean;
import ua.hpopov.parking.datasource.dao.DAOOperationException;
import ua.hpopov.parking.datasource.dao.RouteVertexDAO;

public class MySqlRouteVertexDAO extends MySqlAbstractDAO implements RouteVertexDAO {

	@Override
	public void createVertex(String routeVertexName) throws DAOOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<RouteVertexBean> getVerticesByRouteId(int routeId) throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RouteVertexBean getVertexByName(String routeVertexName) throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRouteVertexById(int routeVertexId) throws DAOOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void markRouteVertexByIdAsDeleted(int routeVertexId) throws DAOOperationException {
		// TODO Auto-generated method stub

	}

}
