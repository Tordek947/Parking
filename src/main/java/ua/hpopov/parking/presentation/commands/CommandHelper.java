package ua.hpopov.parking.presentation.commands;

public class CommandHelper {
	public static Command getCommand(String command) {
		CommandType commandType = CommandType.valueOf(command);
		switch(commandType) {
		case LOG_IN:
			return LoginCommand.getInstance();
		case FORWARD_TO_LOGIN:
			return ForwardToLoginCommand.getInstance();
		case NO_ACTION:
			break;
		case REGISTER:
			return RegisterCommand.getInstance();
		case FORWARD_TO_REGISTRATION:
			return ForwardToRegistrationCommand.getInstance();
		case RESTORE_PASSWORD:
			return RestorePasswordCommand.getInstance();
		case RESET_PASSWORD:
			return ResetPasswordCommand.getInstance();
		case LOG_OUT:
			return LogOutCommand.getInstance();
		case NEW_USERS:
			return NewUsersCommand.getInstance();
		}
		return NoActionCommand.getInstance();
	}
}
