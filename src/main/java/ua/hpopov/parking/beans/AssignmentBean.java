package ua.hpopov.parking.beans;

import java.util.Date;

public class AssignmentBean implements Bean{
	private Integer driverId;
	private Integer delegatorId;
	private Integer busId;
	private Integer routeId;
	private Date delegationTime;
	private Boolean isConfirmed;
	
	public Integer getDriverId() {
		return driverId;
	}
	
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
	
	public Integer getDelegatorId() {
		return delegatorId;
	}

	public void setDelegatorId(Integer delegatorId) {
		this.delegatorId = delegatorId;
	}

	public Integer getBusId() {
		return busId;
	}
	
	public void setBusId(Integer busId) {
		this.busId = busId;
	}
	
	public Integer getRouteId() {
		return routeId;
	}
	
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
	
	public Date getDelegationTime() {
		return delegationTime;
	}
	
	public void setDelegationTime(Date delegationTime) {
		this.delegationTime = delegationTime;
	}
	
	public Boolean getIsConfirmed() {
		return isConfirmed;
	}
	
	public void setIsConfirmed(Boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}	
}
