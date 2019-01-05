package ua.hpopov.parking.beans;

public class LoginInfoBean {
	private Integer userId;
	private String email;
	private String login;
	private String password;
	private Boolean needAdminCheck;
	
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getNeedAdminCheck() {
		return needAdminCheck;
	}

	public void setNeedAdminCheck(Boolean needAdminCheck) {
		this.needAdminCheck = needAdminCheck;
	}
	
	
}
