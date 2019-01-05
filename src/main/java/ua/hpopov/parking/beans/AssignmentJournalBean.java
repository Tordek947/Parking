package ua.hpopov.parking.beans;

import java.util.Date;

public class AssignmentJournalBean {

	private Long assignmentId;
	private Date delegationTime;
	private Date confirmationTime;
	private Integer delegatorUserId;
	private Integer assigneeDriverId;
	private Integer busId;
	private Integer routeId;
	
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
	
	public Integer getDelegatorUserId() {
		return delegatorUserId;
	}
	
	public void setDelegatorUserId(Integer delegatorUserId) {
		this.delegatorUserId = delegatorUserId;
	}
	
	public Integer getAssigneeDriverId() {
		return assigneeDriverId;
	}
	
	public void setAssigneeDriverId(Integer assigneeDriverId) {
		this.assigneeDriverId = assigneeDriverId;
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
	
	
}
