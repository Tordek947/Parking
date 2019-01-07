package ua.hpopov.parking.presentation.commands;

public enum Page {
	WELCOME("/index.jsp"), LOG_IN("/login.jsp"), REGISTRATION("/registration.jsp"),
	REGISTRATION_SUCCESSFUL("/registration_successful.jsp"),
	DRIVER_MAIN("/driver_main.jsp"), ADMIN_MAIN("/admin_main.jsp");
	
	private String path;

	private Page(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
	
}
