package ua.hpopov.parking.datasource.dao;

import ua.hpopov.parking.beans.UserTypeBean;

public interface UserTypeDAO extends DAO {
	UserTypeBean getUserTypeByUserId(int userId) throws DAOOperationException;
}
