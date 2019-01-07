package ua.hpopov.parking.presentation.commands;

public enum CommandResult {
	FORWARD, REDIRECT, STAY;
	
	private String argument;

	public String getArgument() {
		return argument;
	}

	public void setArgument(String argument) {
		this.argument = argument;
	}
	
}
