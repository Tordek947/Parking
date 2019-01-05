package ua.hpopov.parking.datasource.dao.sql.mysql;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.hpopov.parking.datasource.dao.sql.SqlConnectorProvider;

public final class MySqlConnectorProvider extends SqlConnectorProvider {
	
	private static Logger log = LoggerFactory.getLogger(MySqlConnectorProvider.class);
	private static MySqlConnectorProvider instance=null;

	private MySqlConnectorProvider() {
		super();
	}
	
	public static MySqlConnectorProvider getInstance() {
		if (instance == null) {
			instance = new MySqlConnectorProvider();
		}
		return instance;
	}

	@Override
	protected DataSource makeDataSource() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			log.error("Error making mySql DataSource",e);
		}
		return buildDataSource(getConnectURI(), getConnectionProperties());
	}

	private String getConnectURI() {
		return "jdbc:mysql://127.0.0.1:3306";
	}

	private Properties getConnectionProperties() {
    	Properties properties = new Properties();
    	properties.setProperty("user", "root");
    	properties.setProperty("password", "Root");
    	properties.setProperty("useSSL", "FALSE");
    	properties.setProperty("serverTimezone", "UTC");
    	return properties;
	}

	public DataSource buildDataSource(String connectURI, Properties properties) {
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
