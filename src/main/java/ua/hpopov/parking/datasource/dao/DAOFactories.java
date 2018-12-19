package ua.hpopov.parking.datasource.dao;

import ua.hpopov.parking.datasource.dao.mysql.MySqlDAOFactory;

public class DAOFactories {
	public static DAOFactory get() {
		return MySqlDAOFactory.getInstance();
	}
}
