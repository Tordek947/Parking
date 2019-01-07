package ua.hpopov.parking.datasource.dao.sql.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ua.hpopov.parking.beans.AssignmentJournalBean;
import ua.hpopov.parking.beans.AssignmentJournalFull;
import ua.hpopov.parking.beans.BusBean;
import ua.hpopov.parking.beans.DriverFull;
import ua.hpopov.parking.beans.RouteBean;
import ua.hpopov.parking.beans.UserBean;
import ua.hpopov.parking.datasource.Strings;
import ua.hpopov.parking.datasource.dao.AssignmentJournalDAO;
import ua.hpopov.parking.datasource.dao.DAOOperationException;
import ua.hpopov.parking.datasource.dao.ParsingWork;

public class MySqlAssignmentJournalDAO extends MySqlAbstractDAO implements AssignmentJournalDAO {

	static final String FULL_TABLE_NAME, ASSIGNMENT_ID, DELEGATION_TIME, CONFIRMATION_TIME, DELEGATOR_USER_ID,
	ASSIGNEE_DRIVER_ID,	BUS_ID, ROUTE_ID;
	static {
		FULL_TABLE_NAME = "`parking`.`assignment_journal`";
		ASSIGNMENT_ID = "assignment_id";
		DELEGATION_TIME = "delegation_time";
		CONFIRMATION_TIME = "confirmation_time";
		DELEGATOR_USER_ID = "delegator_user_id";
		ASSIGNEE_DRIVER_ID = "assignee_driver_id";
		BUS_ID = "bus_id";
		ROUTE_ID = "route_id";
	}
	
	@Override
	public void create(AssignmentJournalBean assignment) throws DAOOperationException {
		String delegationTime = (assignment.getDelegationTime()==null)?"NOW()":
				new Timestamp(assignment.getDelegationTime().getTime()).toString();
		String confirmationTime = (assignment.getConfirmationTime()==null)?"NULL":
			new Timestamp(assignment.getConfirmationTime().getTime()).toString();
		String sql = Strings.concat(
				"INSERT INTO ",FULL_TABLE_NAME," SET\r\n",
				DELEGATION_TIME,"=",delegationTime,",\r\n",
				CONFIRMATION_TIME,"=",confirmationTime,",\r\n",
				DELEGATOR_USER_ID,"="+assignment.getDelegatorUserId(),",\r\n",
				ASSIGNEE_DRIVER_ID,"="+assignment.getAssigneeDriverId(),",\r\n",
				BUS_ID,"="+assignment.getBusId(),",\r\n",
				ROUTE_ID,"="+assignment.getRouteId(),";"
				);
		executeCreateOperation(sql);
	}

