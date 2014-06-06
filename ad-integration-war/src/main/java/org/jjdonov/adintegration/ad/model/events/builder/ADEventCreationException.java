package org.jjdonov.adintegration.ad.model.events.builder;

public class ADEventCreationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 331044225753631662L;
	private String message = null;

	public ADEventCreationException() {
		super();
	}

	public ADEventCreationException(String message) {
		super(message);
		this.message = message;
	}

	public ADEventCreationException(Throwable cause) {
		super(cause);
	}

	@Override
	public String toString() {
		return message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
