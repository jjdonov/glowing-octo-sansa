package org.jjdonov.adintegration.ad.model.events;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "marketplace")
public class Marketplace {
	
	private String baseUrl;
	private String partner;

	public String getBaseUrl() {
		return baseUrl;
	}

	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getPartner() {
		return partner;
	}

	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement
	public void setPartner(String partner) {
		this.partner = partner;
	}

}
