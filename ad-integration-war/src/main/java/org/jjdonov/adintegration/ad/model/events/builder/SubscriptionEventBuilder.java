package org.jjdonov.adintegration.ad.model.events.builder;

import java.io.Reader;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.jjdonov.adintegration.ad.model.events.ADEvent;
import org.jjdonov.adintegration.ad.model.events.ActionableEvent;
import org.jjdonov.adintegration.ad.model.events.ChangeEvent;
import org.jjdonov.adintegration.ad.model.events.OrderEvent;
import org.jjdonov.adintegration.ad.model.events.SubscriptionEventTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.XmlMappingException;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionEventBuilder implements ADEventBuildder {
	@Autowired
	@Qualifier("unmarshaller")
	private Unmarshaller unmarshaller;
	
	@Override
	public ADEvent buildEvent(Reader reader) throws ADEventCreationException{
		ADEvent event = null;
		try {
			event = (ADEvent) unmarshaller.unmarshal(new StreamSource(reader));
		} catch (XmlMappingException | JAXBException e) {
			throw new ADEventCreationException(e);
		}
		return event;
	}

}
