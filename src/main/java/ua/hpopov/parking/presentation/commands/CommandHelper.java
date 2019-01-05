package ua.hpopov.parking.presentation.commands;

public class CommandHelper {
	public static Command getCommand(String command) {
		CommandType commandType = CommandType.valueOf(command);
		switch(commandType) {
		case LOG_IN:
			return LoginCommand.getInstance();
		default:
			return NoActionCommand.getInstance();
		}
		
	}
}
