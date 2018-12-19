package ua.hpopov.parking.datasource;

import java.util.Properties;

public class DataSourceResources {

	private static DataSourceResources instance=null;
	
	private DataSourceResources() {}
	
	public static DataSourceResources getInstance() {
		if (instance == null) {
			instance = new DataSourceResources();
		}
		return instance;
	}
	
//	public Properties getConnectionProperties() {
//		Properties properties = 
//	}
}
