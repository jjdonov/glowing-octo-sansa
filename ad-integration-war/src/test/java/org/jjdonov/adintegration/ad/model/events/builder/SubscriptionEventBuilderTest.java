package org.jjdonov.adintegration.ad.model.events.builder;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.net.URL;

import org.jjdonov.adintegration.ad.model.events.ADEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:app-context.xml")
public class SubscriptionEventBuilderTest {
		
	@Autowired
	private SubscriptionEventBuilder builder;
	
	@Test
	public void testOrderFactory() throws ADEventCreationException, FileNotFoundException {
		URL url = this.getClass().getResource("/Order.xml");
		File testXml = new File(url.getFile());
		Reader reader = new FileReader(testXml);
		ADEvent event = builder.buildEvent(reader);
		assertEquals(true, event instanceof ADEvent);
		assertEquals("test-email+creator@appdirect.com",event.getCreator().getEmail());
	}
	
}
