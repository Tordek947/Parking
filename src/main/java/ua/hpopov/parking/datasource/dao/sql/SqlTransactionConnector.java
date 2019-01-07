package ua.hpopov.parking.datasource.dao.sql;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.hpopov.parking.datasource.dao.TransactionConnector;

public class SqlTransactionConnector implements SqlConnector, TransactionConnector {
	
private static Logger log = LoggerFactory.getLogger(SqlTransactionConnector.class);
	
	private Connection connection;
	
	public SqlTransactionConnector(Connection connection) {
		
		this.connection = connection;
		
		try {
			this.connection.setAutoCommit(false);
		} catch (SQLException e) {
			log.error("Error on setting autoCommit false", e);
		}
	}

	@Override
	public Connection getConnection() {
		return connection;
	}

	@Override
	public void rollBack() {
		try {
			connection.rollback();
		} catch (SQLException e) {
			log.error("Error on rolling transaction back", e);
		}
	}
	
	@Override
	public void commit() {
		try {
			connection.commit();
		} catch (SQLException e) {
			log.error("Error on committing transaction", e);
		}
	}

	@Override
	public void free() {
		try {
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			log.error("Error on setting autoCommit true", e);
		}
		connection = null;
	}
}
