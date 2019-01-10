package ua.hpopov.parking.datasource.dao.sql;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlCommonConnector implements SqlConnector {

	private static final Logger log = LoggerFactory.getLogger(SqlCommonConnector.class);
	
	private Connection connection;
	
	public SqlCommonConnector(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void free() {
		try {
			connection.close();
		} catch (SQLException e) {
			log.error("Error on closing SQL connection");
		}
		connection = null;
	}

	@Override
	public Connection getConnection() {
		return connection;
	}

	@Override
	public boolean isNeededToFreeByDAO() {
		return true;
	}

}
