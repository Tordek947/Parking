package ua.hpopov.parking.presentation.paginationwrappers;

import ua.hpopov.parking.beans.LoginInfoBean;

public class LoginInfoBeanWrapper extends BeanWrapper {

	private LoginInfoBean loginInfoBean;
	public static final int FIELD_COUNT = 2;
	private int nextFieldIndex = 0;
	
	public LoginInfoBeanWrapper(LoginInfoBean loginInfoBean) {
		this.loginInfoBean = loginInfoBean;
	}

	@Override
	public boolean hasNext() {
		return nextFieldIndex+1 < FIELD_COUNT;
	}

	@Override
	public String next() {
		String result = null;
		switch(nextFieldIndex) {
		case 0:
			result = loginInfoBean.getEmail();
			break;
		case 1:
			result = loginInfoBean.getLogin();
			break;
		}
		if (hasNext()) {
			nextFieldIndex++;
		}
		return result;
	}

	@Override
	public int getBeanId() {
		return loginInfoBean.getUserId();
	}
}
