package ua.hpopov.parking.services;

import javax.sql.DataSource;

import ua.hpopov.parking.datasource.DataSourceManager;

public abstract class AbstractService {

	protected DataSource dataSource;
	
	public AbstractService() {
		dataSource = DataSourceManager.getDataSource();
	}
}
