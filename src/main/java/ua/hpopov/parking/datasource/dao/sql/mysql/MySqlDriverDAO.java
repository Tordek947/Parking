package ua.hpopov.parking.datasource.dao.sql.mysql;

import java.util.List;

import ua.hpopov.parking.beans.DriverBean;
import ua.hpopov.parking.datasource.dao.DAOOperationException;
import ua.hpopov.parking.datasource.dao.DriverDAO;

public class MySqlDriverDAO extends MySqlAbstractDAO implements DriverDAO {

	static final String FULL_TABLE_NAME, DRIVER_ID, USER_ID;
	static {
		FULL_TABLE_NAME = "`parking`.`driver`";
		USER_ID = "`user_id`";
		DRIVER_ID = "`driver_id`";
	}
	
	@Override
	public void createDriver(DriverBean driver) throws DAOOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public DriverBean getDriverByUserId(int userId) throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DriverBean> getAllDriversPage(int fromKeyInd, int limit) throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DriverBean> getAllFreeDriversPage(int fromKeyInd, int limit) throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DriverBean> getAllBusyDriversPage(int fromKeyInd, int limit) throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllDriversCount() throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllFreeDriversCount() throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllBusyDriversCount() throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

}
