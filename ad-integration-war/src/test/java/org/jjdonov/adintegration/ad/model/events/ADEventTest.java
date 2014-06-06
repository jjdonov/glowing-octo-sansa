package org.jjdonov.adintegration.ad.model.events;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.junit.BeforeClass;
import org.junit.Test;

public class ADEventTest {

	private static JAXBContext jc;
	private static Unmarshaller unmarshaller;
	
	@BeforeClass
	public static void setup() throws JAXBException {
		jc = JAXBContext.newInstance(ADEvent.class);
		unmarshaller = jc.createUnmarshaller();
	}
	
	@Test
	public void testMarshallingOrder() throws JAXBException, IOException {
		URL url = this.getClass().getResource("/Order.xml");
		File testXml = new File(url.getFile());
		Reader reader = new FileReader(testXml);
		ADEvent event = (ADEvent) unmarshaller.unmarshal(new StreamSource(reader));
		assertEquals(SubscriptionEventTypes.SUBSCRIPTION_ORDER.toString(), event.getType());
	}
	
	@Test
	public void quickTest() {
		ActionableEvent event = eventGen();
		assertEquals(true, event instanceof OrderEvent);
	}
	
	private ActionableEvent eventGen() {
		ADEvent adEvent = new ADEvent();
		adEvent.setType(SubscriptionEventTypes.SUBSCRIPTION_ORDER.getType());
		OrderEvent event = new OrderEvent(adEvent);
		return event;
	}

}
