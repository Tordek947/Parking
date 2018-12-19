package ua.hpopov.parking.datasource.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.hpopov.parking.datasource.DataSourceManager;

public class TransactionConnector implements Connector {
	
private static Logger log = LoggerFactory.getLogger(TransactionConnector.class);
	
	private Connection connection;
	
	public TransactionConnector() {
		try {
			connection = DataSourceManager.getDataSource().getConnection();
		} catch (SQLException e) {
			log.error("Error on getting connection", e);
		}
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			log.error("Error on setting autoCommit false", e);
		}
	}

	@Override
	public Connection getConnection() {
		return connection;
	}

	public void rollBack() {
		try {
			connection.rollback();
		} catch (SQLException e) {
			log.error("Error on rolling transaction back", e);
		}
	}
	
	public void commit() {
		try {
			connection.commit();
		} catch (SQLException e) {
			log.error("Error on committing transaction", e);
		}
	}

	public void free() {
		try {
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			log.error("Error on setting autoCommit true", e);
		}
		connection = null;
	}
}
