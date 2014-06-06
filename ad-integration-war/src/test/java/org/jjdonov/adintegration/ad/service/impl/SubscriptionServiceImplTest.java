package org.jjdonov.adintegration.ad.service.impl;

import java.util.logging.Logger;

import org.jjdonov.adintegration.ad.service.SubscriptionService;
import org.junit.BeforeClass;

public class SubscriptionServiceImplTest {
	
	private static SubscriptionService service;
	private static Logger log = Logger.getLogger(SubscriptionServiceImplTest.class
			.getName());
	
	@BeforeClass
	public static void setup() {
		service = new SubscriptionServiceImpl();
	}

//	@Test
//	public void testChange() {
//		ADEvent adEvent = new ADEvent();
//		adEvent.setType(SubscriptionEventTypes.SUBSCRIPTION_CHANGE.getType());
//		ChangeEvent event = new ChangeEvent(adEvent);
//		service.handleEvent(event);
//	}
//	
//	@Test
//	public void testOrder() {
//		ADEvent adEvent = new ADEvent();
//		adEvent.setType(SubscriptionEventTypes.SUBSCRIPTION_CHANGE.getType());
//		OrderEvent event = new OrderEvent(adEvent);
//		service.handleEvent(event);
//	}
//	
//	@Test
//	public void testStringFormat() {
//		String eventUrlTemplate = "https://www.appdirect.com/api/integration/v1/events/%s";
//		String s = String.format(eventUrlTemplate, "token");
//		log.info(s);
//		
//	}

}
