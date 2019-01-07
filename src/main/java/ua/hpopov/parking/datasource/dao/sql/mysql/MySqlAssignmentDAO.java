package ua.hpopov.parking.datasource.dao.sql.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ua.hpopov.parking.beans.AssignmentBean;
import ua.hpopov.parking.beans.AssignmentFull;
import ua.hpopov.parking.beans.BusBean;
import ua.hpopov.parking.beans.DriverFull;
import ua.hpopov.parking.beans.RouteBean;
import ua.hpopov.parking.beans.UserBean;
import ua.hpopov.parking.datasource.Strings;
import ua.hpopov.parking.datasource.dao.AssignmentDAO;
import ua.hpopov.parking.datasource.dao.DAOOperationException;
import ua.hpopov.parking.datasource.dao.ParsingWork;
import ua.hpopov.parking.datasource.dao.UpdateResult;

public class MySqlAssignmentDAO extends MySqlAbstractDAO implements AssignmentDAO {

	static final String FULL_TABLE_NAME, DRIVER_ID, DELEGATOR_USER_ID,
		BUS_ID, ROUTE_ID, DELEGATION_TIME, IS_CONFIRMED;
	static {
		FULL_TABLE_NAME = "`parking`.`assignment`";
		DRIVER_ID = "driver_id";
		DELEGATOR_USER_ID = "delegator_user_id";
		BUS_ID = "bus_id";
		ROUTE_ID = "route_id";
		DELEGATION_TIME = "delegation_time";
		IS_CONFIRMED = "is_confirmed";
	}
	
	@Override
	public void createAssignment(AssignmentBean assignment) throws DAOOperationException {
		String isConfirmed = (assignment.getIsConfirmed()==null? "DEFAULT":
			(assignment.getIsConfirmed()?"1":"0"));
		String sql = Strings.concat(
				"INSERT INTO ",FULL_TABLE_NAME," SET\r\n",
				DRIVER_ID,"="+assignment.getDriverId(),",\r\n",
				DELEGATOR_USER_ID,"="+assignment.getDelegatorId(),",\r\n",
				BUS_ID,"="+assignment.getBusId(),",\r\n",
				ROUTE_ID,"="+assignment.getRouteId(),",\r\n",
				IS_CONFIRMED,"=",isConfirmed,";"
				);
		executeCreateOperation(sql);
				
	}

