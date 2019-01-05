package ua.hpopov.parking.datasource.dao.sql;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SqlConnectorProvider {

	private DataSource dataSource;
	private static Logger log = LoggerFactory.getLogger(SqlConnectorProvider.class);
	
	public SqlConnectorProvider() {
		this.dataSource = makeDataSource();
	}
	
	protected abstract DataSource makeDataSource();
	
	public SqlCommonConnector makeCommonConnector() {
		return new SqlCommonConnector(getConnection());
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			log.error("SQLException on getting connection from datasource", e);
		}
		return conn;
	}
	
	public SqlTransactionConnector makeTransactionConnector() {
		return new SqlTransactionConnector(getConnection());
	}
}
