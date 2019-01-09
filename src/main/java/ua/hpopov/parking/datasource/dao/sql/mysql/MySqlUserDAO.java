package ua.hpopov.parking.datasource.dao.sql.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.hpopov.parking.beans.UserBean;
import ua.hpopov.parking.beans.UserProfileWithoutPassword;
import ua.hpopov.parking.beans.UserTypeBean;
import ua.hpopov.parking.datasource.Strings;
import ua.hpopov.parking.datasource.dao.DAOOperationException;
import ua.hpopov.parking.datasource.dao.ParsingWork;
import ua.hpopov.parking.datasource.dao.UpdateResult;
import ua.hpopov.parking.datasource.dao.UserDAO;

public class MySqlUserDAO extends MySqlAbstractDAO implements UserDAO {

	static final String FULL_TABLE_NAME, USER_ID, NAME, SURNAME, USER_TYPE_ID;
	static {
		FULL_TABLE_NAME = "`parking`.`user`";
		USER_ID = "user_id";
		NAME = "name";
		SURNAME = "surname";
		USER_TYPE_ID = "user_type_id";
	}
	
	@Override
	public void createUser(UserBean user) throws DAOOperationException {
		String name = user.getName();
		String surname = user.getSurname();
		Integer userTypeId = user.getUserTypeId();
		String sql = Strings.concat("INSERT INTO ",FULL_TABLE_NAME," SET\r\n",
				"	`",NAME,"`='",name,"',\r\n" + 
				"    `",SURNAME,"`='",surname,"',\r\n" + 
				"    `",USER_TYPE_ID,"`='"+userTypeId,"';");
		executeCreateOperation(sql);
	}

	@Override
	public UserBean getUserById(int userId) throws DAOOperationException {
		String sql = Strings.concat("SELECT user.* FROM ",FULL_TABLE_NAME," AS user\r\n",
				"WHERE  user.",USER_ID,"='"+userId,"';");
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
		result.setUserId(rs.getInt(USER_ID));
		result.setName(rs.getString(NAME));
		result.setSurname(rs.getString(SURNAME));
		result.setUserTypeId(rs.getInt(USER_TYPE_ID));
		return result;
	}

	@Override
	public UpdateResult updateUserById(UserBean user) throws DAOOperationException {
		String sql = Strings.concat(
				"UPDATE ",FULL_TABLE_NAME," SET\r\n",
				NAME,"='"+user.getName(),"',\r\n",
				SURNAME,"='",user.getSurname(),"',\r\n",
				USER_TYPE_ID,"="+user.getUserTypeId(),",\r\n",
				"WHERE ",USER_ID,"="+user.getUserId(),";"
				);
		return executeUpdateOperation(sql);
	}

	@Override
	public UpdateResult deleteUserById(int userId) throws DAOOperationException {
		String sql = Strings.concat(
				"DELETE FROM ",FULL_TABLE_NAME," WHERE\r\n",
				USER_ID,"="+userId,";"
				);
		return executeUpdateOperation(sql);
	}

	@Override
	public List<UserBean> getAllUsersPage(int fromKeyInd, int limit) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT * FROM ",FULL_TABLE_NAME,"\r\n",
				"WHERE ",USER_ID,">="+fromKeyInd,"\r\n",
				"LIMIT "+limit,";"
				);
		ParsingWork<List<UserBean>> parsingWork = (rs)->{
			List<UserBean> result = new ArrayList<>();
			while (rs.next()) {
				result.add(parseUserBean(rs));
			}
			return result;
		};
		return executeRetrievement(sql, parsingWork);
	}

	@Override
	public List<UserBean> getAllUsersByUserTypePage
		(int userTypeId, int fromKeyInd, int limit) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT * FROM ",FULL_TABLE_NAME,"\r\n",
				"WHERE ",USER_TYPE_ID,"="+userTypeId," AND ",USER_ID,">="+fromKeyInd,"\r\n",
				"LIMIT "+limit,";"
				);
		ParsingWork<List<UserBean>> parsingWork = (rs)->{
			List<UserBean> result = new ArrayList<>();
			while (rs.next()) {
				result.add(parseUserBean(rs));
			}
			return result;
		};
		return executeRetrievement(sql, parsingWork);
	}

	@Override
	public Integer getAllUsersCount() throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT COUNT(*) FROM ",FULL_TABLE_NAME,";"
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
	public Integer getAllUsersByUserTypeCount(int userTypeId) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT COUNT(*) FROM ",FULL_TABLE_NAME,"\r\n",
				"WHERE ",USER_TYPE_ID,"="+userTypeId,";"
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
	public UserBean getUserByNameSurname(String name, String surname) throws DAOOperationException {
		String sql = Strings.concat("SELECT user.* FROM ",FULL_TABLE_NAME," AS user\r\n",
				"WHERE  user.",NAME,"='"+name,"' AND\r\n",
				"user.",SURNAME,"='",surname,"';");
		ParsingWork<UserBean> work = (rs)->{
			UserBean userBean = null;
			if (rs.next()) {
				userBean = parseUserBean(rs);
			}
			return userBean;
		};	
		return executeRetrievement(sql, work);
	}

	@Override
	public List<UserProfileWithoutPassword> getUnconfirmedUsersPage(int fromIndex, boolean forward, int pageSize)
			throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT * FROM ",FULL_TABLE_NAME," AS `user`\r\n",
				"INNER JOIN ",MySqlLoginInfoDAO.FULL_TABLE_NAME," AS `login_info` \r\n",
				"ON `user`.",USER_ID,"=`login_info`.",MySqlLoginInfoDAO.USER_ID,"\r\n",
				"WHERE `login_info`.",MySqlLoginInfoDAO.NEED_ADMIN_CHECK,"=1 AND\r\n",
				"`user`.",USER_ID,(forward?"<=":">=")+fromIndex,"\r\n",
				"ORDER BY `user`.",USER_ID," DESC\r\n",
				"LIMIT "+pageSize,";"
				);
		ParsingWork<List<UserProfileWithoutPassword>> parsingWork = (rs)->{
			List<UserProfileWithoutPassword> result = new ArrayList<>();
			while (rs.next()) {
				result.add(parseUserProfileWithoutPassword(rs));
			}
			return result;
		};
		return executeRetrievement(sql, parsingWork);
	}

	private UserProfileWithoutPassword parseUserProfileWithoutPassword(ResultSet rs) throws SQLException {
		UserProfileWithoutPassword result = new UserProfileWithoutPassword();
		result.setUserBean(parseUserBean(rs));
		result.setUserTypeBean(UserTypeBean.fromUserTypeId(result.getUserBean().getUserTypeId()));
		result.setEmail(rs.getString(MySqlLoginInfoDAO.EMAIL));
		result.setLogin(rs.getString(MySqlLoginInfoDAO.LOGIN));
		result.setNeedAdminCheck(rs.getBoolean(MySqlLoginInfoDAO.NEED_ADMIN_CHECK));
		return result;
	}

}
