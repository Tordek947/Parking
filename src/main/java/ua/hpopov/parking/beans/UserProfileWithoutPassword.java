package ua.hpopov.parking.beans;

public class UserProfileWithoutPassword implements Bean{
	private UserBean userBean;
	private UserTypeBean userTypeBean;
	private String email;
	private String login;
	private Boolean needAdminCheck;
	
	public UserBean getUserBean() {
		return userBean;
	}
	
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	public UserTypeBean getUserTypeBean() {
		return userTypeBean;
	}
	
	public void setUserTypeBean(UserTypeBean userTypeBean) {
		this.userTypeBean = userTypeBean;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public Boolean getNeedAdminCheck() {
		return needAdminCheck;
	}
	
	public void setNeedAdminCheck(Boolean needAdminCheck) {
		this.needAdminCheck = needAdminCheck;
	}
}
