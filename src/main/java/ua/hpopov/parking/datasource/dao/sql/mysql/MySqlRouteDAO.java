package ua.hpopov.parking.datasource.dao.sql.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.hpopov.parking.beans.RouteBean;
import ua.hpopov.parking.beans.RouteFull;
import ua.hpopov.parking.beans.RouteVertexBean;
import ua.hpopov.parking.datasource.Strings;
import ua.hpopov.parking.datasource.dao.DAOOperationException;
import ua.hpopov.parking.datasource.dao.ParsingWork;
import ua.hpopov.parking.datasource.dao.RouteDAO;
import ua.hpopov.parking.datasource.dao.UpdateResult;

public class MySqlRouteDAO extends MySqlAbstractDAO implements RouteDAO {

	static final String FULL_TABLE_NAME,ROUTE_ID, ROUTE_START_VERTEX_ID, ROUTE_END_VERTEX_ID, ROUTE_NUMBER;
	static {
		FULL_TABLE_NAME = "`parking`.`route`";
		ROUTE_ID = "route_id";
		ROUTE_START_VERTEX_ID = "route_start_vertex_id";
		ROUTE_END_VERTEX_ID = "route_end_vertex_id";
		ROUTE_NUMBER = "route_number";
	}
	
	@Override
	public void createRoute(RouteBean route) throws DAOOperationException {
		String sql = Strings.concat(
				"INSERT INTO ",FULL_TABLE_NAME," SET\r\n",
				ROUTE_START_VERTEX_ID,"="+route.getRouteStartVertexId(),"\r\n",
				ROUTE_END_VERTEX_ID,"="+route.getRouteEndVertexId(),"\r\n",
				ROUTE_NUMBER,"="+route.getRouteNumber(),";"
				);
		executeCreateOperation(sql);
	}

