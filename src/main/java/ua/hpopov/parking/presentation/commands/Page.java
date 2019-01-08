package ua.hpopov.parking.presentation.commands;

public enum Page {
	WELCOME("/index.jsp"), LOG_IN("/login.jsp"), REGISTRATION("/registration.jsp"),
	REGISTRATION_SUCCESSFUL("/registration_successful.jsp"),
	FORGOT_PASSWORD("/forgot_password.jsp"), RESET_PASSWORD("/reset_password.jsp"),
	RESET_PASSWORD_SUCCESSFUL("/reset_password_successful.jsp"),
	DRIVER_MAIN("/driver_main.jsp"), ADMIN_MAIN("/admin_main.jsp"),
	ERROR("/error.jsp");
	
	private String path;

	private Page(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
	
	public String getName() {
		return path.substring(1);
	}
	
}
