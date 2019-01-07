package ua.hpopov.parking.datasource.dao.sql.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.hpopov.parking.beans.BusBean;
import ua.hpopov.parking.datasource.Strings;
import ua.hpopov.parking.datasource.dao.BusDAO;
import ua.hpopov.parking.datasource.dao.DAOOperationException;
import ua.hpopov.parking.datasource.dao.ParsingWork;
import ua.hpopov.parking.datasource.dao.UpdateResult;

public class MySqlBusDAO extends MySqlAbstractDAO implements BusDAO {

	static final String FULL_TABLE_NAME, BUS_ID, BUS_SERIAL, BUS_MODEL;
	static {
		FULL_TABLE_NAME = "`parking`.`bus`";
		BUS_ID = "bus_id";
		BUS_SERIAL = "bus_serial";
		BUS_MODEL = "bus_model";
	}
	@Override
	public void createBus(BusBean bus) throws DAOOperationException {
		String sql = Strings.concat(
				"INSERT INTO ",FULL_TABLE_NAME," SET\r\n",
				BUS_SERIAL,"="+bus.getBusSerial(),",\r\n",
				BUS_MODEL,"='"+bus.getBusModel(),"';"
				);
		executeCreateOperation(sql);
		
	}

	@Override
	public BusBean getBusById(int busId) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT * FROM ",FULL_TABLE_NAME,"\r\n",
				"WHERE ",BUS_ID,"="+busId,";"
				);
		ParsingWork<BusBean> parsingWork = (rs)->{
			if (rs.next()) {
				return parseBusBean(rs);
			}
			return null;
		};
		return executeRetrievement(sql, parsingWork);
	}

	private BusBean parseBusBean(ResultSet rs) throws SQLException {
		BusBean result = new BusBean();
		result.setBusId(rs.getInt(BUS_ID));
		result.setBusModel(rs.getString(BUS_MODEL));
		result.setBusSerial(rs.getInt(BUS_SERIAL));
		return result;
	}

	@Override
	public List<BusBean> getAllBusesPage(int fromKeyInd, int limit) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT * FROM ",FULL_TABLE_NAME,"\r\n",
				"WHERE ",BUS_ID,">="+fromKeyInd,"\r\n",
				"LIMIT "+limit,";"
				);
		ParsingWork<List<BusBean>> parsingWork = (rs)->{
			List<BusBean> result = new ArrayList<>();
			while (rs.next()) {
				result.add(parseBusBean(rs));
			}
			return result;
		};
		return executeRetrievement(sql, parsingWork);
	}

	@Override
	public List<BusBean> getAllFreeBusesPage(int fromKeyInd, int limit) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT * FROM ",FULL_TABLE_NAME," AS `bus`\r\n",
				"LEFT OUTER JOIN ",MySqlAssignmentDAO.FULL_TABLE_NAME," AS `assignment`\r\n",
				"ON `bus`.",BUS_ID,"=","`assignment`.",MySqlAssignmentDAO.BUS_ID,"\r\n",
				"AND `bus`.",BUS_ID,">="+fromKeyInd,"\r\n",
				"WHERE `assignment`.",MySqlAssignmentDAO.BUS_ID," IS NULL\r\n",			
				"LIMIT "+limit,";"
				);
		ParsingWork<List<BusBean>> parsingWork = (rs)->{
			List<BusBean> result = new ArrayList<>();
			while (rs.next()) {
				result.add(parseBusBean(rs));
			}
			return result;
		};
		return executeRetrievement(sql, parsingWork);
	}

	@Override
	public List<BusBean> getAllBusyBusesPage(int fromKeyInd, int limit) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT * FROM ",FULL_TABLE_NAME," AS `bus`\r\n",
				"LEFT OUTER JOIN ",MySqlAssignmentDAO.FULL_TABLE_NAME," AS `assignment`\r\n",
				"ON `bus`.",BUS_ID,"=","`assignment`.",MySqlAssignmentDAO.BUS_ID,"\r\n",
				"AND `bus`.",BUS_ID,">="+fromKeyInd,"\r\n",
				"WHERE `assignment`.",MySqlAssignmentDAO.BUS_ID," IS NOT NULL\r\n",			
				"LIMIT "+limit,";"
				);
		ParsingWork<List<BusBean>> parsingWork = (rs)->{
			List<BusBean> result = new ArrayList<>();
			while (rs.next()) {
				result.add(parseBusBean(rs));
			}
			return result;
		};
		return executeRetrievement(sql, parsingWork);
	}

	@Override
	public UpdateResult updateBusById(BusBean bus) throws DAOOperationException {
		String sql = Strings.concat(
				"UPDATE ",FULL_TABLE_NAME," SET\r\n",
				BUS_SERIAL,"="+bus.getBusSerial(),",\r\n",
				BUS_MODEL,"='",bus.getBusModel(),"',\r\n",
				"WHERE ",BUS_ID,"="+bus.getBusId(),";"
				);
		return executeUpdateOperation(sql);
	}

	@Override
	public UpdateResult deleteBusById(int busId) throws DAOOperationException {
		String sql = Strings.concat(
				"DELETE FROM ",FULL_TABLE_NAME," WHERE\r\n",
				BUS_ID,"="+busId,";"
				);
		return executeUpdateOperation(sql);
	}

	@Override
	public UpdateResult markBusByIdAsDeleted(int busId) throws DAOOperationException {
		String sql = Strings.concat(
				"UPDATE ",FULL_TABLE_NAME,"\r\n",
				"SET ",BUS_ID,"="+(busId>0?(-busId):busId),"\r\n",
				"WHERE ",BUS_ID,"="+busId,";"
				);
		return executeUpdateOperation(sql);
	}

	@Override
	public Integer getAllBusesCount() throws DAOOperationException {
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
	public Integer getAllFreeBusesCount() throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT COUNT(*) FROM\r\n",
				FULL_TABLE_NAME," AS `bus`\r\n",
				"LEFT OUTER JOIN ",MySqlAssignmentDAO.FULL_TABLE_NAME," AS `assignment`\r\n",
				"ON `bus`.",BUS_ID,"=","`assignment`.",MySqlAssignmentDAO.BUS_ID,"\r\n",
				"WHERE `assignment`.",MySqlAssignmentDAO.BUS_ID," IS NULL;"	
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
	public Integer getAllBusyBusesCount() throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT COUNT(*) FROM\r\n",
				FULL_TABLE_NAME," AS `bus`\r\n",
				"LEFT OUTER JOIN ",MySqlAssignmentDAO.FULL_TABLE_NAME," AS `assignment`\r\n",
				"ON `bus`.",BUS_ID,"=","`assignment`.",MySqlAssignmentDAO.BUS_ID,"\r\n",
				"WHERE `assignment`.",MySqlAssignmentDAO.BUS_ID," IS NOT NULL;"	
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
