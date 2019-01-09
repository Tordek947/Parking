package ua.hpopov.parking.presentation.paginationwrappers;

import ua.hpopov.parking.beans.UserTypeBean;

public class UserTypeBeanWrapper extends BeanWrapper {

	private UserTypeBean userTypeBean;
	public static final int FIELD_COUNT = 1;
	private int nextFieldIndex = 0;
	
	public UserTypeBeanWrapper(UserTypeBean userTypeBean) {
		this.userTypeBean = userTypeBean;
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
			result = userTypeBean.getUserTypeValue().toLowerCase();
			break;
		}
		if (hasNext()) {
			nextFieldIndex++;
		}
		return result;
	}

	@Override
	public int getBeanId() {
		return userTypeBean.getUserTypeId();
	}

}
