package org.jjdonov.adintegration.ad.model.events;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "item")
public class Item {
	private Integer quanity;
	private String unit;

	public Integer getQuanity() {
		return quanity;
	}

	@XmlElement
	public void setQuanity(Integer quanity) {
		this.quanity = quanity;
	}

	public String getUnit() {
		return unit;
	}

	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement
	public void setUnit(String unit) {
		this.unit = unit;
	}

}
