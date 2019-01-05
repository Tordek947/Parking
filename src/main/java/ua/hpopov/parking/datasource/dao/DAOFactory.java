package ua.hpopov.parking.datasource.dao;

public interface DAOFactory {
	AssignmentDAO createAssignmentDAO();
	BusDAO createBusDAO();
	DriverDAO createDriverDAO();
	LoginInfoDAO createLoginInfoDAO();
	RouteDAO createRouteDAO();
	RouteVertexDAO createRouteVertexDAO();
	UserDAO createUserDAO();
	UserTypeDAO createUserTypeDAO();
	AssignmentJournalDAO createAssignmentJournalDAO();
	Transaction createTransaction(TransactionWork transactionWork);
}
