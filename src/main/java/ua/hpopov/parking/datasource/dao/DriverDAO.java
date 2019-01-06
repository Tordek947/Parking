package ua.hpopov.parking.datasource.dao;

import java.util.List;

import ua.hpopov.parking.beans.DriverBean;
import ua.hpopov.parking.beans.DriverFull;

public interface DriverDAO extends DAO {
	void createDriver(DriverBean driver) throws DAOOperationException;
	DriverBean getDriverByUserId(int userId) throws DAOOperationException;
	DriverFull getDriverFullByDriverId(int driverId) throws DAOOperationException;
	Integer getAllDriversCount() throws DAOOperationException;
	List<DriverBean> getAllDriversPage(int fromKeyInd, int limit) throws DAOOperationException;
	Integer getAllFreeDriversCount() throws DAOOperationException;
	List<DriverBean> getAllFreeDriversPage(int fromKeyInd, int limit) throws DAOOperationException;
	Integer getAllBusyDriversCount() throws DAOOperationException;
	List<DriverBean> getAllBusyDriversPage(int fromKeyInd, int limit) throws DAOOperationException;
	
}
