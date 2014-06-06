package org.jjdonov.adintegration.ad.model.events;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "result")
public class EventResult {
	private boolean isSuccesful;
	private String message;
	private String accountIdentifier;
	private String errorCode;
	
	
	
	public String getErrorCode() {
		return errorCode;
	}

	@XmlElement
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public boolean isSuccesful() {
		return isSuccesful;
	}
		
	@XmlElement(name = "success")
	public void setSuccesful(boolean isSuccesful) {
		this.isSuccesful = isSuccesful;
	}

	public String getMessage() {
		return message;
	}
	
	@XmlElement
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getAccountIdentifier() {
		return accountIdentifier;
	}
	
	@XmlElement
	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}
	
	
}


//<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
//<result>
//    <success>true</success>
//    <message>Account creation successful</message>
//    <accountIdentifier>new-account-identifier</accountIdentifier>
//</result>