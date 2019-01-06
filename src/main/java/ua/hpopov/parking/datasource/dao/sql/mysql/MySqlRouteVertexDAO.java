package ua.hpopov.parking.datasource.dao.sql.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import ua.hpopov.parking.beans.RouteVertexBean;
import ua.hpopov.parking.datasource.Strings;
import ua.hpopov.parking.datasource.dao.DAOOperationException;
import ua.hpopov.parking.datasource.dao.ParsingWork;
import ua.hpopov.parking.datasource.dao.RouteVertexDAO;
import ua.hpopov.parking.datasource.dao.UpdateResult;

public class MySqlRouteVertexDAO extends MySqlAbstractDAO implements RouteVertexDAO {

	static final String FULL_TABLE_NAME, ROUTE_VERTEX_ID, ROUTE_VERTEX_NAME;
	static {
		FULL_TABLE_NAME="`parking`.`route_vertex`";
		ROUTE_VERTEX_ID="route_vertex_id";
		ROUTE_VERTEX_NAME="route_vertex_name";
	}
	
	@Override
	public void createVertex(String routeVertexName) throws DAOOperationException {
		String sql = Strings.concat(
				"INSERT INTO ",FULL_TABLE_NAME," SET\r\n",
				ROUTE_VERTEX_NAME,"='"+routeVertexName,"';"
				);
		executeCreateOperation(sql);
	}

	@Override
	public RouteVertexBean getVertexByName(String routeVertexName) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT * FROM ",FULL_TABLE_NAME,"\r\n",
				"WHERE ",ROUTE_VERTEX_NAME,"='"+routeVertexName,"';"
				);
		ParsingWork<RouteVertexBean> parsingWork = (rs)->{
			if (rs.next()) {
				return parseRouteVertexBean(rs);
			}
			return null;
		};
		return executeRetrievement(sql, parsingWork);
	}

	private RouteVertexBean parseRouteVertexBean(ResultSet rs) throws SQLException {
		RouteVertexBean result = new RouteVertexBean();
		result.setRouteVertexId(rs.getInt(ROUTE_VERTEX_ID));
		result.setRouteVertexName(rs.getString(ROUTE_VERTEX_NAME));
		return result;
	}

	@Override
	public UpdateResult deleteRouteVertexById(int routeVertexId) throws DAOOperationException {
		String sql = Strings.concat(
				"DELETE FROM ",FULL_TABLE_NAME," WHERE\r\n",
				ROUTE_VERTEX_ID,"="+routeVertexId,";"
				);
		return executeUpdateOperation(sql);
	}

	@Override
	public UpdateResult markRouteVertexByIdAsDeleted(int routeVertexId) throws DAOOperationException {
		String sql = Strings.concat(
				"UPDATE ",FULL_TABLE_NAME,"\r\n",
				"SET ",ROUTE_VERTEX_ID,"="+(routeVertexId>0?(-routeVertexId):routeVertexId),"\r\n",
				"WHERE ",ROUTE_VERTEX_ID,"="+routeVertexId,";"
				);
		return executeUpdateOperation(sql);
	}

}
