package org.jjdonov.adintegration.ad.model.events;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "entry")
public class Entry {

	private String key;
	private String value;

	public String getKey() {
		return key;
	}

	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement
	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement
	public void setValue(String value) {
		this.value = value;
	}

}
