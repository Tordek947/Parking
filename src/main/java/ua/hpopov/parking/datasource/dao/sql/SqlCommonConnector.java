package ua.hpopov.parking.datasource.dao.sql;

import java.sql.Connection;

public class SqlCommonConnector implements SqlConnector {

	private Connection connection;
	
	public SqlCommonConnector(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void free() {
		connection = null;
	}

	@Override
	public Connection getConnection() {
		return connection;
	}

}
