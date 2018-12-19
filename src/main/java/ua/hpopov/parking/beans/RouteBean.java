package ua.hpopov.parking.beans;

public class RouteBean {
	private Integer routeId;
	private Integer routeStartVertexId;
	private Integer routeEndVertexId;
	private Integer routeNumber;
	
	public Integer getRouteId() {
		return routeId;
	}
	
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
	
	public Integer getRouteStartVertexId() {
		return routeStartVertexId;
	}
	
	public void setRouteStartVertexId(Integer routeStartVertexId) {
		this.routeStartVertexId = routeStartVertexId;
	}
	
	public Integer getRouteEndVertexId() {
		return routeEndVertexId;
	}
	
	public void setRouteEndVertexId(Integer routeEndVertexId) {
		this.routeEndVertexId = routeEndVertexId;
	}

	public Integer getRouteNumber() {
		return routeNumber;
	}

	public void setRouteNumber(Integer routeNumber) {
		this.routeNumber = routeNumber;
	}
	
	
}
