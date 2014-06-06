package org.jjdonov.adintegration.ad.model.events;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "payload")
public class Payload {
	private Company company;
	private Configuration configuration;
	private Order order;
	private Account account;

	public Company getCompany() {
		return company;
	}

	@XmlElement
	public void setCompany(Company company) {
		this.company = company;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	@XmlElement
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public Order getOrder() {
		return order;
	}

	@XmlElement
	public void setOrder(Order order) {
		this.order = order;
	}

	public Account getAccount() {
		return account;
	}

	@XmlElement
	public void setAccount(Account account) {
		this.account = account;
	}
	
	

}
