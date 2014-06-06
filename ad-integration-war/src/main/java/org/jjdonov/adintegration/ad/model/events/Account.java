package org.jjdonov.adintegration.ad.model.events;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "account")
public class Account {
	
	private String accountIdentifier;
	private String status;
	
	
	public String getAccountIdentifier() {
		return accountIdentifier;
	}
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement
	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}
	public String getStatus() {
		return status;
	}
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
