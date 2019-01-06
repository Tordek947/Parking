package ua.hpopov.parking.datasource.dao.sql.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import ua.hpopov.parking.beans.UserTypeBean;
import ua.hpopov.parking.datasource.Strings;
import ua.hpopov.parking.datasource.dao.DAOOperationException;
import ua.hpopov.parking.datasource.dao.ParsingWork;
import ua.hpopov.parking.datasource.dao.UserTypeDAO;

public class MySqlUserTypeDAO extends MySqlAbstractDAO implements UserTypeDAO {

	static final String FULL_TABLE_NAME, USER_TYPE_ID, USER_TYPE_VALUE;
	static {
		FULL_TABLE_NAME = "`parking`.`user_type`";
		USER_TYPE_ID = "user_type_id";
		USER_TYPE_VALUE = "user_type_value";
	}
	
	@Override
	public UserTypeBean getUserTypeByUserTypeId(int userTypeId) throws DAOOperationException {
		String sql = Strings.concat(
				"SELECT * FROM ",FULL_TABLE_NAME,"\r\n",
				"WHERE ",USER_TYPE_ID,"="+userTypeId,";"
				);
		ParsingWork<UserTypeBean> parsingWork = (rs)->{
			if (rs.next()) {
				return parseUserTypeBean(rs);
			}
			return null;
		};
		return executeRetrievement(sql, parsingWork);
	}

	private UserTypeBean parseUserTypeBean(ResultSet rs) throws SQLException {
		UserTypeBean result = new UserTypeBean();
		result.setUserTypeId(rs.getInt(USER_TYPE_ID));
		result.setUserTypeValue(rs.getString(USER_TYPE_VALUE));
		return result;
	}

}
