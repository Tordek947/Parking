package ua.hpopov.parking.beans;

import java.util.Date;

public class AssignmentJournalFull implements Bean{
	private Long assignmentId;
	private Date delegationTime;
	private Date confirmationTime;
	private UserBean delegatorUserBean;
	private Boolean hasDelegatorSeen;
	private DriverFull assigneeDriverFull;
	private Boolean hasAssigneeSeen;
	private BusBean busBean;
	private RouteBean routeBean;
	
	public Long getAssignmentId() {
		return assignmentId;
	}
	
	public void setAssignmentId(Long assignmentId) {
		this.assignmentId = assignmentId;
	}
	
	public Date getDelegationTime() {
		return delegationTime;
	}
	
	public void setDelegationTime(Date delegationTime) {
		this.delegationTime = delegationTime;
	}
	
	public Date getConfirmationTime() {
		return confirmationTime;
	}
	
	public void setConfirmationTime(Date confirmationTime) {
		this.confirmationTime = confirmationTime;
	}
	
	public UserBean getDelegatorUserBean() {
		return delegatorUserBean;
	}
	
	public void setDelegatorUserBean(UserBean delegatorUserBean) {
		this.delegatorUserBean = delegatorUserBean;
	}
	
	public Boolean getHasDelegatorSeen() {
		return hasDelegatorSeen;
	}

	public void setHasDelegatorSeen(Boolean hasDelegatorSeen) {
		this.hasDelegatorSeen = hasDelegatorSeen;
	}

	public DriverFull getAssigneeDriverFull() {
		return assigneeDriverFull;
	}
	
	public void setAssigneeDriverFull(DriverFull assigneeDriverFull) {
		this.assigneeDriverFull = assigneeDriverFull;
	}
	
	public Boolean getHasAssigneeSeen() {
		return hasAssigneeSeen;
	}

	public void setHasAssigneeSeen(Boolean hasAssigneeSeen) {
		this.hasAssigneeSeen = hasAssigneeSeen;
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
	
	
}
