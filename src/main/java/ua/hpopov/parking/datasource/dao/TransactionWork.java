package ua.hpopov.parking.datasource.dao;

@FunctionalInterface
public interface TransactionWork {
	void execute() throws DAOOperationException;
}
