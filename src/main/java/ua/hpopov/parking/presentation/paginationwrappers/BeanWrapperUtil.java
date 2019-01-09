package ua.hpopov.parking.presentation.paginationwrappers;

import java.util.function.Function;

import ua.hpopov.parking.beans.Bean;
import ua.hpopov.parking.beans.LoginInfoBean;
import ua.hpopov.parking.beans.UserBean;
import ua.hpopov.parking.beans.UserProfileWithoutPassword;

public class BeanWrapperUtil {
	public static Function<Bean, BeanWrapper> makeBeanPacker(Bean bean){
		if (bean instanceof UserBean) {
			return new Function<Bean, BeanWrapper>(){

				@Override
				public BeanWrapper apply(Bean bean) {
					return new UserBeanWrapper((UserBean)bean);
				}
				
			};
		} else if (bean instanceof LoginInfoBean) {
			return new Function<Bean, BeanWrapper>(){

				@Override
				public BeanWrapper apply(Bean bean) {
					return new LoginInfoBeanWrapper((LoginInfoBean)bean);
				}
				
			};
		} else if (bean instanceof UserProfileWithoutPassword) {
			return new Function<Bean, BeanWrapper>(){

				@Override
				public BeanWrapper apply(Bean bean) {
					return new UserProfileWithoutPasswordWrapper((UserProfileWithoutPassword)bean);
				}
				
			};
		}
		return null;
	}
}
