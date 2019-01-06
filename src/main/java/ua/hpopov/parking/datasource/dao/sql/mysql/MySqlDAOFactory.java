package ua.hpopov.parking.datasource.dao.sql.mysql;

import ua.hpopov.parking.datasource.dao.AssignmentDAO;
import ua.hpopov.parking.datasource.dao.AssignmentJournalDAO;
import ua.hpopov.parking.datasource.dao.BusDAO;
import ua.hpopov.parking.datasource.dao.DAOFactory;
import ua.hpopov.parking.datasource.dao.DriverDAO;
import ua.hpopov.parking.datasource.dao.LoginInfoDAO;
import ua.hpopov.parking.datasource.dao.RouteDAO;
import ua.hpopov.parking.datasource.dao.RouteVertexDAO;
import ua.hpopov.parking.datasource.dao.Transaction;
import ua.hpopov.parking.datasource.dao.TransactionWork;
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
	public AssignmentDAO createAssignmentDAO() {
		return new MySqlAssignmentDAO();
	}

	@Override
	public BusDAO createBusDAO() {
		return new MySqlBusDAO();
	}

	@Override
	public DriverDAO createDriverDAO() {
		return new MySqlDriverDAO();
	}

	@Override
	public LoginInfoDAO createLoginInfoDAO() {
		return new MySqlLoginInfoDAO();
	}

	@Override
	public RouteDAO createRouteDAO() {
		return new MySqlRouteDAO();
	}

	@Override
	public RouteVertexDAO createRouteVertexDAO() {
		return new MySqlRouteVertexDAO();
	}

	@Override
	public UserDAO createUserDAO() {
		return new MySqlUserDAO();
	}

	@Override
	public UserTypeDAO createUserTypeDAO() {
		return new MySqlUserTypeDAO();
	}

	@Override
	public Transaction createTransaction(TransactionWork transactionWork) {
		return new MySqlTransaction(transactionWork);
	}

	@Override
	public AssignmentJournalDAO createAssignmentJournalDAO() {
		return new MySqlAssignmentJournalDAO();
	}

}
