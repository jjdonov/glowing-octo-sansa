package org.jjdonov.adintegration.ad.model.events;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "company")
public class Company {

	private String country;
	private String email;
	private String name;
	private String phoneNumber;
	private String uuid;
	private String website;

	public String getCountry() {
		return country;
	}

	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement
	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUuid() {
		return uuid;
	}

	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getWebsite() {
		return website;
	}

	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement
	public void setWebsite(String website) {
		this.website = website;
	}

}
