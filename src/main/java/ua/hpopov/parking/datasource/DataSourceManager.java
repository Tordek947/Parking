package ua.hpopov.parking.datasource;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

public class DataSourceManager {
	private static DataSource dataSourceInstance=null;
	
	public static DataSource getDataSource() {
		if (dataSourceInstance == null) {
			dataSourceInstance = loadDataSource();
		}
		return dataSourceInstance;
	}
	
	private static DataSource loadDataSource() {
		return buildDataSource(getConnectURI(), getConnectionProperties());
	}

	private static String getConnectURI() {
		return "jdbc:mysql://127.0.0.1:3306";
	}

	private static Properties getConnectionProperties() {
    	Properties properties = new Properties();
    	properties.setProperty("user", "root");
    	properties.setProperty("password", "Root");
    	properties.setProperty("useSSL", "FALSE");
    	return properties;
	}

	public static DataSource buildDataSource(String connectURI, Properties properties) {
		ConnectionFactory connectionFactory =
				new DriverManagerConnectionFactory(connectURI, properties);
		PoolableConnectionFactory poolableConnectionFactory =
				new PoolableConnectionFactory(connectionFactory, null);
		
		ObjectPool<PoolableConnection> connectionPool =
				new GenericObjectPool<>(poolableConnectionFactory);
		poolableConnectionFactory.setPool(connectionPool);
		PoolingDataSource<PoolableConnection> dataSource =
				new PoolingDataSource<>(connectionPool);
		
		return dataSource;
	}
}
