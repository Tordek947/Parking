package ua.hpopov.parking.presentation.paginationwrappers;

import ua.hpopov.parking.beans.UserProfileWithoutPassword;

public class UserProfileWithoutPasswordWrapper extends BeanWrapper {

	private UserProfileWithoutPassword userProfile;
	private UserBeanWrapper userBeanWrapper;
	private UserTypeBeanWrapper userTypeBeanWrapper;
	
	public static final int OWN_FIELD_COUNT = 2;
	public static final int FIELD_COUNT = UserBeanWrapper.FIELD_COUNT + OWN_FIELD_COUNT + UserTypeBeanWrapper.FIELD_COUNT;
	private int nextFieldIndex = 0;
	
	public UserProfileWithoutPasswordWrapper(UserProfileWithoutPassword userProfile) {
		this.userProfile = userProfile;
		userBeanWrapper = new UserBeanWrapper(userProfile.getUserBean());
		userTypeBeanWrapper = new UserTypeBeanWrapper(userProfile.getUserTypeBean());
	}

	@Override
	public boolean hasNext() {
		return nextFieldIndex < FIELD_COUNT;
	}

	@Override
	public String next() {
		String result = null;
		if (userBeanWrapper.hasNext()) {
			result = userBeanWrapper.next();
		} else if (nextFieldIndex - UserBeanWrapper.FIELD_COUNT < OWN_FIELD_COUNT) {
			switch(nextFieldIndex - UserBeanWrapper.FIELD_COUNT) {
			case 0:
				result = userProfile.getEmail();
				break;
			case 1:
				result = userProfile.getLogin();
				break;
			}
		} else {
			result = userTypeBeanWrapper.next();
		}
		if (hasNext()) {
			nextFieldIndex++;
		}
		return result;
	}

	@Override
	public int getBeanId() {
		return userBeanWrapper.getBeanId();
	}

}
