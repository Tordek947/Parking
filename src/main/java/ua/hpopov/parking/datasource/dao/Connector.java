package ua.hpopov.parking.datasource.dao;

import java.sql.Connection;

public interface Connector {
	Connection getConnection();
}
