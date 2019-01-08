package ua.hpopov.parking.services;

public enum EmailSendingResult {
	SUCCESS, INVALID_EMAIL, NO_USER_WITH_SUCH_EMAIL, FAIL, ERROR;
	
	private String parameter;

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
}
