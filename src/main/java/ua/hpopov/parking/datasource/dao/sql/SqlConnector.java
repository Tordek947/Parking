package ua.hpopov.parking.datasource.dao.sql;

import java.sql.Connection;

import ua.hpopov.parking.datasource.dao.Connector;

public interface SqlConnector extends Connector {
	Connection getConnection();
}
