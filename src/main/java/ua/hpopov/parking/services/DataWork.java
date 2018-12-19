package ua.hpopov.parking.services;

import ua.hpopov.parking.datasource.dao.DAOOperationException;

@FunctionalInterface
public interface DataWork {
	void execute() throws DAOOperationException;
}
