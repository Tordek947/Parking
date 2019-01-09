package ua.hpopov.parking.beans;

import java.util.Date;

public class AssignmentFull implements Bean{
	private DriverFull driverFull;
	private UserBean delegatorUserBean;
	private BusBean busBean;
	private RouteBean routeBean;
	private Date delegationTime;
	private Boolean isConfirmed;
	
	public DriverFull getDriverFull() {
		return driverFull;
	}
	
	public void setDriverFull(DriverFull driverFull) {
		this.driverFull = driverFull;
	}
	
	public UserBean getDelegatorUserBean() {
		return delegatorUserBean;
	}
	
	public void setDelegatorUserBean(UserBean delegatorUserBean) {
		this.delegatorUserBean = delegatorUserBean;
	}
	
	public BusBean getBusBean() {
		return busBean;
	}
	
	public void setBusBean(BusBean busBean) {
		this.busBean = busBean;
	}
	
	public RouteBean getRouteBean() {
		return routeBean;
	}
	
	public void setRouteBean(RouteBean routeBean) {
		this.routeBean = routeBean;
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
