package ua.hpopov.parking.services;

import ua.hpopov.parking.presentation.paginationwrappers.PaginationBeanSet;

public class WalkPaginationResult {
	
	private PaginationResult paginationResult;
	private PaginationBeanSet paginationBeanSet;
	private boolean hasNextPage;
	private boolean hasPrevPage;

	public PaginationResult getPaginationResult() {
		return paginationResult;
	}

	public void setPaginationResult(PaginationResult paginationResult) {
		this.paginationResult = paginationResult;
	}
	
	public PaginationBeanSet getPaginationBeanSet() {
		return paginationBeanSet;
	}
	
	public void setPaginationBeanSet(PaginationBeanSet paginationBeanSet) {
		this.paginationBeanSet = paginationBeanSet;
	}
	
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	
	public boolean isHasPrevPage() {
		return hasPrevPage;
	}
	
	public void setHasPrevPage(boolean hasPrevPage) {
		this.hasPrevPage = hasPrevPage;
	}
}
