package ua.ita.smartcarservice.security;

/**
 * 
 * This is class is just a message object.
 *
 */

public class ResponseMessage {
	
	private String message;
	 
	public ResponseMessage(String message) {
		this.message = message;
	}
 
	public String getMessage() {
		return message;
	}
 
	public void setMessage(String message) {
		this.message = message;
	}

}
