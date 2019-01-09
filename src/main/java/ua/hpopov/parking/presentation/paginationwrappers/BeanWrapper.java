package ua.hpopov.parking.presentation.paginationwrappers;

import java.util.Iterator;

import ua.hpopov.parking.beans.LoginInfoBean;
import ua.hpopov.parking.beans.UserBean;
import ua.hpopov.parking.beans.UserProfileWithoutPassword;
import ua.hpopov.parking.beans.UserTypeBean;

public abstract class BeanWrapper implements Iterator<String> {
	
	public abstract int getBeanId();
	
	
	public static BeanWrapper makeUserBeanWrapper(UserBean userBean) {
		return new UserBeanWrapper(userBean);
	}

	public static BeanWrapper makeLoginInfoBeanWrapper(LoginInfoBean loginInfoBean) {
		return new LoginInfoBeanWrapper(loginInfoBean);
	}

	public static BeanWrapper makeUserTypeBeanWrapper(UserTypeBean userTypeBean) {
		return new UserTypeBeanWrapper(userTypeBean);
	}

	public static BeanWrapper makeUserTypeFullWrapper(UserProfileWithoutPassword userProfile) {
		return new UserProfileWithoutPasswordWrapper(userProfile);
	}
}
