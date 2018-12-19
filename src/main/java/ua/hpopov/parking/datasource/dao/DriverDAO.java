package ua.hpopov.parking.datasource.dao;

import java.util.List;

import ua.hpopov.parking.beans.DriverBean;

public interface DriverDAO extends DAO {
	void createDriver(DriverBean driver) throws DAOOperationException;
	DriverBean getDriverByUserId(int userId) throws DAOOperationException;
	List<DriverBean> getAllDrivers() throws DAOOperationException;
	List<DriverBean> getAllFreeDrivers() throws DAOOperationException;
	List<DriverBean> getAllBusyDrivers() throws DAOOperationException;
	
}