	@Override
	public AssignmentFull getAssignmentWithoutDelegatorByUserId(int userId) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT * FROM (\r\n",
				"SELECT * FROM ",FULL_TABLE_NAME," WHERE\r\n",
				DELEGATOR_USER_ID,"="+userId,") AS `one_assignment`\r\n",
				"INNER JOIN ",MySqlDriverDAO.FULL_TABLE_NAME," AS `driver`\r\n",
				"ON `driver`.",MySqlDriverDAO.DRIVER_ID,"=`assignment`.",DRIVER_ID,"\r\n",
				"INNER JOIN ",MySqlUserDAO.FULL_TABLE_NAME," AS `user`\r\n",
				"ON `driver`.",MySqlDriverDAO.USER_ID,"=","`user`.",MySqlDriverDAO.USER_ID,"\r\n",
				"INNER JOIN ",MySqlBusDAO.FULL_TABLE_NAME," AS `bus`\r\n",
				"ON `assignment`.",MySqlBusDAO.BUS_ID,"=","`bus`.",MySqlBusDAO.BUS_ID,"\r\n",
				"INNER JOIN ",MySqlRouteDAO.FULL_TABLE_NAME," AS `route`\r\n",
				"ON `assignment`.",MySqlRouteDAO.ROUTE_ID,"=","`route`.",MySqlRouteDAO.ROUTE_ID,";"
				);
		ParsingWork<AssignmentFull> parsingWork = (rs)->{
			if (rs.next()) {
				return parseAssignmentFullWithoutDelegator(rs);
			}
			return null;
		};
		return executeRetrievement(sql, parsingWork);
	}

	private AssignmentFull parseAssignmentFullWithoutDelegator(ResultSet rs) throws SQLException {
		AssignmentFull result = new AssignmentFull();
		DriverFull driverFull = parseDriverFull(rs);
		UserBean delegatorUserBean = null;
		BusBean busBean = parseBusBean(rs);
		RouteBean routeBean = parseRouteBean(rs);
		Date delegationTime = new Date(rs.getTimestamp(DELEGATION_TIME).getTime());
		Boolean isConfirmed = rs.getBoolean(IS_CONFIRMED);
		result.setBusBean(busBean);
		result.setDelegationTime(delegationTime);
		result.setDelegatorUserBean(delegatorUserBean);
		result.setDriverFull(driverFull);
		result.setIsConfirmed(isConfirmed);
		result.setRouteBean(routeBean);
		return result;
	}

	private DriverFull parseDriverFull(ResultSet rs) throws SQLException {
		DriverFull driverFull = new DriverFull();
		int driverId = rs.getInt(DRIVER_ID);
		UserBean driverUserBean = parseUserBean(rs);
		driverFull.setDriverId(driverId);
		driverFull.setUserBean(driverUserBean);
		return driverFull;
	}

	private UserBean parseUserBean(ResultSet rs) throws SQLException {
		UserBean result = new UserBean();
		result.setName(rs.getString(MySqlUserDAO.NAME));
		result.setSurname(rs.getString(MySqlUserDAO.SURNAME));
		result.setUserId(rs.getInt(MySqlUserDAO.USER_ID));
		result.setUserTypeId(rs.getInt(MySqlUserDAO.USER_TYPE_ID));
		return result;
	}
	
	private BusBean parseBusBean(ResultSet rs) throws SQLException {
		BusBean result = new BusBean();
		result.setBusId(rs.getInt(MySqlBusDAO.BUS_ID));
		result.setBusModel(rs.getString(MySqlBusDAO.BUS_MODEL));
		result.setBusSerial(rs.getInt(MySqlBusDAO.BUS_SERIAL));
		return result;
	}

	private RouteBean parseRouteBean(ResultSet rs) throws SQLException {
		RouteBean result = new RouteBean();
		result.setRouteEndVertexId(rs.getInt(MySqlRouteDAO.ROUTE_END_VERTEX_ID));
		result.setRouteId(rs.getInt(MySqlRouteDAO.ROUTE_ID));
		result.setRouteNumber(rs.getInt(MySqlRouteDAO.ROUTE_NUMBER));
		result.setRouteStartVertexId(rs.getInt(MySqlRouteDAO.ROUTE_START_VERTEX_ID));
		return result;
	}


	@Override
	public AssignmentFull getAssignmentWithoutDelegatorByDriverId(int driverId) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT * FROM (\r\n",
				"SELECT * FROM ",FULL_TABLE_NAME," WHERE\r\n",
				DRIVER_ID,"="+driverId,") AS `one_assignment`\r\n",
				"INNER JOIN ",MySqlDriverDAO.FULL_TABLE_NAME," AS `driver`\r\n",
				"ON `driver`.",MySqlDriverDAO.DRIVER_ID,"=`assignment`.",DRIVER_ID,"\r\n",
				"INNER JOIN ",MySqlUserDAO.FULL_TABLE_NAME," AS `user`\r\n",
				"ON `driver`.",MySqlDriverDAO.USER_ID,"=","`user`.",MySqlDriverDAO.USER_ID,"\r\n",
				"INNER JOIN ",MySqlBusDAO.FULL_TABLE_NAME," AS `bus`\r\n",
				"ON `assignment`.",MySqlBusDAO.BUS_ID,"=","`bus`.",MySqlBusDAO.BUS_ID,"\r\n",
				"INNER JOIN ",MySqlRouteDAO.FULL_TABLE_NAME," AS `route`\r\n",
				"ON `assignment`.",MySqlRouteDAO.ROUTE_ID,"=","`route`.",MySqlRouteDAO.ROUTE_ID,";"
				);
		ParsingWork<AssignmentFull> parsingWork = (rs)->{
			if (rs.next()) {
				return parseAssignmentFullWithoutDelegator(rs);
			}
			return null;
		};
		return executeRetrievement(sql, parsingWork);
	}

	@Override
	public AssignmentFull getAssignmentWithoutDelegatorByBusId(int busId) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT * FROM (\r\n",
				"SELECT * FROM ",FULL_TABLE_NAME," WHERE\r\n",
				BUS_ID,"="+busId,") AS `one_assignment`\r\n",
				"INNER JOIN ",MySqlDriverDAO.FULL_TABLE_NAME," AS `driver`\r\n",
				"ON `driver`.",MySqlDriverDAO.DRIVER_ID,"=`assignment`.",DRIVER_ID,"\r\n",
				"INNER JOIN ",MySqlUserDAO.FULL_TABLE_NAME," AS `user`\r\n",
				"ON `driver`.",MySqlDriverDAO.USER_ID,"=","`user`.",MySqlDriverDAO.USER_ID,"\r\n",
				"INNER JOIN ",MySqlBusDAO.FULL_TABLE_NAME," AS `bus`\r\n",
				"ON `assignment`.",MySqlBusDAO.BUS_ID,"=","`bus`.",MySqlBusDAO.BUS_ID,"\r\n",
				"INNER JOIN ",MySqlRouteDAO.FULL_TABLE_NAME," AS `route`\r\n",
				"ON `assignment`.",MySqlRouteDAO.ROUTE_ID,"=","`route`.",MySqlRouteDAO.ROUTE_ID,";"
				);
		ParsingWork<AssignmentFull> parsingWork = (rs)->{
			if (rs.next()) {
				return parseAssignmentFullWithoutDelegator(rs);
			}
			return null;
		};
		return executeRetrievement(sql, parsingWork);
	}

	@Override
	public AssignmentFull getAssignmentWithoutDelegatorByRouteId(int routeId) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT * FROM (\r\n",
				"SELECT * FROM ",FULL_TABLE_NAME," WHERE\r\n",
				ROUTE_ID,"="+routeId,") AS `one_assignment`\r\n",
				"INNER JOIN ",MySqlDriverDAO.FULL_TABLE_NAME," AS `driver`\r\n",
				"ON `driver`.",MySqlDriverDAO.DRIVER_ID,"=`assignment`.",DRIVER_ID,"\r\n",
				"INNER JOIN ",MySqlUserDAO.FULL_TABLE_NAME," AS `user`\r\n",
				"ON `driver`.",MySqlDriverDAO.USER_ID,"=","`user`.",MySqlDriverDAO.USER_ID,"\r\n",
				"INNER JOIN ",MySqlBusDAO.FULL_TABLE_NAME," AS `bus`\r\n",
				"ON `assignment`.",MySqlBusDAO.BUS_ID,"=","`bus`.",MySqlBusDAO.BUS_ID,"\r\n",
				"INNER JOIN ",MySqlRouteDAO.FULL_TABLE_NAME," AS `route`\r\n",
				"ON `assignment`.",MySqlRouteDAO.ROUTE_ID,"=","`route`.",MySqlRouteDAO.ROUTE_ID,";"
				);
		ParsingWork<AssignmentFull> parsingWork = (rs)->{
			if (rs.next()) {
				return parseAssignmentFullWithoutDelegator(rs);
			}
			return null;
		};
		return executeRetrievement(sql, parsingWork);
	}

	@Override
	public List<AssignmentFull> getUnconfirmedAssignmentsPageByDelegatorId(int delegatorUserId, int fromKeyInd,
			int limit) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT * FROM (\r\n",
				"SELECT * FROM ",FULL_TABLE_NAME," WHERE\r\n",
				DELEGATOR_USER_ID,"="+delegatorUserId," AND ",DRIVER_ID,">="+fromKeyInd,"\r\n",
				"LIMIT "+limit,") AS `assignment`\r\n",
				"INNER JOIN ",MySqlDriverDAO.FULL_TABLE_NAME," AS `driver`\r\n",
				"ON `driver`.",MySqlDriverDAO.DRIVER_ID,"=`assignment`.",DRIVER_ID,"\r\n",
				"INNER JOIN ",MySqlUserDAO.FULL_TABLE_NAME," AS `user`\r\n",
				"ON `driver`.",MySqlDriverDAO.USER_ID,"=","`user`.",MySqlDriverDAO.USER_ID,"\r\n",
				"INNER JOIN ",MySqlBusDAO.FULL_TABLE_NAME," AS `bus`\r\n",
				"ON `assignment`.",MySqlBusDAO.BUS_ID,"=","`bus`.",MySqlBusDAO.BUS_ID,"\r\n",
				"INNER JOIN ",MySqlRouteDAO.FULL_TABLE_NAME," AS `route`\r\n",
				"ON `assignment`.",MySqlRouteDAO.ROUTE_ID,"=","`route`.",MySqlRouteDAO.ROUTE_ID,";"
				);
		ParsingWork<List<AssignmentFull>> parsingWork = (rs)->{
			List<AssignmentFull> result = new ArrayList<>();
			while(rs.next()) {
				result.add(parseAssignmentFullWithoutDelegator(rs));
			}
			return result;
		};
		return executeRetrievement(sql, parsingWork);
	}

	@Override
	public UpdateResult updateAssignmentByDriverId(AssignmentBean assignment) throws DAOOperationException {
		Date delegationTime = assignment.getDelegationTime();
		boolean isConfirmed = assignment.getIsConfirmed();
		String sql = Strings.concat(
				"UPDATE ",FULL_TABLE_NAME," SET\r\n",
				DELEGATOR_USER_ID,"="+assignment.getDelegatorId(),",\r\n",
				BUS_ID,"="+assignment.getBusId(),",\r\n",
				ROUTE_ID,"="+assignment.getRouteId(),",\r\n",
				DELEGATION_TIME,"=",
				delegationTime==null? "=NOW()\r\n": new Timestamp(delegationTime.getTime()).toString(),",\r\n",
				IS_CONFIRMED,"="+ (isConfirmed?1:0),
				"WHERE ",DRIVER_ID,"="+assignment.getDriverId(),";"
				);
		return executeUpdateOperation(sql);
	}

	@Override
	public UpdateResult deleteByDriverId(int driverId) throws DAOOperationException {
		String sql = Strings.concat(
				"DELETE FROM ",FULL_TABLE_NAME," WHERE\r\n",
				DRIVER_ID,"="+driverId,";"
				);
		return executeUpdateOperation(sql);
	}

	@Override
	public UpdateResult deleteByBusId(int busId) throws DAOOperationException {
		String sql = Strings.concat(
				"DELETE FROM ",FULL_TABLE_NAME," WHERE\r\n",
				BUS_ID,"="+busId,";"
				);
		return executeUpdateOperation(sql);
	}

	@Override
	public UpdateResult deleteAssignmentsByRouteId(int routeId) throws DAOOperationException {
		String sql = Strings.concat(
				"DELETE FROM ",FULL_TABLE_NAME," WHERE\r\n",
				ROUTE_ID,"="+routeId,";"
				);
		return executeUpdateOperation(sql);
	}

	@Override
	public Integer getUnconfirmedAssignmentsByDelegatorIdCount(int delegatorUserId) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT COUNT(*) FROM ",FULL_TABLE_NAME,"\r\n",
				"WHERE ",DELEGATOR_USER_ID,"="+delegatorUserId," AND ",IS_CONFIRMED,"=0;"
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
