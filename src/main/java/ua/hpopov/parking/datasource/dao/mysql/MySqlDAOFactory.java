package ua.hpopov.parking.datasource.dao.mysql;

import ua.hpopov.parking.datasource.dao.AssignmentDAO;
import ua.hpopov.parking.datasource.dao.BusDAO;
import ua.hpopov.parking.datasource.dao.DAOFactory;
import ua.hpopov.parking.datasource.dao.DriverDAO;
import ua.hpopov.parking.datasource.dao.LoginInfoDAO;
import ua.hpopov.parking.datasource.dao.RouteDAO;
import ua.hpopov.parking.datasource.dao.RouteVertexDAO;
import ua.hpopov.parking.datasource.dao.UserDAO;
import ua.hpopov.parking.datasource.dao.UserTypeDAO;

public class MySqlDAOFactory implements DAOFactory {

	private static MySqlDAOFactory instance = null;
	private MySqlDAOFactory() {}
	
	public static MySqlDAOFactory getInstance() {
		if (instance == null) {
			instance = new MySqlDAOFactory();
		}
		return instance;
	}
	
	@Override
	public AssignmentDAO assignmentDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BusDAO busDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DriverDAO driverDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginInfoDAO loginInfoDAO() {
		return new MySqlLoginInfoDAO();
	}

	@Override
	public RouteDAO routeDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RouteVertexDAO routeVertexDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDAO userDAO() {
		return new MySqlUserDAO();
	}

	@Override
	public UserTypeDAO userTypeDAO() {
		return new MySqlUserTypeDAO();
	}

}
