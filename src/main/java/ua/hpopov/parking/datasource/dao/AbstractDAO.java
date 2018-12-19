package ua.hpopov.parking.datasource.dao;

public abstract class AbstractDAO implements DAO {
	protected Connector connector = null;
	
	@Override
	public void setConnector(Connector connector) {
		this.connector = connector;
	}
}
