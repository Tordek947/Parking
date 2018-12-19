package ua.hpopov.parking.datasource.dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.hpopov.parking.datasource.DataSourceManager;
import ua.hpopov.parking.datasource.dao.AbstractDAO;
import ua.hpopov.parking.datasource.dao.DAOOperationException;
import ua.hpopov.parking.datasource.dao.ParsingWork;
import ua.hpopov.parking.datasource.dao.UpdateResult;

public class MySqlAbstractDAO extends AbstractDAO {


	private static final Logger log = LoggerFactory.getLogger(AbstractDAO.class);
	
	
	protected <T> T executeRetrievement(String selectQuery, ParsingWork<T> parsingWork)
			throws DAOOperationException {
		Connection connection = getConnection();
		if (connection == null) {
			throw new DAOOperationException();
		}
		Statement statement = createStatement(connection);
		if (statement == null) {
			throw new DAOOperationException();
		}
		ResultSet resultSet;
		T parsingResult;
		resultSet = executeQuery(statement, selectQuery);
		parsingResult = executeParsing(parsingWork, resultSet);
		closeResultSet(resultSet);
		closeStatement(statement);		
		return parsingResult;
	}
	
	protected UpdateResult executeUpdateOperation(String query)
			throws DAOOperationException {
		Connection connection = getConnection();
		if (connection == null) {
			throw new DAOOperationException();
		}
		Statement statement = createStatement(connection);
		if (statement == null) {
			throw new DAOOperationException();
		}
		UpdateResult updateResult;
		updateResult = executeUpdateQuery(statement, query);
		closeStatement(statement);
		if (updateResult == UpdateResult.ERROR) {
			throw new DAOOperationException();
		}
		return updateResult;
	}
	
	protected Integer executeUpdateOperationRetrievingKey(String query)
			throws DAOOperationException {
		Connection connection = getConnection();
		if (connection == null) {
			throw new DAOOperationException();
		}
		Statement statement = createStatement(connection);
		if (statement == null) {
			throw new DAOOperationException();
		}
		Integer key = executeUpdateQueryRetrievingKey(statement, query);
		closeStatement(statement);
		if (key == null) {
			throw new DAOOperationException();
		}
		return key;
	}
	
	protected Long executeUpdateOperationRetrievingLongKey(String query)
			throws DAOOperationException {
		Connection connection = getConnection();
		if (connection == null) {
			throw new DAOOperationException();
		}
		Statement statement = createStatement(connection);
		if (statement == null) {
			throw new DAOOperationException();
		}
		Long key = executeUpdateQueryRetrievingLongKey(statement, query);
		closeStatement(statement);
		if (key == null) {
			throw new DAOOperationException();
		}
		return key;
	}
	
	protected void executeCreateOperation(String query) throws DAOOperationException {
		Connection connection = getConnection();
		if (connection == null) {
			 throw new DAOOperationException();
		}
		Statement statement = createStatement(connection);
		if (statement == null) {
			throw new DAOOperationException();
		}
		boolean successful = execute(statement, query);
		closeStatement(statement);
		if (!successful) {
			throw new DAOOperationException();
		}
	}
	
	
	protected Connection getConnection() {
		if (connector != null) {
			return connector.getConnection();
		}
		try {
			return DataSourceManager.getDataSource().getConnection();
		} catch (SQLException e) {
			log.error("SQLException on getting connection from dbcp",e);
			return null;
		}
	}
	
	private Statement createStatement(Connection conn) {
		Statement st=null;
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			log.error("SQLException on creating statement", e);
		} 
		return st;		
	}
	
	protected Statement prepareStatement(Connection conn, String sql) {
		Statement st=null;
		try {
			st = conn.prepareStatement(sql);
		} catch (SQLException e) {
			log.error("SQLException on preparing statement", e);
		}
		return st;		
	}
	
	private ResultSet executeQuery(Statement st, String sql) {
		ResultSet rs=null;
		try {
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			log.error("SQLException on executing query",e);
		}
		return rs;
	}
	
	private UpdateResult executeUpdateQuery(Statement st, String sql) {
		int updatedRowsCount = 0;
		try {
			updatedRowsCount = st.executeUpdate(sql);
			if (updatedRowsCount > 0) {
				return UpdateResult.SUCCESSFULLY;
			}
			return UpdateResult.NO_CHANGES_SUCCESSFULLY;
		} catch (SQLException e) {
			log.error("SQLException on executing update query",e);
			return UpdateResult.ERROR;
		}
	}
	
	private Integer executeUpdateQueryRetrievingKey(Statement st, String sql) {
		Integer key;
		try {
			key = st.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			return key;
		} catch (SQLException e) {
			log.error("SQLException on executing update query retrieving autogenerated key",e);
			return null;
		}
	}
	
	private Long executeUpdateQueryRetrievingLongKey(Statement st, String sql) {
		Long key;
		try {
			key = st.executeLargeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			return key;
		} catch (SQLException e) {
			log.error("SQLException on executing update query retrieving autogenerated key",e);
			return null;
		}
	}
	
	private boolean execute(Statement st, String sql) {
		try {
			st.execute(sql);
			return true;
		} catch (SQLException e) {
			log.error("SQLException on executing query",e);
			return false;
		}
	}
	
	
	private <T> T executeParsing(ParsingWork<T> work, ResultSet rs) throws DAOOperationException{
		T result = null;
		try {
			result = work.execute(rs);
		} catch (SQLException e) {
			log.error("SQLException during resultSet parsing",e);
		}
		return result;
	}
	
	private void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			log.error("SQLException during resultSet closing",e);
		}
	}
	
	private void closeStatement(Statement st) {
		try {
			st.close();
		} catch (SQLException e) {
			log.error("SQLException during statement closing",e);
		}
	}
}
