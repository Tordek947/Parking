package ua.hpopov.parking.beans;

public class RouteFull implements Bean{
	private Integer routeId;
	private RouteVertexBean routeStartVertex;
	private RouteVertexBean routeEndVertex;
	private Integer routeNumber;
	
	public Integer getRouteId() {
		return routeId;
	}
	
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
	
	public RouteVertexBean getRouteStartVertex() {
		return routeStartVertex;
	}
	
	public void setRouteStartVertex(RouteVertexBean routeStartVertex) {
		this.routeStartVertex = routeStartVertex;
	}
	
	public RouteVertexBean getRouteEndVertex() {
		return routeEndVertex;
	}
	
	public void setRouteEndVertex(RouteVertexBean routeEndVertex) {
		this.routeEndVertex = routeEndVertex;
	}
	
	public Integer getRouteNumber() {
		return routeNumber;
	}
	
	public void setRouteNumber(Integer routeNumber) {
		this.routeNumber = routeNumber;
	}
	
	
}
