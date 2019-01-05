package ua.hpopov.parking.datasource.dao.sql.mysql;

import java.util.List;

import ua.hpopov.parking.beans.RouteBean;
import ua.hpopov.parking.beans.RouteFull;
import ua.hpopov.parking.datasource.dao.DAOOperationException;
import ua.hpopov.parking.datasource.dao.RouteDAO;
import ua.hpopov.parking.datasource.dao.UpdateResult;

public class MySqlRouteDAO extends MySqlAbstractDAO implements RouteDAO {

	static final String FULL_TABLE_NAME,ROUTE_ID, ROUTE_START_VERTEX_ID, ROUTE_END_VERTEX_ID, ROUTE_NUMBER;
	static {
		FULL_TABLE_NAME = "`parking`.`route`";
		ROUTE_ID = "`route_id`";
		ROUTE_START_VERTEX_ID = "`route_start_vertex_id`";
		ROUTE_END_VERTEX_ID = "`route_end_vertex_id`";
		ROUTE_NUMBER = "`route_number`";
	}
	
	@Override
	public void createRoute(RouteBean route) throws DAOOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public RouteFull getRouteById(int routeId) throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RouteFull> getAllRoutesPage(int fromKeyInd, int limit) throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RouteFull> getAllFreeRoutesPage(int fromKeyInd, int limit) throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RouteFull> getAllBusyRoutesPage(int fromKeyInd, int limit) throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateResult updateRoute(RouteBean route) throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRouteById(int routeId) throws DAOOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void markRouteAsDeletedById(int routeId) throws DAOOperationException {
		// TODO Auto-generated method stub

	}

}
