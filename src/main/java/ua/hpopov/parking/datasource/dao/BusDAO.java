package ua.hpopov.parking.datasource.dao;

import java.util.List;

import ua.hpopov.parking.beans.BusBean;

public interface BusDAO extends DAO {
	void createBus(BusDAO bus) throws DAOOperationException;
	BusBean getBusById(int busId) throws DAOOperationException;
	UpdateResult updateBusById(BusBean bus) throws DAOOperationException;
	void deleteBusById(int busId) throws DAOOperationException;
	List<BusBean> getAllBuses() throws DAOOperationException;
	List<BusBean> getAllFreeBuses() throws DAOOperationException;
	List<BusBean> getAllBusyBuses() throws DAOOperationException;
	
}
