package org.jjdonov.adintegration.ad.model.events;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "event")
public class ADEvent {
	private String type;
	private String flag;
	private String returnUrl;
	private Creator creator;
	private Marketplace marketPlace;
	private Payload payload;

	public String getType() {
		return type;
	}

	@XmlElement
	public void setType(String type) {
		this.type = type;
	}

	public String getFlag() {
		return flag;
	}

	@XmlElement
	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	@XmlElement
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public Creator getCreator() {
		return creator;
	}

	@XmlElement
	public void setCreator(Creator creator) {
		this.creator = creator;
	}

	public Marketplace getMarketPlace() {
		return marketPlace;
	}

	@XmlElement
	public void setMarketPlace(Marketplace marketPlace) {
		this.marketPlace = marketPlace;
	}

	public Payload getPayload() {
		return payload;
	}

	@XmlElement
	public void setPayload(Payload payload) {
		this.payload = payload;
	}

}
