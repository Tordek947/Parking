package ua.hpopov.parking.services;

import ua.hpopov.parking.beans.UserBean;

public enum LoginResult {
	INVALID_DATA, SUCCESSFUL, NO_SUCH_USER, ERROR, PROFILE_NEED_VERIFICATION;
	
	private UserBean userBean;
	
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	public UserBean getUserBean() {
		return userBean;
	}
}
