package org.jjdonov.adintegration.ad.model.events;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "configuration")
public class Configuration {

	private List<Entry> entry;

	public List<Entry> getEntry() {
		return entry;
	}

	@XmlElement(name = "entry")
	public void setEntry(List<Entry> entry) {
		this.entry = entry;
	}

}
