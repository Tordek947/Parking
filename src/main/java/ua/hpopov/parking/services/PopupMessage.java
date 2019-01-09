package ua.hpopov.parking.services;

public class PopupMessage {
	
	private String messageText;
	private PopupMessageType messageType;

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public PopupMessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(PopupMessageType messageType) {
		this.messageType = messageType;
	}

	public enum PopupMessageType{
		SUCCESS, INFO, WARNING, DANGER
	}
	
}
