package ua.hpopov.parking.datasource.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ParsingWork<T> {
	T execute(ResultSet rs) throws SQLException;
}
