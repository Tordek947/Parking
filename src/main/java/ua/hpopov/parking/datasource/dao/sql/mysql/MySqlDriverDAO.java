package ua.hpopov.parking.datasource.dao.sql.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.hpopov.parking.beans.DriverBean;
import ua.hpopov.parking.beans.DriverFull;
import ua.hpopov.parking.beans.UserBean;
import ua.hpopov.parking.datasource.Strings;
import ua.hpopov.parking.datasource.dao.DAOOperationException;
import ua.hpopov.parking.datasource.dao.DriverDAO;
import ua.hpopov.parking.datasource.dao.ParsingWork;

public class MySqlDriverDAO extends MySqlAbstractDAO implements DriverDAO {

	static final String FULL_TABLE_NAME, DRIVER_ID, USER_ID;
	static {
		FULL_TABLE_NAME = "`parking`.`driver`";
		USER_ID = "user_id";
		DRIVER_ID = "driver_id";
	}
	
	@Override
	public void createDriver(DriverBean driver) throws DAOOperationException {
		String sql = Strings.concat(
				"INSERT INTO ",FULL_TABLE_NAME," SET\r\n",
				USER_ID,"="+driver.getUserId(),";"
				);
		executeCreateOperation(sql);
	}

	@Override
	public DriverBean getDriverByUserId(int userId) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT * FROM ",FULL_TABLE_NAME,"\r\n",
				"WHERE ",USER_ID,"="+userId,";"
				);
		ParsingWork<DriverBean> parsingWork = (rs)->{
			if (rs.next()) {
				return parseDriverBean(rs);
			}
			return null;
		};
		return executeRetrievement(sql, parsingWork);
	}

	private DriverBean parseDriverBean(ResultSet rs) throws SQLException {
		DriverBean result = new DriverBean();
		result.setDriverId(rs.getInt(DRIVER_ID));
		result.setUserId(rs.getInt(USER_ID));
		return result;
	}

	@Override
	public List<DriverBean> getAllDriversPage(int fromKeyInd, int limit) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT * FROM ",FULL_TABLE_NAME,"\r\n",
				"WHERE ",DRIVER_ID,">="+fromKeyInd,"\r\n",
				"LIMIT "+limit,";"
				);
		ParsingWork<List<DriverBean>> parsingWork = (rs)->{
			List<DriverBean> result = new ArrayList<>();
			while (rs.next()) {
				result.add(parseDriverBean(rs));
			}
			return result;
		};
		return executeRetrievement(sql, parsingWork);
	}

	@Override
	public List<DriverBean> getAllFreeDriversPage(int fromKeyInd, int limit) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT * FROM ",FULL_TABLE_NAME," AS `driver`\r\n",
				"LEFT OUTER JOIN ",MySqlAssignmentDAO.FULL_TABLE_NAME," AS `assignment`\r\n",
				"ON `driver`.",DRIVER_ID,"=","`assignment`.",MySqlAssignmentDAO.DRIVER_ID,"\r\n",
				"AND `driver`.",DRIVER_ID,">="+fromKeyInd,"\r\n",
				"WHERE `assignment`.",MySqlAssignmentDAO.DRIVER_ID," IS NULL\r\n",			
				"LIMIT "+limit,";"
				);
		ParsingWork<List<DriverBean>> parsingWork = (rs)->{
			List<DriverBean> result = new ArrayList<>();
			while (rs.next()) {
				result.add(parseDriverBean(rs));
			}
			return result;
		};
		return executeRetrievement(sql, parsingWork);
	}

	@Override
	public List<DriverBean> getAllBusyDriversPage(int fromKeyInd, int limit) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT * FROM ",FULL_TABLE_NAME," AS `driver`\r\n",
				"LEFT OUTER JOIN ",MySqlAssignmentDAO.FULL_TABLE_NAME," AS `assignment`\r\n",
				"ON `driver`.",DRIVER_ID,"=","`assignment`.",MySqlAssignmentDAO.DRIVER_ID,"\r\n",
				"AND `driver`.",DRIVER_ID,">="+fromKeyInd,"\r\n",
				"WHERE `assignment`.",MySqlAssignmentDAO.DRIVER_ID," IS NOT NULL\r\n",			
				"LIMIT "+limit,";"
				);
		ParsingWork<List<DriverBean>> parsingWork = (rs)->{
			List<DriverBean> result = new ArrayList<>();
			while (rs.next()) {
				result.add(parseDriverBean(rs));
			}
			return result;
		};
		return executeRetrievement(sql, parsingWork);
	}

	@Override
	public Integer getAllDriversCount() throws DAOOperationException {
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
	public Integer getAllFreeDriversCount() throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT COUNT(*) FROM\r\n",
				FULL_TABLE_NAME," AS `driver`\r\n",
				"LEFT OUTER JOIN ",MySqlAssignmentDAO.FULL_TABLE_NAME," AS `assignment`\r\n",
				"ON `driver`.",DRIVER_ID,"=","`assignment`.",MySqlAssignmentDAO.DRIVER_ID,"\r\n",
				"WHERE `assignment`.",MySqlAssignmentDAO.DRIVER_ID," IS NULL;"	
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
	public Integer getAllBusyDriversCount() throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT COUNT(*) FROM\r\n",
				FULL_TABLE_NAME," AS `driver`\r\n",
				"LEFT OUTER JOIN ",MySqlAssignmentDAO.FULL_TABLE_NAME," AS `assignment`\r\n",
				"ON `driver`.",DRIVER_ID,"=","`assignment`.",MySqlAssignmentDAO.DRIVER_ID,"\r\n",
				"WHERE `assignment`.",MySqlAssignmentDAO.DRIVER_ID," IS NOT NULL;"	
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
	public DriverFull getDriverFullByDriverId(int driverId) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT * FROM (\r\n",
				"SELECT * FROM ",FULL_TABLE_NAME,"\r\n",
				"WHERE ",DRIVER_ID,"="+driverId,"\r\n",
				") AS `driver`\r\n",
				"INNER JOIN ",MySqlUserDAO.FULL_TABLE_NAME," AS `user`\r\n",
				"ON `driver`.",USER_ID,"=","`user`.",MySqlUserDAO.USER_ID,";"
				);
		ParsingWork<DriverFull> parsingWork = (rs)->{
			if (rs.next()) {
				return parseDriverFull(rs);
			}
			return null;
		};
		return executeRetrievement(sql, parsingWork);
	}

	private DriverFull parseDriverFull(ResultSet rs) throws SQLException {
		DriverFull result = new DriverFull();
		result.setDriverId(rs.getInt(DRIVER_ID));
		result.setUserBean(parseUserBean(rs));
		return result;
	}

	private UserBean parseUserBean(ResultSet rs) throws SQLException {
		UserBean result = new UserBean();
		result.setName(rs.getString(MySqlUserDAO.NAME));
		result.setSurname(rs.getString(MySqlUserDAO.SURNAME));
		result.setUserId(rs.getInt(MySqlUserDAO.USER_ID));
		result.setUserTypeId(rs.getInt(MySqlUserDAO.USER_TYPE_ID));
		return result;
	}
	

}
