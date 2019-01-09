package ua.hpopov.parking.presentation.paginationwrappers;

import ua.hpopov.parking.beans.UserBean;

public class UserBeanWrapper extends BeanWrapper {

	private UserBean userBean;
	public static final int FIELD_COUNT = 2;
	private int nextFieldIndex = 0;
	
	public UserBeanWrapper(UserBean bean) {
		this.userBean = bean;
	}

	@Override
	public boolean hasNext() {
		return nextFieldIndex < FIELD_COUNT;
	}

	@Override
	public String next() {
		String result = null;
		switch(nextFieldIndex) {
		case 0:
			result = userBean.getName();
			break;
		case 1:
			result = userBean.getSurname();
			break;
		}
		if (hasNext()) {
			nextFieldIndex++;
		}
		return result;
	}

	@Override
	public int getBeanId() {
		return userBean.getUserId();
	}

}
