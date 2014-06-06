package org.jjdonov.adintegration.ad.model.events;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "order")
public class Order {

	private String editionCode;
	private List<Item> item;
	private String pricingDuration;

	public String getEditionCode() {
		return editionCode;
	}

	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement
	public void setEditionCode(String editionCode) {
		this.editionCode = editionCode;
	}

	public List<Item> getItem() {
		return item;
	}

	@XmlElements(value = { @XmlElement(name = "item") })
	public void setItem(List<Item> item) {
		this.item = item;
	}

	public String getPricingDuration() {
		return pricingDuration;
	}

	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement
	public void setPricingDuration(String pricingDuration) {
		this.pricingDuration = pricingDuration;
	}

}
