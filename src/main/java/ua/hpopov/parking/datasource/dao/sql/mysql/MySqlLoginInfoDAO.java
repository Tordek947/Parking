package ua.hpopov.parking.datasource.dao.sql.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import ua.hpopov.parking.beans.LoginInfoBean;
import ua.hpopov.parking.datasource.dao.DAOOperationException;
import ua.hpopov.parking.datasource.dao.LoginInfoDAO;
import ua.hpopov.parking.datasource.dao.ParsingWork;
import ua.hpopov.parking.datasource.dao.UpdateResult;

public class MySqlLoginInfoDAO extends MySqlAbstractDAO implements LoginInfoDAO {

	@Override
	public void createLoginInfo(LoginInfoBean loginInfo) throws DAOOperationException {
		// TODO Auto-generated method stub

	}

	@Override
	public LoginInfoBean getLoginInfoByUserId(int userId) throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateResult updateLoginInfoByUserId(LoginInfoBean loginInfo) throws DAOOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginInfoBean getLoginInfoByLoginPassword(String login, String password)
			throws DAOOperationException {
		String sql = "SELECT login_info.* FROM parking.login_info AS login_info\r\n" + 
				"WHERE  login_info.login='"+login+"' AND  login_info.password='"+password+"'";
		ParsingWork<LoginInfoBean> work = (rs)->{
			LoginInfoBean loginInfoBean = null;
			if (rs.next()) {
				loginInfoBean = parseLoginInfoBean(rs);
			}
			return loginInfoBean;
		};	
		return executeRetrievement(sql, work);
	}

	private LoginInfoBean parseLoginInfoBean(final ResultSet rs) throws SQLException {
		LoginInfoBean result = new LoginInfoBean();
		result.setUserId(rs.getInt("user_id"));
		result.setLogin(rs.getString("login"));
		result.setPassword(rs.getString("password"));
		result.setNeedAdminCheck(rs.getBoolean("need_admin_check"));
		return result;
	}

}
