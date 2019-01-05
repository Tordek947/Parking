package ua.hpopov.parking.datasource.dao.sql.mysql;

import java.util.List;

import ua.hpopov.parking.beans.BusBean;
import ua.hpopov.parking.datasource.dao.BusDAO;
import ua.hpopov.parking.datasource.dao.DAOOperationException;
import ua.hpopov.parking.datasource.dao.UpdateResult;

public class MySqlBusDAO extends MySqlAbstractDAO implements BusDAO {

	static final String FULL_TABLE_NAME, BUS_ID, BUS_SERIAL, BUS_MODEL;
	static {
		FULL_TABLE_NAME = "`parking`.`bus`";
		BUS_ID = "`bus_id`";
		BUS_SERIAL = "`bus_serial`";
		BUS_MODEL = "`bus_model`";
	}
	@Override
	public void createBus(BusDAO bus) throws DAOOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public BusBean getBusById(int busId) throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusBean> getAllBusesPage(int fromKeyInd, int limit) throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusBean> getAllFreeBusesPage(int fromKeyInd, int limit) throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusBean> getAllBusyBusesPage(int fromKeyInd, int limit) throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateResult updateBusById(BusBean bus) throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBusById(int busId) throws DAOOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public void markBusByIdAsDeleted(int busId) throws DAOOperationException {
		// TODO Auto-generated method stub

	}

}
