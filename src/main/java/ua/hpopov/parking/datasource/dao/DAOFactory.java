package ua.hpopov.parking.datasource.dao;

public interface DAOFactory {
	AssignmentDAO assignmentDAO();
	BusDAO busDAO();
	DriverDAO driverDAO();
	LoginInfoDAO loginInfoDAO();
	RouteDAO routeDAO();
	RouteVertexDAO routeVertexDAO();
	UserDAO userDAO();
	UserTypeDAO userTypeDAO();	
}