	@Override
	public RouteFull getRouteById(int routeId) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT `route`.*,\r\n",
				"`s_vertex`.`route_vertex_id` AS `s_vertex_id`,\r\n",
				"`s_vertex`.`route_vertex_name` AS `s_vertex_name`,\r\n",
				"`e_vertex`.* FROM (\r\n",
				"SELECT * FROM ",FULL_TABLE_NAME,"\r\n",
				"WHERE ",ROUTE_ID,"="+routeId,"\r\n",
				") AS `route`\r\n",
				"INNER JOIN ",MySqlRouteVertexDAO.FULL_TABLE_NAME," AS `s_vertex`\r\n",
				"ON `route`.",ROUTE_START_VERTEX_ID,"=","`s_vertex`.",MySqlRouteVertexDAO.ROUTE_VERTEX_ID,"\r\n",
				"INNER JOIN ",MySqlRouteVertexDAO.FULL_TABLE_NAME," AS `e_vertex`\r\n",
				"ON `route`.",ROUTE_END_VERTEX_ID,"=","`e_vertex`.",MySqlRouteVertexDAO.ROUTE_VERTEX_ID,";"
				);
		ParsingWork<RouteFull> parsingWork = (rs)->{
			if (rs.next()) {
				return parseRouteFull(rs);
			}
			return null;
		};
		return executeRetrievement(sql, parsingWork);
	}

	private RouteFull parseRouteFull(ResultSet rs) throws SQLException {
		RouteFull result = new RouteFull();
		result.setRouteId(rs.getInt(ROUTE_ID));
		result.setRouteStartVertex(parseRouteVertexBean(rs,"s_vertex_id","s_vertex_name"));
		result.setRouteEndVertex(parseRouteVertexBean(rs, MySqlRouteVertexDAO.ROUTE_VERTEX_ID,
				MySqlRouteVertexDAO.ROUTE_VERTEX_NAME));
		result.setRouteNumber(rs.getInt(ROUTE_NUMBER));
		return result;
	}

	private RouteVertexBean parseRouteVertexBean(ResultSet rs, String id, String name) throws SQLException {
		RouteVertexBean result = new RouteVertexBean();
		result.setRouteVertexId(rs.getInt(id));
		result.setRouteVertexName(rs.getString(name));
		return result;
	}

	@Override
	public List<RouteFull> getAllRoutesPage(int fromKeyInd, int limit) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT `route`.*,\r\n",
				"`s_vertex`.`route_vertex_id` AS `s_vertex_id`,\r\n",
				"`s_vertex`.`route_vertex_name` AS `s_vertex_name`,\r\n",
				"`e_vertex`.* FROM (\r\n",
				"SELECT * FROM ",FULL_TABLE_NAME,"\r\n",
				"WHERE ",ROUTE_ID,">="+fromKeyInd,"\r\n",
				"LIMIT "+limit,"\r\n",
				") AS `route`\r\n",
				"INNER JOIN ",MySqlRouteVertexDAO.FULL_TABLE_NAME," AS `s_vertex`\r\n",
				"ON `route`.",ROUTE_START_VERTEX_ID,"=","`s_vertex`.",MySqlRouteVertexDAO.ROUTE_VERTEX_ID,"\r\n",
				"INNER JOIN ",MySqlRouteVertexDAO.FULL_TABLE_NAME," AS `e_vertex`\r\n",
				"ON `route`.",ROUTE_END_VERTEX_ID,"=","`e_vertex`.",MySqlRouteVertexDAO.ROUTE_VERTEX_ID,";"
				);
		ParsingWork<List<RouteFull>> parsingWork = (rs)->{
			List<RouteFull> result = new ArrayList<>();
			while(rs.next()) {
				result.add(parseRouteFull(rs));
			}
			return result;
		};
		return executeRetrievement(sql, parsingWork);
	}

	@Override
	public List<RouteFull> getAllFreeRoutesPage(int fromKeyInd, int limit) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT `route`.*,\r\n",
				"`s_vertex`.`route_vertex_id` AS `s_vertex_id`,\r\n",
				"`s_vertex`.`route_vertex_name` AS `s_vertex_name`,\r\n",
				"`e_vertex`.* FROM (\r\n",
				"SELECT * FROM ",FULL_TABLE_NAME," AS `just_route`\r\n",
				"LEFT OUTER JOIN ",MySqlAssignmentDAO.FULL_TABLE_NAME," AS `assignment`\r\n",
				"ON `just_route`.",ROUTE_ID,"=","`assignment`.",MySqlAssignmentDAO.ROUTE_ID,"\r\n",
				"WHERE ",ROUTE_ID,">="+fromKeyInd," AND `assignment`.",MySqlAssignmentDAO.ROUTE_ID," IS NULL\r\n",
				"LIMIT "+limit,"\r\n",
				") AS `route`\r\n",
				"INNER JOIN ",MySqlRouteVertexDAO.FULL_TABLE_NAME," AS `s_vertex`\r\n",
				"ON `route`.",ROUTE_START_VERTEX_ID,"=","`s_vertex`.",MySqlRouteVertexDAO.ROUTE_VERTEX_ID,"\r\n",
				"INNER JOIN ",MySqlRouteVertexDAO.FULL_TABLE_NAME," AS `e_vertex`\r\n",
				"ON `route`.",ROUTE_END_VERTEX_ID,"=","`e_vertex`.",MySqlRouteVertexDAO.ROUTE_VERTEX_ID,";"
				);
		ParsingWork<List<RouteFull>> parsingWork = (rs)->{
			List<RouteFull> result = new ArrayList<>();
			while(rs.next()) {
				result.add(parseRouteFull(rs));
			}
			return result;
		};
		return executeRetrievement(sql, parsingWork);
	}

	@Override
	public List<RouteFull> getAllBusyRoutesPage(int fromKeyInd, int limit) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT `route`.*,\r\n",
				"`s_vertex`.`route_vertex_id` AS `s_vertex_id`,\r\n",
				"`s_vertex`.`route_vertex_name` AS `s_vertex_name`,\r\n",
				"`e_vertex`.* FROM (\r\n",
				"SELECT * FROM ",FULL_TABLE_NAME," AS `just_route`\r\n",
				"LEFT OUTER JOIN ",MySqlAssignmentDAO.FULL_TABLE_NAME," AS `assignment`\r\n",
				"ON `just_route`.",ROUTE_ID,"=","`assignment`.",MySqlAssignmentDAO.ROUTE_ID,"\r\n",
				"WHERE ",ROUTE_ID,">="+fromKeyInd," AND `assignment`.",MySqlAssignmentDAO.ROUTE_ID," IS NOT NULL\r\n",
				"LIMIT "+limit,"\r\n",
				") AS `route`\r\n",
				"INNER JOIN ",MySqlRouteVertexDAO.FULL_TABLE_NAME," AS `s_vertex`\r\n",
				"ON `route`.",ROUTE_START_VERTEX_ID,"=","`s_vertex`.",MySqlRouteVertexDAO.ROUTE_VERTEX_ID,"\r\n",
				"INNER JOIN ",MySqlRouteVertexDAO.FULL_TABLE_NAME," AS `e_vertex`\r\n",
				"ON `route`.",ROUTE_END_VERTEX_ID,"=","`e_vertex`.",MySqlRouteVertexDAO.ROUTE_VERTEX_ID,";"
				);
		ParsingWork<List<RouteFull>> parsingWork = (rs)->{
			List<RouteFull> result = new ArrayList<>();
			while(rs.next()) {
				result.add(parseRouteFull(rs));
			}
			return result;
		};
		return executeRetrievement(sql, parsingWork);
	}

	@Override
	public UpdateResult updateRoute(RouteBean route) throws DAOOperationException {
		String sql = Strings.concat(
				"UPDATE ",FULL_TABLE_NAME," SET\r\n",
				ROUTE_START_VERTEX_ID,"="+route.getRouteStartVertexId(),"\r\n",
				ROUTE_END_VERTEX_ID,"="+route.getRouteEndVertexId(),"\r\n",
				ROUTE_NUMBER,"="+route.getRouteNumber(),"\r\n",
				"WHERE ",ROUTE_ID,"="+route.getRouteId(),";"
				);
		return executeUpdateOperation(sql);
	}

	@Override
	public UpdateResult deleteRouteById(int routeId) throws DAOOperationException {
		String sql = Strings.concat(
				"DELETE FROM ",FULL_TABLE_NAME," WHERE\r\n",
				ROUTE_ID,"="+routeId,";"
				);
		return executeUpdateOperation(sql);
	}

	@Override
	public UpdateResult markRouteAsDeletedById(int routeId) throws DAOOperationException {
		String sql = Strings.concat(
				"UPDATE ",FULL_TABLE_NAME,"\r\n",
				"SET ",ROUTE_ID,"="+(routeId>0?(-routeId):routeId),"\r\n",
				"WHERE ",ROUTE_ID,"="+routeId,";"
				);
		return executeUpdateOperation(sql);
	}

	@Override
	public Integer getAllRoutesCount() throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT COUNT(*) FROM ",FULL_TABLE_NAME,";"
				);
		ParsingWork<Integer> parsingWork = (rs)->{
			if (rs.next()) {
				return rs.getInt(0);
			}
			return null;
		};
		return executeRetrievement(sql, parsingWork);
	}

	@Override
	public Integer getAllFreeRoutesCount() throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT COUNT(*) FROM\r\n",
				FULL_TABLE_NAME," AS `just_route`\r\n",
				"LEFT OUTER JOIN ",MySqlAssignmentDAO.FULL_TABLE_NAME," AS `assignment`\r\n",
				"ON `just_route`.",ROUTE_ID,"=","`assignment`.",MySqlAssignmentDAO.ROUTE_ID,"\r\n",
				"WHERE `assignment`.",MySqlAssignmentDAO.ROUTE_ID," IS NULL;"
				);
		ParsingWork<Integer> parsingWork = (rs)->{
			if (rs.next()) {
				return rs.getInt(0);
			}
			return null;
		};
		return executeRetrievement(sql, parsingWork);
	}

	@Override
	public Integer getAllBusyRoutesCount() throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT COUNT(*) FROM\r\n",
				FULL_TABLE_NAME," AS `just_route`\r\n",
				"LEFT OUTER JOIN ",MySqlAssignmentDAO.FULL_TABLE_NAME," AS `assignment`\r\n",
				"ON `just_route`.",ROUTE_ID,"=","`assignment`.",MySqlAssignmentDAO.ROUTE_ID,"\r\n",
				"WHERE `assignment`.",MySqlAssignmentDAO.ROUTE_ID," IS NOT NULL;"
				);
		ParsingWork<Integer> parsingWork = (rs)->{
			if (rs.next()) {
				return rs.getInt(0);
			}
			return null;
		};
		return executeRetrievement(sql, parsingWork);
	}

}
