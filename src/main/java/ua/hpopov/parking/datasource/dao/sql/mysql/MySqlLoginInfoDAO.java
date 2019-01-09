package ua.hpopov.parking.datasource.dao.sql.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import ua.hpopov.parking.beans.LoginInfoBean;
import ua.hpopov.parking.datasource.Strings;
import ua.hpopov.parking.datasource.dao.DAOOperationException;
import ua.hpopov.parking.datasource.dao.LoginInfoDAO;
import ua.hpopov.parking.datasource.dao.ParsingWork;
import ua.hpopov.parking.datasource.dao.UpdateResult;

public class MySqlLoginInfoDAO extends MySqlAbstractDAO implements LoginInfoDAO {

	static final String FULL_TABLE_NAME, USER_ID, EMAIL, LOGIN, PASSWORD, NEED_ADMIN_CHECK;
	static {
		FULL_TABLE_NAME = "`parking`.`login_info`";
		USER_ID = "user_id";
		EMAIL = "email";
		LOGIN = "login";
		PASSWORD = "password";
		NEED_ADMIN_CHECK = "need_admin_check";
	}
	
	@Override
	public void createLoginInfo(LoginInfoBean loginInfo) throws DAOOperationException {
		String needAdminCheck = (loginInfo.getNeedAdminCheck()==null?"DEFAULT":
			(loginInfo.getNeedAdminCheck()?"1":"0"));
		String sql = Strings.concat(
				"INSERT INTO ",FULL_TABLE_NAME," SET\r\n",
				USER_ID,"="+loginInfo.getUserId(),",\r\n",
				EMAIL,"='"+loginInfo.getEmail(),"',\r\n",
				LOGIN,"='"+loginInfo.getLogin(),"',\r\n",
				PASSWORD,"='"+loginInfo.getPassword(),"',\r\n",
				NEED_ADMIN_CHECK,"="+needAdminCheck,";"
				);
		executeCreateOperation(sql);
	}

	@Override
	public LoginInfoBean getLoginInfoByUserId(int userId) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT * FROM ",FULL_TABLE_NAME,"\r\n",
				"WHERE ",USER_ID,"="+userId,";"
				);
		ParsingWork<LoginInfoBean> parsingWork = (rs)->{
			if (rs.next()) {
				return parseLoginInfoBean(rs);
			}
			return null;
		};
		return executeRetrievement(sql, parsingWork);
	}

	@Override
	public UpdateResult updateLoginInfoByUserId(LoginInfoBean loginInfo) throws DAOOperationException {
		String sql = Strings.concat(
				"UPDATE ",FULL_TABLE_NAME," SET\r\n",
				EMAIL,"='"+loginInfo.getEmail(),"',\r\n",
				LOGIN,"='"+loginInfo.getLogin(),"',\r\n",
				PASSWORD,"='"+loginInfo.getPassword(),"',\r\n",
				NEED_ADMIN_CHECK,"=",(loginInfo.getNeedAdminCheck()?"1":"0"),"\r\n",
				"WHERE ",USER_ID,"="+loginInfo.getUserId(),";"
				);
		return executeUpdateOperation(sql);
	}

	@Override
	public LoginInfoBean getLoginInfoByLoginOrEmailPassword(String loginOrEmail, String password)
			throws DAOOperationException {
		String part;
		if (loginOrEmail.contains("@")) {
			part = EMAIL;
		} else {
			part = LOGIN;
		}
		String sql = Strings.concat(
				"SELECT `login_info`.* FROM",FULL_TABLE_NAME," AS `login_info`\r\n",
				"WHERE `login_info`.",part,"='",loginOrEmail,"' AND login_info.",PASSWORD,"='"+password+"';"
				);
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
		result.setUserId(rs.getInt(USER_ID));
		result.setLogin(rs.getString(LOGIN));
		result.setEmail(rs.getString(EMAIL));
		result.setPassword(rs.getString(PASSWORD));
		result.setNeedAdminCheck(rs.getBoolean(NEED_ADMIN_CHECK));
		return result;
	}

	@Override
	public LoginInfoBean getLoginInfoByEmail(String email) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT * FROM ",FULL_TABLE_NAME,"\r\n",
				"WHERE ",EMAIL,"='"+email,"';"
				);
		ParsingWork<LoginInfoBean> parsingWork = (rs)->{
			if (rs.next()) {
				return parseLoginInfoBean(rs);
			}
			return null;
		};
		return executeRetrievement(sql, parsingWork);
	}

	@Override
	public Integer getUnconfirmedUsersCount() throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT COUNT(*) FROM ",FULL_TABLE_NAME,"\r\n",
				"WHERE ",NEED_ADMIN_CHECK,"=1;"
				);
		ParsingWork<Integer> parsingWork = (rs)->{
			if (rs.next()) {
				return rs.getInt(0);
			}
			return null;
		};
		return executeRetrievement(sql, parsingWork);
	}

	@Override
	public LoginInfoBean getEdgeUnconfirmedLoginInfo(boolean first) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT * FROM ",FULL_TABLE_NAME,"\r\n",
				"WHERE ",NEED_ADMIN_CHECK,"=1\r\n",
				"ORDER BY ",USER_ID," ",(first?"DESC":"ASC")," LIMIT 1;"
				);
		ParsingWork<LoginInfoBean> parsingWork = (rs)->{
			if (rs.next()) {
				return parseLoginInfoBean(rs);
			}
			return null;
		};
		return executeRetrievement(sql, parsingWork);
	}

}
