package ua.hpopov.parking.beans;

public class DriverBean implements Bean{
	private Integer driverId;
	private Integer userId;

	public Integer getDriverId() {
		return driverId;
	}
	
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
	
}