	@Override
	public Integer getAssignmentsByDelegatorIdCount(int delegatorUserId) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT COUNT(*) FROM ",FULL_TABLE_NAME,"\r\n",
				"WHERE ",DELEGATOR_USER_ID,"="+delegatorUserId,";"
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
	public List<AssignmentJournalFull> getAssignmentsPageByDelegatorId(int delegatorUserId, int fromKeyInd, int limit)
			throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT `assignment`.*,`user`.*, `bus`.*, `route`.*,\r\n",
				"`d_user`.",MySqlUserDAO.USER_ID," AS 'd_user_id', `d_user`.",MySqlUserDAO.NAME," AS 'd_name',\r\n",
				"`d_user`.",MySqlUserDAO.SURNAME," AS 'd_surname',\r\n",
				"`d_user`.",MySqlUserDAO.USER_TYPE_ID," AS 'd_user_type_id' FROM (\r\n",
				"SELECT * FROM ",FULL_TABLE_NAME," WHERE\r\n",
				DELEGATOR_USER_ID,"="+delegatorUserId," AND ",ASSIGNMENT_ID,">="+fromKeyInd,"\r\n",
				"LIMIT "+limit,") AS `assignment`\r\n",
				"INNER JOIN ",MySqlDriverDAO.FULL_TABLE_NAME," AS `driver`\r\n",
				"ON `driver`.",MySqlDriverDAO.DRIVER_ID,"=`assignment`.",ASSIGNEE_DRIVER_ID,"\r\n",
				"INNER JOIN ",MySqlUserDAO.FULL_TABLE_NAME," AS `user`\r\n",
				"ON `driver`.",MySqlDriverDAO.USER_ID,"=","`user`.",MySqlDriverDAO.USER_ID,"\r\n",
				"INNER JOIN ",MySqlUserDAO.FULL_TABLE_NAME," AS `d_user`\r\n",
				"ON `assignment`.",DELEGATOR_USER_ID,"=","`d_user`.",MySqlDriverDAO.USER_ID,"\r\n",
				"INNER JOIN ",MySqlBusDAO.FULL_TABLE_NAME," AS `bus`\r\n",
				"ON `assignment`.",MySqlBusDAO.BUS_ID,"=","`bus`.",MySqlBusDAO.BUS_ID,"\r\n",
				"INNER JOIN ",MySqlRouteDAO.FULL_TABLE_NAME," AS `route`\r\n",
				"ON `assignment`.",MySqlRouteDAO.ROUTE_ID,"=","`route`.",MySqlRouteDAO.ROUTE_ID,";"
				);
		ParsingWork<List<AssignmentJournalFull>> parsingWork = (rs)->{
			List<AssignmentJournalFull> result = new ArrayList<>();
			while(rs.next()) {
				result.add(parseAssignmentJournalFull(rs));
			}
			return result;
		};
		return executeRetrievement(sql, parsingWork);
	}

	private AssignmentJournalFull parseAssignmentJournalFull(ResultSet rs) throws SQLException {
		AssignmentJournalFull result = new AssignmentJournalFull();
		Long assignmentId = rs.getLong(ASSIGNMENT_ID);
		Date delegationTime = parseDate(rs,DELEGATION_TIME);
		Date confirmationTime = parseDate(rs,CONFIRMATION_TIME);
		UserBean delegatorUserBean = parseDelegatorUserBean(rs);
		DriverFull assigneeDriverFull = parseDriverFull(rs);
		BusBean busBean = parseBusBean(rs);
		RouteBean routeBean = parseRouteBean(rs);
		result.setAssigneeDriverFull(assigneeDriverFull);
		result.setAssignmentId(assignmentId);
		result.setBusBean(busBean);
		result.setConfirmationTime(confirmationTime);
		result.setDelegationTime(delegationTime);
		result.setDelegatorUserBean(delegatorUserBean);
		result.setRouteBean(routeBean);
		return result;
	}

	private Date parseDate(ResultSet rs, String columnName) throws SQLException {
		Timestamp timestamp = rs.getTimestamp(columnName);
		if (timestamp == null) {
			return null;
		}
		return new Date(timestamp.getTime());
	}

	private UserBean parseDelegatorUserBean(ResultSet rs) throws SQLException {
		UserBean result = new UserBean();
		result.setName(rs.getString("d_name"));
		result.setSurname(rs.getString("d_surname"));
		result.setUserId(rs.getInt("d_user_id"));
		result.setUserTypeId(rs.getInt("d_user_type_id"));
		return result;
	}

	private DriverFull parseDriverFull(ResultSet rs) throws SQLException {
		DriverFull driverFull = new DriverFull();
		int driverId = rs.getInt(ASSIGNEE_DRIVER_ID);
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
	public Integer getAssignmentsPageByAssigneeIdCount(int assigneeDriverId) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT COUNT(*) FROM ",FULL_TABLE_NAME,"\r\n",
				"WHERE ",ASSIGNEE_DRIVER_ID,"="+assigneeDriverId,";"
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
	public List<AssignmentJournalFull> getAssignmentsPageByAssigneeId(int assigneeDriverId, int fromKeyInd, int limit)
			throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT `assignment`.*,`user`.*, `bus`.*, `route`.*,\r\n",
				"`d_user`.",MySqlUserDAO.USER_ID," AS 'd_user_id', `d_user`.",MySqlUserDAO.NAME," AS 'd_name',\r\n",
				"`d_user`.",MySqlUserDAO.SURNAME," AS 'd_surname',\r\n",
				"`d_user`.",MySqlUserDAO.USER_TYPE_ID," AS 'd_user_type_id' FROM (\r\n",
				"SELECT * FROM ",FULL_TABLE_NAME," WHERE\r\n",
				ASSIGNEE_DRIVER_ID,"="+assigneeDriverId," AND ",ASSIGNMENT_ID,">="+fromKeyInd,"\r\n",
				"LIMIT "+limit,") AS `assignment`\r\n",
				"INNER JOIN ",MySqlDriverDAO.FULL_TABLE_NAME," AS `driver`\r\n",
				"ON `driver`.",MySqlDriverDAO.DRIVER_ID,"=`assignment`.",ASSIGNEE_DRIVER_ID,"\r\n",
				"INNER JOIN ",MySqlUserDAO.FULL_TABLE_NAME," AS `user`\r\n",
				"ON `driver`.",MySqlDriverDAO.USER_ID,"=","`user`.",MySqlDriverDAO.USER_ID,"\r\n",
				"INNER JOIN ",MySqlUserDAO.FULL_TABLE_NAME," AS `d_user`\r\n",
				"ON `assignment`.",DELEGATOR_USER_ID,"=","`d_user`.",MySqlDriverDAO.USER_ID,"\r\n",
				"INNER JOIN ",MySqlBusDAO.FULL_TABLE_NAME," AS `bus`\r\n",
				"ON `assignment`.",MySqlBusDAO.BUS_ID,"=","`bus`.",MySqlBusDAO.BUS_ID,"\r\n",
				"INNER JOIN ",MySqlRouteDAO.FULL_TABLE_NAME," AS `route`\r\n",
				"ON `assignment`.",MySqlRouteDAO.ROUTE_ID,"=","`route`.",MySqlRouteDAO.ROUTE_ID,";"
				);
		ParsingWork<List<AssignmentJournalFull>> parsingWork = (rs)->{
			List<AssignmentJournalFull> result = new ArrayList<>();
			while(rs.next()) {
				result.add(parseAssignmentJournalFull(rs));
			}
			return result;
		};
		return executeRetrievement(sql, parsingWork);
	}

	@Override
	public Integer getAssignmentsPageByBusIdCount(int busId) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT COUNT(*) FROM ",FULL_TABLE_NAME,"\r\n",
				"WHERE ",BUS_ID,"="+busId,";"
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
	public List<AssignmentJournalFull> getAssignmentsPageByBusId(int busId, int fromKeyInd, int limit)
			throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT `assignment`.*,`user`.*, `bus`.*, `route`.*,\r\n",
				"`d_user`.",MySqlUserDAO.USER_ID," AS 'd_user_id', `d_user`.",MySqlUserDAO.NAME," AS 'd_name',\r\n",
				"`d_user`.",MySqlUserDAO.SURNAME," AS 'd_surname',\r\n",
				"`d_user`.",MySqlUserDAO.USER_TYPE_ID," AS 'd_user_type_id' FROM (\r\n",
				"SELECT * FROM ",FULL_TABLE_NAME," WHERE\r\n",
				BUS_ID,"="+busId," AND ",ASSIGNMENT_ID,">="+fromKeyInd,"\r\n",
				"LIMIT "+limit,") AS `assignment`\r\n",
				"INNER JOIN ",MySqlDriverDAO.FULL_TABLE_NAME," AS `driver`\r\n",
				"ON `driver`.",MySqlDriverDAO.DRIVER_ID,"=`assignment`.",ASSIGNEE_DRIVER_ID,"\r\n",
				"INNER JOIN ",MySqlUserDAO.FULL_TABLE_NAME," AS `user`\r\n",
				"ON `driver`.",MySqlDriverDAO.USER_ID,"=","`user`.",MySqlDriverDAO.USER_ID,"\r\n",
				"INNER JOIN ",MySqlUserDAO.FULL_TABLE_NAME," AS `d_user`\r\n",
				"ON `assignment`.",DELEGATOR_USER_ID,"=","`d_user`.",MySqlDriverDAO.USER_ID,"\r\n",
				"INNER JOIN ",MySqlBusDAO.FULL_TABLE_NAME," AS `bus`\r\n",
				"ON `assignment`.",MySqlBusDAO.BUS_ID,"=","`bus`.",MySqlBusDAO.BUS_ID,"\r\n",
				"INNER JOIN ",MySqlRouteDAO.FULL_TABLE_NAME," AS `route`\r\n",
				"ON `assignment`.",MySqlRouteDAO.ROUTE_ID,"=","`route`.",MySqlRouteDAO.ROUTE_ID,";"
				);
		ParsingWork<List<AssignmentJournalFull>> parsingWork = (rs)->{
			List<AssignmentJournalFull> result = new ArrayList<>();
			while(rs.next()) {
				result.add(parseAssignmentJournalFull(rs));
			}
			return result;
		};
		return executeRetrievement(sql, parsingWork);
	}

	@Override
	public Integer getAssignmentsPageByRouteIdCount(int routeId) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT COUNT(*) FROM ",FULL_TABLE_NAME,"\r\n",
				"WHERE ",ROUTE_ID,"="+routeId,";"
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
	public List<AssignmentJournalFull> getAssignmentsPageByRouteId(int routeId, int fromKeyInd, int limit)
			throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT `assignment`.*,`user`.*, `bus`.*, `route`.*,\r\n",
				"`d_user`.",MySqlUserDAO.USER_ID," AS 'd_user_id', `d_user`.",MySqlUserDAO.NAME," AS 'd_name',\r\n",
				"`d_user`.",MySqlUserDAO.SURNAME," AS 'd_surname',\r\n",
				"`d_user`.",MySqlUserDAO.USER_TYPE_ID," AS 'd_user_type_id' FROM (\r\n",
				"SELECT * FROM ",FULL_TABLE_NAME," WHERE\r\n",
				ROUTE_ID,"="+routeId," AND ",ASSIGNMENT_ID,">="+fromKeyInd,"\r\n",
				"LIMIT "+limit,") AS `assignment`\r\n",
				"INNER JOIN ",MySqlDriverDAO.FULL_TABLE_NAME," AS `driver`\r\n",
				"ON `driver`.",MySqlDriverDAO.DRIVER_ID,"=`assignment`.",ASSIGNEE_DRIVER_ID,"\r\n",
				"INNER JOIN ",MySqlUserDAO.FULL_TABLE_NAME," AS `user`\r\n",
				"ON `driver`.",MySqlDriverDAO.USER_ID,"=","`user`.",MySqlDriverDAO.USER_ID,"\r\n",
				"INNER JOIN ",MySqlUserDAO.FULL_TABLE_NAME," AS `d_user`\r\n",
				"ON `assignment`.",DELEGATOR_USER_ID,"=","`d_user`.",MySqlDriverDAO.USER_ID,"\r\n",
				"INNER JOIN ",MySqlBusDAO.FULL_TABLE_NAME," AS `bus`\r\n",
				"ON `assignment`.",MySqlBusDAO.BUS_ID,"=","`bus`.",MySqlBusDAO.BUS_ID,"\r\n",
				"INNER JOIN ",MySqlRouteDAO.FULL_TABLE_NAME," AS `route`\r\n",
				"ON `assignment`.",MySqlRouteDAO.ROUTE_ID,"=","`route`.",MySqlRouteDAO.ROUTE_ID,";"
				);
		ParsingWork<List<AssignmentJournalFull>> parsingWork = (rs)->{
			List<AssignmentJournalFull> result = new ArrayList<>();
			while(rs.next()) {
				result.add(parseAssignmentJournalFull(rs));
			}
			return result;
		};
		return executeRetrievement(sql, parsingWork);
	}

}
