package ua.hpopov.parking.services;

public enum RegistrationResult {
	SUCCESSFUL, INVALID_EMAIL, INVALID_LOGIN, INVALID_NAME_OR_SURNAME, PASSWORDS_DIFFERS, INVALID_PASSWORD,
	UNUNIQUE_NAME_AND_SURNAME, UNUNIQUE_LOGIN_OR_EMAIL, ERROR;
	
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
