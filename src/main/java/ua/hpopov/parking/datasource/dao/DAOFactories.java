package ua.hpopov.parking.datasource.dao;

import ua.hpopov.parking.datasource.dao.sql.mysql.MySqlDAOFactory;

public class DAOFactories {
	public static DAOFactory getFactory() {
		return MySqlDAOFactory.getInstance();
	}
}
