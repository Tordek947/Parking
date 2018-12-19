package ua.hpopov.parking.datasource.dao.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ua.hpopov.parking.beans.UserBean;
import ua.hpopov.parking.beans.UserTypeBean;
import ua.hpopov.parking.datasource.dao.DAOOperationException;
import ua.hpopov.parking.datasource.dao.ParsingWork;
import ua.hpopov.parking.datasource.dao.UpdateResult;
import ua.hpopov.parking.datasource.dao.UserDAO;

public class MySqlUserDAO extends MySqlAbstractDAO implements UserDAO {

	@Override
	public void createUser(UserBean user) throws DAOOperationException {
		String name = user.getName();
		String surname = user.getSurname();
		Integer userTypeId = user.getUserTypeId();
		String sql = "INSERT INTO parking.user SET\r\n" + 
				"	`name`='"+name+"',\r\n" + 
				"    `surname`='"+surname+"',\r\n" + 
				"    `user_type_id`='"+userTypeId+"';";
		executeCreateOperation(sql);
	}

	@Override
	public UserBean getUserById(int userId) throws DAOOperationException {
		String sql = "SELECT user.* FROM parking.user AS user\r\n" + 
				"WHERE  user.user_id='"+userId+"'";
		ParsingWork<UserBean> work = (rs)->{
			UserBean userBean = null;
			if (rs.next()) {
				userBean = parseUserBean(rs);
			}
			return userBean;
		};	
		return executeRetrievement(sql, work);
	}

	private UserBean parseUserBean(final ResultSet rs) throws SQLException{
		UserBean result = new UserBean();
		result.setUserId(rs.getInt("user_id"));
		result.setName(rs.getString("name"));
		result.setSurname(rs.getString("surname"));
		result.setUserTypeId(rs.getInt("user_type_id"));
		return result;
	}

	@Override
	public UpdateResult updateUserById(UserBean user) throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserById(int userId) throws DAOOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UserBean> getAllUsers() throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserBean> getAllUsersByUserType(UserTypeBean userType) throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

}
