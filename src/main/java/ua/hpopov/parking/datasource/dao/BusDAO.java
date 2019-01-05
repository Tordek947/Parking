package ua.hpopov.parking.datasource.dao;

import java.util.List;

import ua.hpopov.parking.beans.BusBean;

public interface BusDAO extends DAO {
	void createBus(BusDAO bus) throws DAOOperationException;
	BusBean getBusById(int busId) throws DAOOperationException;
	Integer getAllBusesCount() throws DAOOperationException;
	List<BusBean> getAllBusesPage(int fromKeyInd, int limit) throws DAOOperationException;
	Integer getAllFreeBusesCount() throws DAOOperationException;
	List<BusBean> getAllFreeBusesPage(int fromKeyInd, int limit) throws DAOOperationException;
	Integer getAllBusyBusesCount() throws DAOOperationException;
	List<BusBean> getAllBusyBusesPage(int fromKeyInd, int limit) throws DAOOperationException;
	UpdateResult updateBusById(BusBean bus) throws DAOOperationException;
	void deleteBusById(int busId) throws DAOOperationException;
	void markBusByIdAsDeleted(int busId) throws DAOOperationException;
}
