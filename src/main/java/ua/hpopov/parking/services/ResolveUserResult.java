package ua.hpopov.parking.services;

import ua.hpopov.parking.beans.UserBean;

public enum ResolveUserResult {
	ERROR, SUCCESS, WAS_ALREADY_RESOLVED;
	
	private UserBean userBean;

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
}
