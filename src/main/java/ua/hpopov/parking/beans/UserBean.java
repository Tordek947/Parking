package ua.hpopov.parking.beans;

public class UserBean implements Bean{
	private Integer userId;
	private String name;
	private String surname;
	private Boolean needAdminCheck;
	private Integer userTypeId;
	
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Boolean getNeedAdminCheck() {
		return needAdminCheck;
	}

	public void setNeedAdminCheck(Boolean needAdminCheck) {
		this.needAdminCheck = needAdminCheck;
	}

	public Integer getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}
	
	
}
