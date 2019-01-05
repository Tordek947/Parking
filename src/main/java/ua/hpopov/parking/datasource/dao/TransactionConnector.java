package ua.hpopov.parking.datasource.dao;

public interface TransactionConnector extends Connector {
	void rollBack();
	void commit();
}
